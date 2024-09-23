//package com.study.rest.service;
//
//import com.study.rest.dto.*;
//import com.study.rest.entity.Color;
//import com.study.rest.entity.Product;
//import com.study.rest.entity.Size;
//import com.study.rest.repository.ColorMapper;
//import com.study.rest.repository.ProductMapper;
//import com.study.rest.repository.SizeMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//// 3-2          // 4 : ReqRegisterColorDto에서 toEntity만들기
//@Service
//public class ProductServiceImpl implements ProductService {
//
//    @Autowired
//    private ProductMapper productMapper;
//    @Autowired
//    private SizeMapper sizeMapper;
//    @Autowired
//    private ColorMapper colorMapper;
//
//    @Override
//    public List<SizeDto.Info> getSizeListAll() {
//        return SizeDto.toList(sizeMapper.findAll());
//    }
//
//    @Override
//    public List<Color> getColorListAll() {
//        return colorMapper.findAll();
//    }
//
//    @Override
//    public CommonResponseDto registerProduct(ProductDto.Register register) {
//        return CommonResponseDto.ofDefault(productMapper.save(register.toEntity()));
//    }
//
//    @Override
//    public CommonResponseDto registerSize(ReqRegisterSizeDto reqRegisterSizeDto) {
////        // dto -> entity로 변환
////        Size size = Size.builder()
////                .sizeName(reqRegisterSizeDto.getSizeName())
////                .build();
////
////        int successCount = sizeMapper.save(size);
////        return CommonResponseDto.ofDefault(successCount);
////    }
////
//            // 성공횟수를 ofDefault한테 매개변수로 넘겨준다.
//
//        // 6 : save 호출
//        return CommonResponseDto.ofDefault(sizeMapper.save(reqRegisterSizeDto.toEntity()));
//
//    }
//
//    // service : 컨트롤러한테서 dto를 받아서(프런트에서 받아온 객체를 dto형태로 컨트롤러가 받아서(컨트롤러가 그 객체를 dto형대로 받음)
//    // service메소드의 매개변수로 준다))
//    // dto를 entity로 바꿔서 mapper한테 줘야 함(mapper한테 주면 mapper가 DB랑 연결해 준다)
//    @Override
//    public CommonResponseDto registerColor(ReqRegisterColorDto reqRegisterColorDto) {
//        return CommonResponseDto.ofDefault();
//    }
//}