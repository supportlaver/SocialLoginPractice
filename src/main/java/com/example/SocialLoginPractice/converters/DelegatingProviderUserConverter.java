package com.example.SocialLoginPractice.converters;

import com.example.SocialLoginPractice.model.ProviderUser;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// DI 방기 위해서 Component
@Component
public class DelegatingProviderUserConverter implements ProviderUserConverter<ProviderUserRequest , ProviderUser> {

    private List<ProviderUserConverter<ProviderUserRequest , ProviderUser>> converters;

    public DelegatingProviderUserConverter() {
        List<ProviderUserConverter<ProviderUserRequest , ProviderUser>> providerUserConverters =
                Arrays.asList(new OAuth2GoogleProviderUserConverter() ,
                        new OAuth2NaverProviderUserConverter(),
                        new OAuth2KakaoProviderUserConverter(),
                        new UserDetailsProviderUserConverter());

        // 불변객체로 생성
        this.converters = Collections.unmodifiableList(new LinkedList<>(providerUserConverters));
    }

    @Override
    public ProviderUser converter(ProviderUserRequest providerUserRequest) {
        Assert.notNull(providerUserRequest, "providerUserRequest cannot be null");

        for (ProviderUserConverter<ProviderUserRequest, ProviderUser> converter : converters) {
            // 각 구현체에 위임
            ProviderUser providerUser = converter.converter(providerUserRequest);

            if (providerUser != null) {
                return providerUser;
            }
        }
        return null;
    }
}
