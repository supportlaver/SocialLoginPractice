package com.example.SocialLoginPractice.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
public abstract class OAuth2ProviderUser implements ProviderUser{

    private Map<String , Object> attributes;

    private OAuth2User oAuth2User;
    private ClientRegistration clientRegistration;

    private boolean isCertificated;

    public OAuth2ProviderUser(Map<String, Object> attributes, OAuth2User oAuth2User, ClientRegistration clientRegistration) {
        this.attributes = attributes;
        this.oAuth2User = oAuth2User;
        this.clientRegistration = clientRegistration;
    }

    // 서비스 이름 , 패스워드 , 이메일 , 속성들 , 권한정보들은 어느 서비스에서도 가져올 수 있기 때문에 묶어 놓는다.

    @Override
    public String getProvider() {
        return clientRegistration.getRegistrationId();
    }

    @Override
    public String getPassword() {
        // password 는 그냥 랜던값으로 사용
        return UUID.randomUUID().toString();
    }

    @Override
    public String getEmail() {
        return (String)getAttributes().get("email");
    }

    @Override
    public List<? extends GrantedAuthority> getAuthorities() {
        return oAuth2User.getAuthorities().stream().
                map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public void isCertificated(boolean isCertificated) {
        this.isCertificated = isCertificated;

    }

    @Override
    public boolean isCertificated() {
        return isCertificated;
    }
}
