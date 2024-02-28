package com.example.SocialLoginPractice.service;

import com.example.SocialLoginPractice.converters.ProviderUserRequest;
import com.example.SocialLoginPractice.model.PrincipalUser;
import com.example.SocialLoginPractice.model.ProviderUser;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends AbstractOAuth2UserService implements OAuth2UserService<OAuth2UserRequest , OAuth2User> { // <요청과 반환값>
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        ClientRegistration clientRegistration = userRequest.getClientRegistration();
        OAuth2UserService<OAuth2UserRequest , OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();

        // 실제 인가서버와 통신을 하고 사용자 정보를 가져온다.
        // 가져온 정보를 OAuth2User 타입에 저장을 해서 반환을 하는 것
        OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);

        // OAuth2 인증 방식이기 떄문에 User 는 null 을 넣어준다.
        ProviderUserRequest providerUserRequest = new ProviderUserRequest(clientRegistration,oAuth2User);
        ProviderUser providerUser = providerUser(providerUserRequest);

        // 어떤 타입인지 확인한다. (전의 방식)
//        ProviderUser providerUser = super.providerUser(clientRegistration, oAuth2User);

        // 회원가입 (DB 저장)
        super.register(providerUser , userRequest);
        return new PrincipalUser(providerUser);
    }
}
