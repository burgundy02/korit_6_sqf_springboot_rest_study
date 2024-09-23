package com.study.rest.controller;

import com.study.rest.dto.*;
import com.study.rest.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

// REST API (view를 리턴하는게 아님)
@Slf4j
@RestController  // 프런트엔드에게 줄 때 view를 주는게 아니라 데이터를 줄려고
// ResponceBody : 이거 안달면 return에 view(html파일) 경로를 달았음, 달면 view경로가 화면에 그대로 뜸 / Controller : (컴포넌트 안에 ctroller, mapper ,sirvice가 들어있음)가 같이 있음
public class BasicController {

//    @Autowired: 자동으로 객체 만들어 주는 거(new 안해도 됨)
    private ProductService productService;

    /**
     * REST API
     * 데이터 통신을 위한 HTTP 요청 방식
     * HTTP(프로토콜)를 조금 더 잘 쓰기 위해서 정의된 개념
     * <p>
     * 1. 요청에 대한 동작은 무조건 메소드에 따라서 정의하자.
     * 메소드
     * - 데이터 추가(등록)는 POST요청으로 하자, 그리고 POST요청은 JSON데이터로 요청하자.
     *
     * - 데이터 조회는 GET 요청으로 하자,
     * 그리고 GET 요청은 QueryString(params)으로 요청하자.
     * 예) 주소?key1=value1&key2=value2   // QueryString
     *
     * - 데이터 수정은 PUT, PATCH 요청으로 하자, JSON으로 요청하자.
     * PUT 요청과 PATCH 요청의 차이점  (put, patch 모두 수정이 일어나게 끔 만들어 줘야 함)
     * put 요청 : 공백 또는 NULL인 데이터도 수정을 함.(공백이나 null도 업뎃함)   // 둘 다 수정인데 put요청일 떄와 patch요청일 떄의 차이점을 사용자가 알 수 있게 만들어야 함
     * PATCH 요청 : 공백 또는 NULL인 데이터는 수정을 하지 않음.(공백이나 null은 업뎃안함) // 사용: 부분 수정이 필요한 경우
     *
     * - 데이터 삭제는 DELETE 요청으로 하자, params로 요청하자.
     * <p>
     * 2. 주소(URL) 요청 메세지(Resource) 자원 작성법
     * - URL은 가능한 동사를 사용하지 않는다.
     * - 계층 구조로 작성한다.
     * 예) /집/주방/가스레인지, /식당/주방/가스레인지
     * <p>
     * - 요청 메소드 마다 작성하는 방법이 다르다.
     * 상품 등록(POST) /product
     * 상품 하나(단건) 조회(GET) /product/1(id 또는 key)
     * 상품 여러 개(다건) 조회(GET) /products(전체), /products?page=1&count=30(한 페이지에 30개씩:1번 페이지에 30씩 들고와라)
     * 상품 수정(PUT) /product/1(id 또는 key)
     * 상품 삭제(DELETE) /product/1(id 또는 key)
     * <p>
     * 3. 주소는 가능한 대문자를 사용하지 않는다.
     */

    // ctroller 메소드의 리턴타입은 ResponseEntity<?>로 한다(뭐가 들어올지 모르니까)
    @CrossOrigin    // 프런트, 백 연결할 때 오류 잡음
    // 이 url로 들어오면 메소드 호출해라
    @PostMapping("/basic/student")   // 계층형, 가능한 동사 안넣음 / CRUD의 동작은 무조건 메소드로 : postMapping은 추가 = 학생을 추가
    // ResponseEntity: 응답에 대한 정보가 들어감  // **데이터가 json형태면 무조건 @RequestBody적어준다(요청데이터이니까 Request)
    public ResponseEntity<?> basicPost(@RequestBody ReqStudentDto reqStudentDto) {   // post 요청을 날리면 이 메소드가 호출됨
        log.info("Student: {}", reqStudentDto);  // 중괄호안에 reqStudentDto가 들어감
        return ResponseEntity.badRequest().body(null);  // 리턴이 null이면 프론트에 요청만 받고 응답을 안하겠다.(log에 띄우는 게 끝)
    }      // ok -> 상태코드 200(서버 정상) , badRequest -> 상태코드 400(오류)

    @CrossOrigin
    @PostMapping("/api/v1/product")          // json데이터 받을 수 있는 dto필요     /  ProductDto.Register: 클래스 타입
    public ResponseEntity<?> registerProduct(@RequestBody ProductDto.Register register) {
        return ResponseEntity.ok().body(productService.registerProduct(register));  // 여기서 호출들이 되고 메소드 연결됨
    }

    @CrossOrigin
    @GetMapping("/api/v1/sizes")
    public ResponseEntity<?> sizeListApi() {
        return ResponseEntity.ok().body(productService.getSizeListAll());
    }

    @CrossOrigin
    @GetMapping("/api/v1/colors")
    public ResponseEntity<?> colorListApi() {
        return ResponseEntity.ok().body(productService.getColorListAll());
    }

    @CrossOrigin
    @PostMapping("/api/v1/size")
    public ResponseEntity<?> registerSizeApi(@RequestBody ReqRegisterSizeDto reqRegisterSizeDto) {
        // 옵션(콘솔 확인용)
        log.info("{}", reqRegisterSizeDto);
        return ResponseEntity.ok().body(productService.registerSize(reqRegisterSizeDto));
    }

// ** 백엔드 1                         // 2번 여기서 이 메소드의 매개변수로 Dto 객체를 받아야 되니까 Dto를 만든다.
    @CrossOrigin
    @PostMapping("/api/v1/color")               // color객체를 dto형태로 받아옴
    public ResponseEntity<?> registerColorApi(@RequestBody ReqRegisterColorDto reqRegisterColorDto) {
        log.info("{}", reqRegisterColorDto);
        return ResponseEntity.ok().body(null);
    }

}

// log.info : syos는 가급적 안쓰는게 좋음

