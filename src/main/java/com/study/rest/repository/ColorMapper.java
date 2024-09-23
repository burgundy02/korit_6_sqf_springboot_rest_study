package com.study.rest.repository;

import com.study.rest.entity.Color;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ColorMapper {
    // save : DB에 insert하는 것 / 얘는 추가,수정,삭제의 성공 갯수를 리턴 받는다(그래서 리턴타입 int)

    // 5-1 : 여기서 인터페이스 선언     // 5-2: xml에서 구현
    int save(Color color);   // 얘는 mapper니까 service한테 entity형태로 객체를 받으니까 여기 매개변수로 그걸 받아옴
    List<Color> findAll();   // 리턴타입 List / 이 인터페이스의 메소드가 기준이 돼서 이 대로 실행됨 => 여기선 다건 조회가 된다.(List라서)
}                           // 이 메소드를 호출하면 xml실행 -> 쿼리 결과 실행(mybatis가 알아서함)
