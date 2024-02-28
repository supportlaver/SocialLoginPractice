package com.example.SocialLoginPractice.converters;

import com.example.SocialLoginPractice.OAuth2Utils;
import com.example.SocialLoginPractice.enums.OAuth2Config;
import com.example.SocialLoginPractice.model.ProviderUser;
import com.example.SocialLoginPractice.model.social.GoogleUser;
import com.example.SocialLoginPractice.model.social.NaverUser;

public class OAuth2NaverProviderUserConverter implements ProviderUserConverter<ProviderUserRequest, ProviderUser> {
    @Override
    public ProviderUser converter(ProviderUserRequest providerUserRequest) {

        if (!providerUserRequest.clientRegistration().getRegistrationId()
                .equals(OAuth2Config.SocialType.NAVER.getSocialName())){
            return null;
        }

        return new NaverUser(OAuth2Utils.getSubAttributes(providerUserRequest.oAuth2User(),"response"),
                providerUserRequest.oAuth2User(), providerUserRequest.clientRegistration());
    }
}
