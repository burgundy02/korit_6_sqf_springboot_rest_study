package com.study.rest.dto;

import lombok.Data;

// 3 -> vsCode로가서 page마저 작성하기

// 프론트에서 받아올 데이터, 이걸 객체(객체이름이 Dto)로 백이 controller의 매개변수로 받을 것임
@Data
public class ReqRegisterComputerDto {
    private String company;
    private String cpu;
    private int ram;
    private int ssd;
}
