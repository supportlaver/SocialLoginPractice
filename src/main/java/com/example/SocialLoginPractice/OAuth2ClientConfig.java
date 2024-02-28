package com.example.SocialLoginPractice;

import com.example.SocialLoginPractice.service.CustomOAuth2UserService;
import com.example.SocialLoginPractice.service.CustomOidcUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
@Slf4j
public class OAuth2ClientConfig {

    // @Service 로 등록했기 때문에 DI 받으면 된다.

    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;
    @Autowired
    private CustomOidcUserService customOidcUserService;


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/static/js/**", "/static/images/**", "/static/css/**","/static/scss/**");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authRequest -> authRequest.
                requestMatchers(new AntPathRequestMatcher("/")).permitAll().
                requestMatchers(new AntPathRequestMatcher("/jiwon")).permitAll().
                // 사용자가 다음과 같은 권한을 가지고 있다면 /api/user 에 접근할 수 있도록 한다.
                        // naver 는 ROLE_OAUTH2_USER 로
                                // keycloak 은    ROLE_SCOPE_profile, ROLE_SCOPE_openid , ROLE_SCOPE_email , ROLE_OIDC_USER
                requestMatchers(new AntPathRequestMatcher("/api/user")).hasAnyRole("OAUTH2_USER").
//                requestMatchers(new AntPathRequestMatcher("/api/user")).hasAnyRole("OAUTH2_USER").
                requestMatchers(new AntPathRequestMatcher("/api/oidc")).hasAnyRole("SCOPE_openid"));
        http.oauth2Login(oauth2Login -> oauth2Login.
                userInfoEndpoint(
                        userInfo -> userInfo
                                .userService(customOAuth2UserService)
                                .oidcUserService(customOidcUserService)));
        // form 인증을 위한 설정 (th:action="@{/loginProc}")
        http.formLogin(formLogin ->
                formLogin.loginPage("/login").loginProcessingUrl("/loginProc").defaultSuccessUrl("/").permitAll());
        // login 실패시 예외 Handling
        http.exceptionHandling(exceptionHandling->
                exceptionHandling.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login")));

//        http.logout(logout -> logout.logoutSuccessUrl("/"));
        return http.build();
    }

    // 구글 같은 경우는 scope 가 email , profile 이런식으로 오는게 아니라
    // http://~~~ 의 긴 문자열을 반환한다.
    // 그래서 우리는 이것을 파싱을 해서 email , profile 만을 추출해야한다.
    // 해당 역할을 하는 것을 빈으로 등록해보자.
    @Bean
    public GrantedAuthoritiesMapper customAuthorityMapper() {
        log.info("customAuthorityMapper");
        return new CustomAuthorityMapper();
    }
}
