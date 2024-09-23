package com.study.rest.repository;

import com.study.rest.entity.Car;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CarRepository {

        //  List<타입> listName = new ArrayList<타입(옵션)>();
        //  List.of : new를 안하고 배열에 값을 넣으면서 생성, 단점 : 한 번 값을 넣으면 수정 불가
    private List<Car> carList = List.of(
            Car.builder().model("쏘나타").color("화이트").build(),
            Car.builder().model("k5").color("블랙").build()
    );

    public Car findCarByModel(String model){

        // for문 가능  / stream()으로 filter돌리면 그 조건에 맞는 것들이 새로운 배열에 담김
       return carList.stream()
               .filter(car -> car.getModel().equals(model)) // 입력한 모델이랑 같은 걸 찾음
               .collect(Collectors.toList())    // stream이니까 리스트로 바꿈
               .get(0); // 그 리스트의 0인덱스에 들어있는, 입력한 것과 같은 객체를 리턴
    }
}
