package com.study.rest.repository;

import com.study.rest.entity.Color;
import com.study.rest.entity.Product;
import com.study.rest.entity.Size;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    int save(Product product);   // 추상메소드
//    insert, update, delete는 리턴 타입이 무조건 int로 고정 / 매개변수로 어떤 객체 insert할지 적어줘야함
//    select만 xml에 resultType 있음
}
