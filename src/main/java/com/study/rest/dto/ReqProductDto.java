package com.study.rest.dto;

import lombok.Data;

@Data    // getter, setter로 값 주입
public class ReqProductDto {
    private String productName;
    private int price;
    private int sizeId;
    private int colorId;
}
