package com.example.SocialLoginPractice.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.List;
import java.util.Map;

public interface ProviderUser {
    // 유니크한 id
    String getId();
    String getUsername();
    String getPassword();
    String getEmail();
    // 어디 서비스로부터 온 것인지 알기 위해
    String getProvider();
    List<? extends GrantedAuthority> getAuthorities();
    // 서비스 제공자로부터 받는 값들
    Map<String,Object> getAttributes();

    public String getPicture();

    OAuth2User getOAuth2User();
    void isCertificated(boolean isCertificated);
    boolean isCertificated();
}

