package edu.xiao.java01jwt.config;


import edu.xiao.java01jwt.handler.JwtTokenRequestFilter;
import edu.xiao.java01jwt.handler.MyAccessDeniedHandler;
import edu.xiao.java01jwt.handler.MyAuthenticationEntryPoint;
import edu.xiao.java01jwt.handler.MyAuthenticationFailureHandler;
import edu.xiao.java01jwt.handler.MyAuthenticationSuccessHandler;
import edu.xiao.java01jwt.handler.MyAuthorizationManager;
import edu.xiao.java01jwt.handler.MyUsernamePasswordAuthenticationFilter;
import edu.xiao.java01jwt.service.UserService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@EnableWebSecurity(debug = true)
@Configuration
@EnableConfigurationProperties({SpringSecurityProperties.class})
public class SpringSecurityConfig {

    private final SpringSecurityProperties springSecurityProperties;

    private final AuthenticationConfiguration authenticationConfiguration;

    private final MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    private final MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    private final UserService userService;

    private final JwtTokenRequestFilter jwtTokenRequestFilter;

    private final MyAuthorizationManager myAuthorizationManager;

    public SpringSecurityConfig(SpringSecurityProperties springSecurityProperties, AuthenticationConfiguration authenticationConfiguration, MyAuthenticationSuccessHandler myAuthenticationSuccessHandler, MyAuthenticationFailureHandler myAuthenticationFailureHandler, UserService userService, JwtTokenRequestFilter jwtTokenRequestFilter, MyAuthorizationManager myAuthorizationManager) {
        this.springSecurityProperties = springSecurityProperties;
        this.authenticationConfiguration = authenticationConfiguration;
        this.myAuthenticationSuccessHandler = myAuthenticationSuccessHandler;
        this.myAuthenticationFailureHandler = myAuthenticationFailureHandler;
        this.userService = userService;
        this.jwtTokenRequestFilter = jwtTokenRequestFilter;
        this.myAuthorizationManager = myAuthorizationManager;
    }

    @Bean
    public MyUsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter() throws Exception {
        MyUsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter =
                new MyUsernamePasswordAuthenticationFilter(springSecurityProperties.getLoginProcessingUrl());
        myUsernamePasswordAuthenticationFilter.setAuthenticationManager(authenticationManager(authenticationConfiguration));
        myUsernamePasswordAuthenticationFilter.setAuthenticationSuccessHandler(myAuthenticationSuccessHandler);
        myUsernamePasswordAuthenticationFilter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);

        return myUsernamePasswordAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        http
            .addFilterBefore(myUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(jwtTokenRequestFilter, MyUsernamePasswordAuthenticationFilter.class)
            .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
                authorizationManagerRequestMatcherRegistry
                    // 设置不需要认证的路径
                    .requestMatchers(springSecurityProperties.getIgnoreUrl().toArray(new String[0])).permitAll()
                    .anyRequest()
                    // 所有请求经过授权才能访问
                    .access(myAuthorizationManager);
            })
            .formLogin(formLoginCustomizer -> {
                formLoginCustomizer
                        .loginPage("/api/user/login/needLogin")
                        .loginProcessingUrl("/api/user/login").permitAll()
                        .successHandler(myAuthenticationSuccessHandler)
                        .failureHandler(myAuthenticationFailureHandler);
            })
            .exceptionHandling(exceptionHandlingCustomizer -> {
                exceptionHandlingCustomizer
                        // 自定义认证异常处理
                        .authenticationEntryPoint(new MyAuthenticationEntryPoint())
                        // 自定义授权失败处理
                        .accessDeniedHandler(new MyAccessDeniedHandler());
            })
            .sessionManagement(sessionManagementCustomizer -> {
                // 基于token，所以不需要session
                // 设置不使用cookie。每个请求需要重新验证
                sessionManagementCustomizer
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            })
            // 设置允许跨域
            .cors(corsCustomizer -> {
                CorsConfiguration corsConfiguration = new CorsConfiguration();
                corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
                corsConfiguration.setAllowedMethods(Arrays.asList("*"));
                corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
                corsConfiguration.setMaxAge(3600L);
                UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
                configurationSource.registerCorsConfiguration("/**", corsConfiguration);
                corsCustomizer.configurationSource(configurationSource);
            })
            // 关闭csrf
            .csrf(AbstractHttpConfigurer::disable)
            .headers(headerCustomizer -> {
                headerCustomizer
                        .cacheControl(HeadersConfigurer.CacheControlConfig::disable)
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable);
            });

        return http.build();
    }


    /**
     * 认证管理器，登录的时候参数会传给 authenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userService.loadUserByUsername(username);
    }

    /**
     * 密码加密方式
     *
     * @return
     */
    // @Bean
    // public PasswordEncoder passwordEncoder() {
    //
    // }

}
