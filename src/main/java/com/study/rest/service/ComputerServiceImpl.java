package com.study.rest.service;

import com.study.rest.dto.*;
import com.study.rest.entity.Computer;
import com.study.rest.repository.ComputerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 의존관계
 * Controller > Service > Repository(Mapper)   => Controller에 Service객체를 생성하고 > Service에 Mapper객체를 생성한다.
 *           dto       entity       => 둘둘씩 저걸로 주고받아야 함
 */

// 서비스로 사용할 클래스는 서비스 컨트롤러를 달아준다.

// Controller는 무조건 Dto, Mapper는 무조건 Entity, 그렇게 되려면 중간에서 service가 바꿔줘야 한다 => 이게 service 존재의 이유
                                                                                    // => Dto, Entity바꾸는 게 필요없으면
                                                                                    // 매개변수 넘겨 주는 것만 하면 됨

// 서비스는 Dto(이 Dto는 Controller한테서 받은 Dto)를 Entiy로바꿔서 Mapper한테 주는게 목적이다.
// (Mapper는 매개변수를 Entity로 무조건 받게 해야 됨, "Mapper는 Entity다")
@Service
public class ComputerServiceImpl {

    // 추가
    @Autowired
    private ComputerMapper computerMapper;  // Service에 Mapper객체를 생성한다

    // 메소드만들기(Dto를 Entity로 바꿔주고 Entity를 Dto로 바꿔주는 메소드임)
    public int registerComputer(ReqRegisterComputerDto reqDto) {           // 매개변수는 controller에서 받은 Dto객체임)
        Computer computer = Computer.builder()                             // controller에서 service 메소드를 호출하는데
                .company(reqDto.getCompany())                              // controller의 매개변수를 여기의 매개변수로 받음
                .cpu(reqDto.getCpu())                                      // => 프론트에서 받은 데이터를 여기의 매개변수로도 받음
                .ram(reqDto.getRam())
                .ssd(reqDto.getSsd())
                .build();           // 빌더를 씀으로써 Dto를 Entity로 바꾼다.

        // 빌더를 통해 Entity로 바꾼것을 Mapper의 save 메소드한테 매개변수로 줌
        return computerMapper.save(computer);  // 위에서 @Autowired해서 저렇게 한 이유는 이렇게 쓰기 위해서이다 :
                                               // insert된 성공 횟수를 들고오려고(성공은1, 못하면0) => 결국은 프론트가 insert 성공 회수에
                                               // 따라 화면에 띄울걸 만들기 위해서 성공 횟수를 리턴해 준다.
    }                                          // computerMapper.save(computer) => 이거 자체가 0 또는 1이다.

    // 다건 조회
    public List<RespGetListDto> getComputerList(ReqGetListDto reqDto) {
        List<RespGetListDto> respDtos = new ArrayList<>();

        Computer computer = Computer.builder()
                .company(reqDto.getCompany())
                .cpu(reqDto.getCpu())
                .build();

        List<Computer> computers = computerMapper.findComputerByCompanyAndCpu(computer);

        for(Computer com : computers) {
            RespGetListDto dto = RespGetListDto.builder()
                    .computerId(com.getComputerId())
                    .company(com.getCompany())
                    .build();

            respDtos.add(dto);
        }

        return respDtos;
    }

    public List<RespGetListDto> getComputerList2(ReqGetListDto reqDto) {
        Computer computer = Computer.builder()
                .company(reqDto.getCompany())
                .cpu(reqDto.getCpu())
                .build();

        List<Computer> computers = computerMapper.findComputerByCompanyAndCpu(computer);

        return computers.stream().map(com -> RespGetListDto.builder()
                .computerId(com.getComputerId())
                .company(com.getCompany())
                .build()
        ).collect(Collectors.toList());
    }

    // 단건 조회
    public RespGetComputerDto getComputer(int computerId) {
        Computer computer = computerMapper.findComputerById(computerId);
        return RespGetComputerDto.builder()
                .computerId(computer.getComputerId())
                .company(computer.getCompany())
                .cpu(computer.getCpu())
                .ram(computer.getRam())
                .ssd(computer.getSsd())
                .build();
    }

    // 삭제
    public int deleteComputer(int computerId) {
        // computerMapper.delete(computerId) : computerMapper안에 있는 delete 메소드를 호출하는 것 -> delete 메소드를 호출하면
        // void가 아니니라int임 -> 성공횟수를 호출한 곳인 여기에서 리턴한다. => delete 메소드를 호출하면

/**
 *
 * // 이 변수도 delete의 리턴값을 담을 것이기 때문에 int로 적음
 * int successCount = computerMapper.delete(computerId); // 매소드를 호출하면 그 매소드의 리턴값이 리턴됨 -> 그러면 computerMapper.delete(computerId);
 * // 얘 자체가 리턴값이 됨 -> 그러면  computerMapper.delete(computerId)얘 자체가 0 또는 1이됨 -> successCount 여기에 0 또는 1이 들어감
 * // -> successCount얘를 리턴함 => deleteComputer의 리턴값은 결국 0 또는 1이다. => computerMapper.delete(computerId);이 매소드의
 * // 리턴값을 return successCount; 얘로 또다시 리턴한다 -> deleteComputer얘를 호출한 자리에
 * return successCount;
 */
// 위 주석처럼 하면 호출할 때마다 새로운 변수를 생성해야 돼서 그렇게 안하고 그냥 retrun에 바로 한것임
       return computerMapper.delete(computerId);
        }

    // 수정
    public int updateComputer(ReqUpdateComputerDto reqDto) {
        Computer computer = Computer.builder()
                .computerId(reqDto.getComputerId())
                .company(reqDto.getCompany())
                .cpu(reqDto.getCpu())
                .ram(reqDto.getRam())
                .ssd(reqDto.getSsd())
                .build();

        return computerMapper.update(computer);
    }
}
