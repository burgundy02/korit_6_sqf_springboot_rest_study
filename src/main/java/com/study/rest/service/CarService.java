package com.study.rest.service;

import com.study.rest.entity.Car;
import com.study.rest.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor  // 필수 어쩌고 생성자 / 이거 한줄 쓰고 종속성주입 final로 하면 코드가 깔끔해짐(정석은 @Autowired)
public class CarService {

    /**
     * final - > 무조건 한 번은 초기화를 해줘야 한다.
     * => final을 달면 초기화를 null이라도 처음애 해줘야 함 / => 외부에서 주입되어지면 걔가 초기값으로 들어온다.
     *
     * private final CarRepository;  // 상수는 직접 초기화 해줘야 함(강제성 부여)
     * // 이 부품이 무조건 있어야 프로그램이 동작한다.(주소값이 변하면 안됨: 싱글톤이라서)
     * // 생성자에서 무조건 초기화 해줘야 함
     *
     * public Carservice(CarRepository carRepository) {
     *         this.carRepository = carRepository;
     */

    // 종속성 주입
    private final CarRepository carRepository;

    public Car getCar(String model) {
        return carRepository.findCarByModel(model);  // 이 리턴값이 결국 CarRepository에서 리턴 받은 객체
    }
}
