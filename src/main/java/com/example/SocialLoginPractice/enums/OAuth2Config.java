package com.example.SocialLoginPractice.enums;

public class OAuth2Config {

    public enum SocialType {
        // equals 메서드를 사용해야 하기 때문에 다음과 같이 생성

        GOOGLE("google") , NAVER("naver") , KAKAO("kakao");
        private final String socialName;
        SocialType(String socialName) {
            this.socialName = socialName;
        }

        public String getSocialName() {
            return socialName;
        }
    }
}
