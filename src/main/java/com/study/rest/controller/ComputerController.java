package com.study.rest.controller;

import com.study.rest.dto.ReqGetListDto;
import com.study.rest.dto.ReqRegisterComputerDto;
import com.study.rest.dto.ReqUpdateComputerDto;
import com.study.rest.service.ComputerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * post가 추가라는 건 그렇게 정해져 있는 게 아니라 그렇게 만들어야 함
 *
 * <parameterType>
 *  파라미터타입은 매개변수 타입인 것 같다.
 *  delete메소드는 매개변수로 타입이int인 todo_id를 받음, 근데 .xml에서는 파라미터 타입을 Entity로 잡았음
 *
 * <Entity는 하나만 만들어서 씀>
 *
 * <Dto 만들기>
 * 추가 : req(요청) 1개
 * 조회 : req, resp 2개
 * 수정 : req 1개
 * 삭제 : 안만듦
 *
 * <req, resp>
 * req : 프런트에서 받을 떄
 * resp : 프런트에 줄 때
 *
 * <쿼리문에서>
 * 조회 : resultType / 추가, 수정, 삭제 : parameterType
 *
 * CRUD 순서
 * <추가>
 * 순서. 프론트에서 추가 요청이 들어옴 -> 이걸 Post에서 받고싶다면 프론트에서 그렇게 만들어야 함 -> 백에서 프론트의 요청을 받음 -> 그걸 controller
 * 매개변수에서 Dto형태로 받음 -> Dto만들기(프론트에서 준 데이터를 Dto형태로 백이 받으려고 만듦) -> DB테이블 만들기("아예 처음에", Mapper랑 연결할 DB만듦)
 * -> repository에 Mapper인터페이스 만들기 (DB랑 연결하려고) -> 그러면 resources의 mappers에 .xml파일을 만들어 줘야 함
 * -> .xml에서 Mapper랑 둘이 연결하기 -> .xml에서 쿼리문 작성하기 -> service 폴더에서 클래스 만든다.(메소드가 많아지면 인터페이스를 만들어서 거기 다 선언만
 * 해놓고 클래스에서 implements해서 구현) -> 그 클래스에서 Dto를 Entity로 바꾸고 Mapper의 save 메소드한테 매개변수로 준다. ->
 * .xml의 values에 Entity로 바꾼 객체들이 들어감(왜냐면 Mapper라는 인터페이스를 구현한게 .xml이고, 그래서 Mapper랑 .xml은 연결 돼 있고
 * int save라는 메소드를 DB형태로 구현한것임 그래서 그 매개변수의 객체에 있는 변수의 값이 values의 각각의 표현식 안에 들어가는 것이다.
 * -> DB에 가서 쿼리문 실행됨(테이블안에 값이들어감) -> DB에서 Mapper매소드에 성공횟수를 리턴한다. -> Mapper는 service에 리턴 -> Service는
 * Controller에 리턴 -> Controller의 리턴값은 프론트로 간다 => 이 과정이 프론트의 post요청에 대한 응답이다.
 *
 *
 * <삭제>
 * 순서. 프론트에서 삭제 요청이 들어옴 -> 매개변수로 삭제할 ID를 받음 -> (Dto, Entity안 씀) -> repository에 Mapper인터페이스 만들기
 * -> 그러면 resources의 mappers에 .xml파일을 만들어 줘야 함 -> .xml에서 Mapper랑 둘이 연결하기 -> .xml에서 쿼리문 작성하기
 * -> service 폴더에서 클래스 만든다.(메소드가 많아지면 인터페이스를 만들어서 거기 다 선언만해놓고 클래스에서 implements해서 구현)
 * -> service에서 메소드 구현(메소드 매개변수로 controller의 computerId를 받아옴) -> 구현한 메소드의 리턴값을 deleteComputer를 호출한
 * Controller에서 호출했으니까 Controller에 리턴값 0 또는 1이 body(computerService.deleteComputer(computerId));에 들어감
 * -> 그러면 0 또는 1 이 리턴값이 프론트로 간다.
 *
 * => .xml의 쿼리문으로 DB로 가서 요청값과 DB값을 비교 후 삭제 성공 혹은 실패 여부(0 또는 1)를 리턴해야 한다. 그래서 메소드 타고타고 할 때
 * 리턴 타입을 int로 잡은 것임. 그래서 최종적으로 삭제부분에서는 프론트에 삭제 성공 혹은 실패 여부(성공은 1, 실패는 0)를 준다.
 *
 *
 * <수정>
 *
 *
 *
 *
 * - 서비스에서 메퍼로 데이터를 전달할 때 dto를 entity로바꿔야 한다.
 * 그 때 dto 클래스 안에 toEntity메소드를 만들어야 한다.(그러면 dto.toEntity()로 entity로 바뀐 값을 얻을 수 있다.)
 */

@Slf4j  // log.info() 쓸 수 있게
@CrossOrigin // 프,백 오류잡기
@RestController // 프론트에 데이터만 리턴하는 컨트롤러
@RequestMapping("/api/v1")  // 이거를 붙인 클래스 안에 저 url이 앞에 생략 돼 있다.
public class ComputerController {

    @Autowired   // 객체생성 안하고 선언만 함
    private ComputerServiceImpl computerService;    // Controller에 Service객체를 호출한것임(service에 적어놓은 의존관계 참고)

    //추가
    @PostMapping("/computer")    // @RequestBody : json형태객체 받을 때 사용 => 프론트에서 Dto로 받음
    public ResponseEntity<?> registerApi(@RequestBody ReqRegisterComputerDto reqDto) {
        log.info("{}", reqDto); // 옵션(데이터 잘 넘어왔는지 확인용)

        // controller에서 service 메소드를 호출함
        return ResponseEntity.ok().body(computerService.registerComputer(reqDto)); // service는 controller에서 호출 되니까
    }                                                                           // 이 리턴값도 0 또는 1
                                                                                // 결국은 프론트에 0 또는 1이라는 데이터를
                                                                                // 응답해 줘야 되기 때문에 restController야 됨
    // 수정
    @PutMapping("/computer/{computerId}")
    public ResponseEntity<?> modifyApi(@PathVariable int computerId, @RequestBody ReqUpdateComputerDto reqDto) {
        return ResponseEntity.ok().body(computerService.updateComputer(reqDto));
    }

    // 다건 조회(computer"s")
    @GetMapping("/computers")
    public ResponseEntity<?> getListApi(ReqGetListDto reqDto) {
        log.info("{}", reqDto);
        return ResponseEntity.ok().body(computerService.getComputerList(reqDto));
    }

    // 단건 조회
    // http://localhost:8080/api/v1/computer/3
    @GetMapping("/computer/{computerId}")
    public ResponseEntity<?> getApi(@PathVariable int computerId) {
        log.info("{}", computerId);
        return ResponseEntity.ok().body(computerService.getComputer(computerId));
    }

    // 삭제
    @DeleteMapping("/computer/{computerId}") // @PathVariable: 삭제할 해당 ID를 가지고 있어야 DB에 접근가능해서 씀, 필요한 아이디를 들고옴
    public ResponseEntity<?> removeApi(@PathVariable int computerId) {
        return ResponseEntity.ok().body(computerService.deleteComputer(computerId));
    }
}