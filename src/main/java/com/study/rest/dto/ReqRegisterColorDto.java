package com.study.rest.dto;

import com.study.rest.entity.Color;
import lombok.Data;

// 2      // 3-1 : ProductService에서 인터페이스 선언, 3-2 : ProductServiceImpl에서 구현
// dto를 entity로 바꾸는 걸 메소드로 따로 빼놓음
@Data
public class ReqRegisterColorDto {
    private String colorName;

//    // 4 : toEntity만들기          // 5: serivceImpl -> Colormapper
//    public Color toEntity() {
//
//    }
}
