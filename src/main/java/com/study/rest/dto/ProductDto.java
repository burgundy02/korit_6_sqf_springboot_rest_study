package com.study.rest.dto;

import com.study.rest.entity.Product;
import lombok.Builder;
import lombok.Data;

public class ProductDto {

    @Data
    public static class Register {   // 내부 클래스
        private String productName;
        private int price;
        private int sizeId;
        private int colorId;

        public Product toEntity() {
            return Product.builder()
                    .productName(productName)  // this.productName ==
                    .price(price)
                    .sizeId(sizeId)
                    .colorId(colorId)
                    .build();
        }
    }
}
