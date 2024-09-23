package com.study.rest.repository;

import com.study.rest.entity.Size;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

// mapper 인터페이스 여기서 선언 후 xml에서 구현
@Mapper
public interface SizeMapper {
    int save(Size size);    // 엔티티 객체
    List<Size> findAll();
}
