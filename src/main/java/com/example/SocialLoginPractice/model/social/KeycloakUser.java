//package com.example.SocialLoginPractice.model.social;
//
//import com.example.SocialLoginPractice.model.OAuth2ProviderUser;
//import org.springframework.security.oauth2.client.registration.ClientRegistration;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//
//public class KeycloakUser extends OAuth2ProviderUser {
//
//    public KeycloakUser(OAuth2User oAuth2User, ClientRegistration clientRegistration) {
//        super(oAuth2User.getAttributes(), oAuth2User,clientRegistration);
//    }
//
//    // 서비스마다 동일되는 것들은 가능하지만 id , username 은 좀 차이가 나기 때문에 따로 정의해야한다.
//
//    @Override
//    public String getId() {
//        return (String) getAttributes().get("sub");
//    }
//
//    @Override
//    public String getUsername() {
//        return (String) getAttributes().get("preferred_username");
//    }
//}
