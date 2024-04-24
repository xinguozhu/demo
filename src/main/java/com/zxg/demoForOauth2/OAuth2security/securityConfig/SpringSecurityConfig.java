package com.zxg.demoForOauth2.OAuth2security.securityConfig;

import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.zxg.demoForOauth2.OAuth2security.handler.AuthFailureHandler;
import com.zxg.demoForOauth2.OAuth2security.customize.CustomizedTokenResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

@EnableWebSecurity
@Configuration(proxyBeanMethods = false)
public class SpringSecurityConfig {

    private  final String regex="/test1\\?.*RPTID=(dd|cc).*";

    /**
     * Spring Security SecurityFilterChain 认证配置
     */
    @Bean
    @Order(22)
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
            throws Exception {
        http
                //.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        .authorizeHttpRequests((authorize) -> authorize
                .requestMatchers(new RegexRequestMatcher(regex,"POST")).authenticated().anyRequest().permitAll()

        )
                .csrf(AbstractHttpConfigurer::disable).oauth2ResourceServer()
                //配置校验失败的处理
                .authenticationEntryPoint(new AuthFailureHandler())
                .bearerTokenResolver(customizedTokenResolver()).jwt();
        return http.build();
    }

    @Bean
    public CustomizedTokenResolver customizedTokenResolver(){
        return new CustomizedTokenResolver();
    }

    /**
     * 解码签名访问令牌
     */
    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring()
                // Spring Security should completely ignore URLs starting with /resources/
                .requestMatchers("/test/**");
    }

    /**
     * 内存存储用户
     */
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails userDetails = User.withDefaultPasswordEncoder()
                .username("user")
                .password("123456")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(userDetails);
    }
}

