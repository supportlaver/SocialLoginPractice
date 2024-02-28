package com.example.SocialLoginPractice.model.social;

import com.example.SocialLoginPractice.model.Attributes;
import com.example.SocialLoginPractice.model.OAuth2ProviderUser;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class GoogleUser extends OAuth2ProviderUser {

    public GoogleUser(Attributes attributes ,OAuth2User oAuth2User, ClientRegistration clientRegistration) {
        super(attributes.getMainAttributes(),oAuth2User,clientRegistration);
    }

    // 서비스마다 동일되는 것들은 가능하지만 id , username 은 좀 차이가 나기 때문에 따로 정의해야한다.


    // 우리가 인가 서버로부터 사용자의 정보를 가져오면 그 정보들은 Attributes 에 담겨있다.
    @Override
    public String getId() {
        // Google 은 sub 로 가져올 수 있다.
        // 그냥 ID 는 식별자의 역할을 한다.
        return (String) getAttributes().get("sub");
    }

    @Override
    public String getUsername() {
        // Username 이 userId 라고 생각하면 된다.
        // Google 은 Id , Username 모두 sub 로 가져온다.
        return (String) getAttributes().get("sub");
    }

    @Override
    public String getPicture() {
        return null;
    }
}
