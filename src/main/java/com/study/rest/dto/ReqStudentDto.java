package com.study.rest.dto;

import lombok.Data;
import lombok.ToString;

@Data // ToString,setter(값을 set할 수 있게)  / @RequestBody, @Data있어야 JSON으로 받을 수 있다.
// 프런트에서 날려주는 형태와 백에서 받는 형태가 같아야 함
public class ReqStudentDto {
    private String schoolName;          // dto객체는 무조건 보내는 형태 그대로여야 함
    private String department;
    private String grade;
    private String name;
}

