package edu.xiao.java01jwt.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JwtToken生成的工具类
 * <p>
 * JWT token的格式：header.payload.signature
 * </p>
 * header的格式（算法、token的类型）：
 * {"alg": "HS512","typ": "JWT"}
 * <p>
 * payload的格式（用户名、创建时间、生成时间）：
 * {"sub":"wang","created":1489079981393,"exp":1489684781}
 * </p>
 * <p>
 * signature的生成算法：
 * HMACSHA512(base64UrlEncode(header) + "." +base64UrlEncode(payload),secret)
 * </p>
 * @author xiaoning
 */
@Slf4j
@Component
public class JwtTokenUtils {
    /**
     * jwt负载中用户名的key
     */
    private static final String CLAIM_KEY_USERNAME = "sub";
    /**
     * jwt负载中token创建时间的key
     */
    private static final String CLAIM_KEY_CREATED = "created";

    /**
     * jwt token 生成的密钥
     */
    @Value("${jwt.secret}")
    private String secret;

    /**
     * jwt token 过期时间
     */
    @Value("${jwt.expiration}")
    private Long expiration;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    /**
     * 根据用户信息生成token
     *
     * @param userDetails 用户信息
     * @return
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * 根据负载生成token
     *
     * @param claims 负载
     * @return
     */
    private String generateToken(Map<String, Object> claims) {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        SecretKey secretKey = Keys.hmacShaKeyFor(keyBytes);
        return Jwts.builder()
                .addClaims(claims)
                .setExpiration(generateExpiration())
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    /**
     * 生成过期时间
     *
     * @return
     */
    private Date generateExpiration() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * 从token中获取用户名
     *
     * @param token
     * @return
     */
    public String getUsernameFromToken(String token) {
        String username = null;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            log.error("从token中获取用户名错误。原因：{}", e.getMessage(), e);
        }
        return username;
    }

    /**
     * 从token中获取负载
     *
     * @param token
     * @return
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            JwtParser jwtParser = Jwts.parserBuilder()
                    .setSigningKey(secret)
                    .build();
            claims = jwtParser.parseClaimsJws(token)
                    .getBody();
            return claims;
        } catch (Exception e) {
            log.error("jwt token 格式验证失败。原因：{}", e.getMessage(), e);
        }
        return claims;
    }

    /**
     * 验证token是否有效
     *
     * @param token
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        // 获取用户名
        String username = getUsernameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * token 是否过期
     *
     * @param token
     * @return
     */
    private boolean isTokenExpired(String token) {
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }

    /**
     * 从token中获取过期时间
     *
     * @param token
     * @return
     */
    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }
}
