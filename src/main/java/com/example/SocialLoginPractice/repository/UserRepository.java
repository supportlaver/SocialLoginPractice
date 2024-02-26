package com.example.SocialLoginPractice.repository;

import com.example.SocialLoginPractice.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository {

    private Map<String, Object> users = new HashMap<>();

    public void register(User user) {
        // 이미 가입된 회원
        if (users.containsKey(user.getUsername())) {
            return;
        }
        users.put(user.getUsername() , user);
    }

    public User findByUsername(String username) {
        if (users.containsKey("username")){
            return (User) users.get("username");
        }
        return null;
    }
}
