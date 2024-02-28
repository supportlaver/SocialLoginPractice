package com.example.SocialLoginPractice.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JiwonController {

    @GetMapping("/jiwon")
    public Authentication jiwon(Authentication authentication, @AuthenticationPrincipal OAuth2User oAuth2User) {
        return authentication;
    }
}
