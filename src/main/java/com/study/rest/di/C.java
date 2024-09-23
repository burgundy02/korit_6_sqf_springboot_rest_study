package com.study.rest.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
public class C {

    // 종속적주입 무조건 할 필요 없음(생성을 무조건 할 필요 없다)
    @Autowired(required = false)
    private D d;

    public void cCall() {
        System.out.println("C 객체에서 메소드 호출");
        d.dCall();
    }

    @ResponseBody
    @GetMapping("/ioc")
    public Object ioc() {
        return null;
    }
}
