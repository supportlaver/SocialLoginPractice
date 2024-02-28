package com.example.SocialLoginPractice.service;

import com.example.SocialLoginPractice.converters.ProviderUserConverter;
import com.example.SocialLoginPractice.converters.ProviderUserRequest;
import com.example.SocialLoginPractice.model.*;
import com.example.SocialLoginPractice.model.user.User;
import com.example.SocialLoginPractice.repository.UserRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@Getter
public abstract class AbstractOAuth2UserService {

    @Autowired
    private UserRepository userRepository; //user 가 존재하면 service 를 통해서 repo 로 가는 식으로 구성

    @Autowired
    private UserService userService;

    @Autowired
    private ProviderUserConverter<ProviderUserRequest , ProviderUser> providerUserConverter;


    // 사용자를 등록하는 Method
    protected void register(ProviderUser providerUser, OAuth2UserRequest userRequest) {

        User user = userRepository.findByUsername(providerUser.getUsername());

        if (user == null) {
            String registrationId = userRequest.getClientRegistration().getRegistrationId();
            userService.register(registrationId , providerUser);
        } else {
            // 이미 존재하는 회원
            System.out.println("user = " + user);
        }
    }

    // 어떤 사용자 (3가지 중에서) 로 return 할건지
    public ProviderUser providerUser(ProviderUserRequest providerUserRequest) {
        ProviderUser providerUser = providerUserConverter.converter(providerUserRequest);
        return providerUser;
    }






}
