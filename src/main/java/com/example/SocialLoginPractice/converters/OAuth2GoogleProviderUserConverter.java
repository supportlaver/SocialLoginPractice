package com.example.SocialLoginPractice.converters;

import com.example.SocialLoginPractice.OAuth2Utils;
import com.example.SocialLoginPractice.enums.OAuth2Config;
import com.example.SocialLoginPractice.model.ProviderUser;
import com.example.SocialLoginPractice.model.social.GoogleUser;

public class OAuth2GoogleProviderUserConverter implements ProviderUserConverter<ProviderUserRequest, ProviderUser> {

    // 현재 여기에 Google , Naver , Kakao 인증 모두 올 수 있기 때문에 Google 이 아닌 경우에는 return 해서 실행이 되지 않도록 해야한다.
    @Override
    public ProviderUser converter(ProviderUserRequest providerUserRequest) {

        if (!providerUserRequest.clientRegistration().getRegistrationId()
                .equals(OAuth2Config.SocialType.GOOGLE.getSocialName())){
            return null;
        }

        return new GoogleUser(OAuth2Utils.getMainAttributes(providerUserRequest.oAuth2User()),
                providerUserRequest.oAuth2User(), providerUserRequest.clientRegistration());
    }
}
