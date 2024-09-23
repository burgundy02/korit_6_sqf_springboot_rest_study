package com.study.rest.repository;

import com.study.rest.entity.Computer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

// db테이블만들고나서 여기

// mapper는 무조건 매개변수를 Entity로 받아야 됨
@Mapper
public interface ComputerMapper {
    // 기능별로 메소드를 다 다르게 만듦(추가, 조회, 수정, 삭제)

    // 레포지토리에서 mapper에서 save만들고 나서 -> xml에서 save insert into 하기
    // 처음에 .xml이랑 연결하면 밑줄뜨고 거기에 어쩌고를 누르면 .xml에 자동으로 interpace를 구현할 수 있는 코드가 만들어짐
    int save(Computer computer);   // save 메소드에 매개변수로 Entity 객체를 받음(이 과정은 service에서 함ㅠㅠ)

    // 1.리턴타입이 Computer computer Entity 객체를 담을 수 있는 리스트이다.
    List<Computer> findComputerByCompanyAndCpu(Computer computer);  // 다건조회

    Computer findComputerById(int id);  // 단건조회

    int delete(int id); // 삭제

    int update(Computer computer);  // 수정


}
