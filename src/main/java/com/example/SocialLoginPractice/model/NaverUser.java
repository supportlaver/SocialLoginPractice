package com.example.SocialLoginPractice.model;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

public class NaverUser extends OAuth2ProviderUser{
    public NaverUser(OAuth2User oAuth2User, ClientRegistration clientRegistration) {
        // 네이버는 response 라는 계층 안에 각 정보들이 있기 때문에 response 를 먼저 생성자에서 가져오고
        // 사용할 때 거기서 한 번 더 꺼내서 써야한다.
        // 즉 계층이 하나 더 있다 (네이버만 이러한 특징이 있다)
        super((Map<String, Object>) oAuth2User.getAttributes().get("response"), oAuth2User,clientRegistration);
    }

    // 서비스마다 동일되는 것들은 가능하지만 id , username 은 좀 차이가 나기 때문에 따로 정의해야한다.

    @Override
    public String getId() {
        return (String) getAttributes().get("id");
    }

    @Override
    public String getUsername() {
        return (String) getAttributes().get("email");
    }
}
