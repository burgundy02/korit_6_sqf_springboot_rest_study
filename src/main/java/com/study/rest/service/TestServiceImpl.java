package com.study.rest.service;

import org.springframework.stereotype.Service;

// 얘도 컴포넌트
@Service("ts")   // 괄호 컴포넌트 이름
public class TestServiceImpl implements TestService{

    @Override
    public void test() {
        System.out.println("기존 단위 테스트 실행");
    }
}
