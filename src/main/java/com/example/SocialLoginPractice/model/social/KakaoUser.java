package com.example.SocialLoginPractice.model.social;

import com.example.SocialLoginPractice.model.Attributes;
import com.example.SocialLoginPractice.model.OAuth2ProviderUser;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

public class KakaoUser extends OAuth2ProviderUser {

    private Map<String,Object> otherAttributes;
    public KakaoUser(Attributes attributes, OAuth2User oAuth2User, ClientRegistration clientRegistration) {
        super(attributes.getOtherAttributes() ,oAuth2User,clientRegistration);
        this.otherAttributes = attributes.getOtherAttributes();
    }

    // 서비스마다 동일되는 것들은 가능하지만 id , username 은 좀 차이가 나기 때문에 따로 정의해야한다.

    @Override
    public String getId() {
        return (String) getAttributes().get("id");
    }

    @Override
    public String getUsername() {
        return (String) otherAttributes.get("nickname");
    }

    @Override
    public String getPicture() {
        // return (String) otherAttributes.get("profile_image_url");
        return null;
    }
}
