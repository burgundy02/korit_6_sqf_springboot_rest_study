package com.study.rest.controller;

import com.study.rest.entity.Car;
import com.study.rest.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CarController {

    // Autowired   / 생성자가 두 개면 Allarge, Noarge 중 Noarge가 우선순위
    @Autowired
    private CarService carService;

    @ResponseBody // 객체를 그대로 화면에 띄움 => JSON형태로 띄어짐(Spring 자체에서 JSON형태로 띄움)
    @GetMapping("/car")
    public Car getCar(@RequestParam String model) {   // String model: 주소창의 변수라고 생각하고 model은 ~라고 적음 => car?
                                                      // 사용자가 k5라고 구체적으로 적어야함(/car?model=k5)

           return carService.getCar(model); // 사용자가 입력한 model(k5)을 넘겨줘서 매개변수로 들어옴
    }
}

/**
 * 요청 Get - /car?model=k5  => 화면에 JSON형태로 띄움
 *
 * CarEntity                -> model, color
 *
 * CarRepository            -> Car findCarByModel(String model)
 *         -> List<Car>
 *             -> index0: Car(model: "쏘나타", color: "화이트")
 *             -> index1: Car(model: "k5", color: "블랙")
 *
 * CarService           -> Car getCar(String model)
 *
 * CarController        -> Car getCar(@RequestParam String model)
 *
 * 의존관계 :
 * CarController > CarService > CarRepository(얘가 시작): 의존성이 없는 것 부터 만든다.
 */
