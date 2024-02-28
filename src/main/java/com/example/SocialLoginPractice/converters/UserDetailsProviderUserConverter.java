package com.example.SocialLoginPractice.converters;

import com.example.SocialLoginPractice.OAuth2Utils;
import com.example.SocialLoginPractice.enums.OAuth2Config;
import com.example.SocialLoginPractice.model.ProviderUser;
import com.example.SocialLoginPractice.model.social.NaverUser;
import com.example.SocialLoginPractice.model.user.FormUser;
import com.example.SocialLoginPractice.model.user.User;

public class UserDetailsProviderUserConverter implements ProviderUserConverter<ProviderUserRequest, ProviderUser> {
    @Override
    public ProviderUser converter(ProviderUserRequest providerUserRequest) {

        if (providerUserRequest.user() == null) {
            return null;
        }

        User user = providerUserRequest.user();
        return FormUser.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .authorities(user.getAuthorities())
                .provider("none")
                .build();
    }
}
