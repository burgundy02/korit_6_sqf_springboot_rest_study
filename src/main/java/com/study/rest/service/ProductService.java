package com.study.rest.service;

import com.study.rest.dto.*;
import com.study.rest.entity.Color;
import com.study.rest.entity.Size;

import java.util.List;

// 3-1                    // 서비스 호출은 컨트롤러에서, 서비스는 dto들고 있음
public interface ProductService {
    List<SizeDto.Info> getSizeListAll();
    List<Color> getColorListAll();
    CommonResponseDto registerProduct(ProductDto.Register register);
    CommonResponseDto registerSize(ReqRegisterSizeDto reqRegisterSizeDto);
    CommonResponseDto registerColor(ReqRegisterColorDto reqRegisterColorDto);  // dto로 받고 준다 / 여기서 인터페이스 선언만 함
    // return
}