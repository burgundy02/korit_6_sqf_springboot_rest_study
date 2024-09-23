package com.study.rest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
    private int productId;
    private String productName;
    private int price;
    private int sizeId;
    private int colorId;

    private Size size;
    private Color color;
}
