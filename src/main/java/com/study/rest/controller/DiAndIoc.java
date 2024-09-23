package com.study.rest.controller;

import com.study.rest.di.A;
import com.study.rest.di.B;
import com.study.rest.di.C;
import com.study.rest.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

// 처음에 클라이언트가 요청(엔터)을 하면 요청한 주소를 가지고 있는지 컨트롤러가 확인하고 걔한테 찾아감
@Controller
public class DiAndIoc {

    // @Autowired는 주로 이렇게 씀(메소드 안에다가 쓰고 하는 건 필요시에만)
    @Autowired  // 객체생성 안해도 자동으로 객체를 만들어 줌
    private C c;

    @Autowired
    @Qualifier("ts")
    private TestService testService1;

    @Autowired
    @Qualifier("newTestServiceImpl")
    private TestService testService2;

    @ResponseBody
    @GetMapping("/di")
    public Object di() {
        // A가 B를 의존하는 관계 / 결합도는 낮은 상태
        B b = new B();
        A a = new A(b);
        a.aCall();
        return null;
    }

    @ResponseBody  // 리턴값이 그대로 화면에 출력됨
    @GetMapping("/ioc") // 주소를 만듦
    public Object ioc() {

        c.cCall();
        return null;
    }

    @ResponseBody
    @GetMapping("/test")
    public Object startTest(@RequestParam String type) {
        if("old".equals(type)) {
            testService1.test();
        }else {
            testService2.test();
        }
        return null;
    }
}


