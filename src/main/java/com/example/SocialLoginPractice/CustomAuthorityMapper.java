package com.example.SocialLoginPractice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;

import java.util.Collection;
import java.util.HashSet;

@Slf4j
public class CustomAuthorityMapper implements GrantedAuthoritiesMapper {

    // 기본 prefix
    private String prefix = "ROLE_";

    // SimpleAuthorityMapper 에서 가져옴
    @Override
    public Collection<? extends GrantedAuthority> mapAuthorities(Collection<? extends GrantedAuthority> authorities) {

        HashSet<GrantedAuthority> mapped = new HashSet<>(authorities.size());
        for (GrantedAuthority authority : authorities) {
            mapped.add(mapAuthority(authority.getAuthority()));
        }
        return mapped;
    }

    private GrantedAuthority mapAuthority(String name) {

        log.info("name1 = {}",name);
        // 최종적으로 필터링한 권한 정보를 add 시킨다.
        // http://google.com/~~~/abc.email <- 구글은 이런식으로 넘어온다.

        // . 이 있을때
        // 현재 점 이후의 나머지 단어를 가져온다.
        // 예를 들어 http://google.com/~~/abc.email
        // 마지막 . 이후에 있는 단어를 가져오기 위한 로직
        // 그렇게 추출한 scope 에 SCOPE_ 를 붙혀줘서 통일시킨다.
        if (name.lastIndexOf(".") > 0) {
            int index = name.lastIndexOf(".");
            name = "SCOPE_" + name.substring(index + 1);
        }
        log.info("name2 = {}",name);

        // prefix 가 존재하고 name 에 있는 것이 prefix 로 시작하지 않는다면
        // prefix 를 더해줘서 붙혀준다.
        if (prefix.length() > 0 && !name.startsWith(prefix)) {
            log.info("name3 = {}",name);
            name = prefix + name;
        }

        log.info("name4 = {}",name);
        // 딱 email 만 추출해서 name 으로 넣고자 하는 것
        return new SimpleGrantedAuthority(name);
    }
}
