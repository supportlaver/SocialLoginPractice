package com.example.SocialLoginPractice.model.user;

import com.example.SocialLoginPractice.model.ProviderUser;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class FormUser implements ProviderUser {

    private String id;
    private String username;
    private String password;
    private String email;
    private List<? extends GrantedAuthority> authorities;
    private String provider;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getEmail() {
        return email;
    }


    @Override
    public List<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getProvider() {
        return null;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public String getPicture() {
        return null;
    }

    @Override
    public OAuth2User getOAuth2User() {
        return null;
    }

    @Override
    public void isCertificated(boolean isCertificated) {

    }

    @Override
    public boolean isCertificated() {
        return false;
    }
}
