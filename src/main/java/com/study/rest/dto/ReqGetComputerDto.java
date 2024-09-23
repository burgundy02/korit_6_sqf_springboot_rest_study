package com.study.rest.dto;

import lombok.Builder;

// 요청마다 Dto 만들면 된다
@Builder
@Deprecated
public class ReqGetComputerDto {
    private int computerId;
    private String company;
    private  String cpu;
    private int ram;
    private int ssd;
}
