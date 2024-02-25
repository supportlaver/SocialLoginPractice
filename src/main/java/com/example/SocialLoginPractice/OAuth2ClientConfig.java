package com.example.SocialLoginPractice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@Slf4j
public class OAuth2ClientConfig {

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        log.info("WebSecurityCustomizer");
//        return (web) -> web.ignoring().requestMatchers(
//                "/static/js/**","/static/images/**","/static/css/**","/static/scss/**","/static/js/**");
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authRequest -> authRequest.requestMatchers("/").permitAll());
        http.authorizeHttpRequests(authRequest -> authRequest.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll());
        http.oauth2Login(Customizer.withDefaults());
        http.logout(logout -> logout.logoutSuccessUrl("/"));
        return http.build();
    }
}
