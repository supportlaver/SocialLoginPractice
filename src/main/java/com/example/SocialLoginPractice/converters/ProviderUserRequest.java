package com.example.SocialLoginPractice.converters;

import com.example.SocialLoginPractice.model.user.User;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.user.OAuth2User;

// 이 클래스가 Lombok 처럼 @Getter , @Setter 들을 만들어준다.
// 이 자체는 불변객체이기 때문에 상속도 되지 않는다.
// 그래서 멀티 쓰레드 환경에서 안전한 객체가 될 수 있다.
public record ProviderUserRequest(ClientRegistration clientRegistration , OAuth2User oAuth2User , User user) {

    // Social 인증 방식 생성자
    public ProviderUserRequest(ClientRegistration clientRegistration , OAuth2User oAuth2User) {
        this(clientRegistration,oAuth2User,null);
    }

    // Form 인증 방식 생성자
    public ProviderUserRequest(User user) {
        this(null,null,user);
    }
}
