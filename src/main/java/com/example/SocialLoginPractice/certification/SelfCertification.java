package com.example.SocialLoginPractice.certification;

import com.example.SocialLoginPractice.model.ProviderUser;
import com.example.SocialLoginPractice.model.user.User;
import com.example.SocialLoginPractice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SelfCertification {

    private final UserRepository userRepository;
    public void checkCertification(ProviderUser providerUser) {
        User user = userRepository.findByUsername(providerUser.getId());
//        if(user != null) {
        boolean bool = providerUser.getProvider().equals("none") || providerUser.getProvider().equals("naver");
        providerUser.isCertificated(bool);
//        }
    }
    public void certificate(ProviderUser providerUser) {

    }
}
