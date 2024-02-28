package com.example.SocialLoginPractice.service;

import com.example.SocialLoginPractice.model.ProviderUser;
import com.example.SocialLoginPractice.model.user.User;
import com.example.SocialLoginPractice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void register(String registrationId , ProviderUser providerUser) {

        User user = User.builder()
                .registrationId(registrationId)
                .id(providerUser.getId())
                .username(providerUser.getUsername())
                .email(providerUser.getEmail())
                .authorities(providerUser.getAuthorities())
                .build();

        userRepository.register(user);

    }
}
