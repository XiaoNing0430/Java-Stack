package edu.xiao.java01jwt.handler;

import edu.xiao.java01jwt.constant.RedisConstants;
import edu.xiao.java01jwt.exception.TokenExpiredException;
import edu.xiao.java01jwt.model.bo.UserBO;
import edu.xiao.java01jwt.util.JwtTokenUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@Schema(title = "自定义权限过滤")
public class JwtTokenRequestFilter extends OncePerRequestFilter {

    private final JwtTokenUtils jwtTokenUtils;

    private final RedisTemplate redisTemplate;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    public JwtTokenRequestFilter(JwtTokenUtils jwtTokenUtils, RedisTemplate redisTemplate) {
        this.jwtTokenUtils = jwtTokenUtils;
        this.redisTemplate = redisTemplate;
    }

    /**
     * Same contract as for {@code doFilter}, but guaranteed to be
     * just invoked once per request within a single request thread.
     * See {@link #shouldNotFilterAsyncDispatch()} for details.
     * <p>Provides HttpServletRequest and HttpServletResponse arguments instead of the
     * default ServletRequest and ServletResponse ones.
     *
     * @param request
     * @param response
     * @param filterChain
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("jwt 过滤");
        String authHeader = request.getHeader(tokenHeader);
        if (StringUtils.isEmpty(authHeader)) {
            // token为空
            filterChain.doFilter(request, response);
            return;
        }
        // 拿到token
        String token = authHeader.substring(tokenHead.length());
        // 解析出用户名
        String username = jwtTokenUtils.getUsernameFromToken(token);
        // 从缓存中拿到userDetails信息
        String key = RedisConstants.TOKEN_KEY_PREFIX + token + RedisConstants.SEPARATOR + username;
        UserBO userBO = (UserBO) redisTemplate.opsForValue().get(key);
        if (ObjectUtils.isEmpty(userBO)) {
            // token已过期
            throw new TokenExpiredException("token已过期，请重新登录");
        }
        // 效验token
        boolean isValidated = jwtTokenUtils.validateToken(token, userBO);
        if (isValidated) {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(userBO, null, userBO.getAuthorities());
            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }

        filterChain.doFilter(request, response);
    }
}
