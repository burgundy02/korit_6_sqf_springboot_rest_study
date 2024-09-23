package com.study.rest.dto;

import lombok.Builder;
import lombok.Data;

// 요청마다 Dto 만들면 된다
@Builder
@Data
public class RespGetComputerDto {
    private int computerId;
    private String company;
    private  String cpu;
    private int ram;
    private int ssd;
}
