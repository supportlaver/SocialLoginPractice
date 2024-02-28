package com.example.SocialLoginPractice.converters;

public interface ProviderUserConverter<T,R> { // 입력 타입 , 반환 타입
    R converter(T t);
}
