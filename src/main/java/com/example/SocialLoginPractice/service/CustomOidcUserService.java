package com.example.SocialLoginPractice.service;

import com.example.SocialLoginPractice.converters.ProviderUserRequest;
import com.example.SocialLoginPractice.model.PrincipalUser;
import com.example.SocialLoginPractice.model.ProviderUser;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOidcUserService extends AbstractOAuth2UserService implements OAuth2UserService<OidcUserRequest , OidcUser> {

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        ClientRegistration clientRegistration = userRequest.getClientRegistration();
        OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService = new OidcUserService();

        // 실제 인가서버와 통신을 하고 사용자 정보를 가져온다.
        // 가져온 정보를 OAuth2User 타입에 저장을 해서 반환을 하는 것
        OidcUser oidcUser = oidcUserService.loadUser(userRequest);
        ProviderUserRequest providerUserRequest = new ProviderUserRequest(clientRegistration,oidcUser);
        ProviderUser providerUser = providerUser(providerUserRequest);
        // 어떤 타입인지 확인한다.
//        ProviderUser providerUser = super.providerUser(clientRegistration, oidcUser);

        // 회원가입 (DB 저장)
        super.register(providerUser , userRequest);
        return new PrincipalUser(providerUser);
    }
}
