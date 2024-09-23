package com.study.rest.di;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


// bean == Component (ioc컨테이너에 생성되어진 객체)
@Component  // ioc에 등록
public class D {

    public void dCall() {
        System.out.println("D 객체에서 메소드 호출");

    }
}
