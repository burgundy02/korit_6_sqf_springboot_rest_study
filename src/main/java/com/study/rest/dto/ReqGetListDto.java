package com.study.rest.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ReqGetListDto {
    private String company;
    private String cpu;
}
