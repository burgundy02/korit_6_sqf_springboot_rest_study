package com.study.rest.di;

import org.springframework.stereotype.Component;

@Component
// ctrl + i = 오버라이드
public class 총 implements 무기{

    @Override
    public void 공격() {
        System.out.println("총을 쏩니다.");
    }
}
