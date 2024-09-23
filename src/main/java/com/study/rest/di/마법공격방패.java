package com.study.rest.di;

import org.springframework.stereotype.Component;

@Component
public class 마법공격방패 implements 방패{

    @Override
    public void 방어() {
        System.out.println("마법공격을 막습니다.");
    }
}
