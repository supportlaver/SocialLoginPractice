package com.example.SocialLoginPractice.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {


    @GetMapping("/api/user")
    public Authentication user(Authentication authentication, @AuthenticationPrincipal OAuth2User oAuth2User) {
        // 인증을 다 받은 상태로 오고, 권한은 profile , email 을 가지고 있다.
        System.out.println("authentication = " + authentication + ", oAuth2User = " + oAuth2User);
        return authentication;
    }

    // google 은 oidc 를 지원하지만 naver 는 oidc 를 지원하지 않는다.
    @GetMapping("/api/oidc")
    public Authentication oidc(Authentication authentication, @AuthenticationPrincipal OidcUser oidcUser) {
        System.out.println("authentication = " + authentication + ", oidcUser = " + oidcUser);
        return authentication;
    }


}
