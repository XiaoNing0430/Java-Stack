package com.example.java01jwt.handler;

import com.example.java01jwt.config.SpringSecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

/**
 * 自定义授权管理器
 */
@Slf4j
@Component
public class MyAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {
    /**
     * 拒绝访问
     */
    private static final AuthorizationDecision DENY = new AuthorizationDecision(false);

    /**
     * 允许访问
     */
    private static final AuthorizationDecision PERMIT = new AuthorizationDecision(true);
    /**
     * 放弃决策
     */
    private static final AuthorizationDecision ABANDON = null;

    private final SpringSecurityProperties springSecurityProperties;

    public MyAuthorizationManager(SpringSecurityProperties springSecurityProperties) {
        this.springSecurityProperties = springSecurityProperties;
    }

    /**
     * Determines if access should be granted for a specific authentication and object.
     *
     * @param authentication the {@link Supplier} of the {@link Authentication} to check
     * @param object         the {@link T} object to check
     * @throws AccessDeniedException if access is not granted
     */
    @Override
    public void verify(Supplier<Authentication> authentication, RequestAuthorizationContext object) {
        AuthorizationManager.super.verify(authentication, object);
    }

    /**
     * Determines if access is granted for a specific authentication and object.
     *
     * @param authentication the {@link Supplier} of the {@link Authentication} to check
     * @param object         the {@link T} object to check
     * @return an {@link AuthorizationDecision} or null if no decision could be made
     */
    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext object) {
        String requestURI = object.getRequest().getRequestURI();
        if (springSecurityProperties.getIgnoreUrl().contains(requestURI)) {
            // 在白名单中，直接放下
            log.info("请求路径：{} 在白名单中，直接放行", requestURI);
            return PERMIT;
        }

        if (object.getRequest().getMethod().equals(HttpMethod.OPTIONS.name())) {
            // option请求直接放行
            log.info("请求路径：{} 为OPTIONS请求，直接放行", requestURI);
            return PERMIT;
        }

        // 获取用户权限列表
        Collection<? extends GrantedAuthority> authorities = authentication.get().getAuthorities();
        List<String> authorityStrs = authorities.stream().map(GrantedAuthority::getAuthority).toList();
        PathMatcher pathMatcher = new AntPathMatcher();
        for (String authorityStr : authorityStrs) {
            if (pathMatcher.match(authorityStr, requestURI)) {
                log.info("请求路径：{} 匹配权限：{}，直接放行", requestURI, authorityStr);
                return PERMIT;
            }
        }

        // 无权限，拒绝访问
        log.info("请求路径：{} 无权限访问，拒绝访问", requestURI);
        return DENY;
    }
}
