package com.example.SocialLoginPractice.service;

import com.example.SocialLoginPractice.converters.ProviderUserRequest;
import com.example.SocialLoginPractice.model.PrincipalUser;
import com.example.SocialLoginPractice.model.ProviderUser;
import com.example.SocialLoginPractice.model.user.User;
import com.example.SocialLoginPractice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService extends AbstractOAuth2UserService implements UserDetailsService {

    private final UserRepository userRepository;

    // OAuth2 는 OAuth2User 타입
    // Form Login 은 UserDetails 타입
    // 이 타입을 맞춰줘야 하기 때문에 Custom 한 타입을 만들어야 한다. (PrincipalUser)

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user==null) {
            user = User.builder()
                    .id("1")
                    .username("user1")
                    .password("{noop}1234")
                    .authorities(AuthorityUtils.createAuthorityList("ROLE_USER"))
                    .email("user@a.com")
                    .build();
        }

        ProviderUserRequest providerUserRequest = new ProviderUserRequest(user);
        ProviderUser providerUser = providerUser(providerUserRequest);

        return new PrincipalUser(providerUser);
    }
}
