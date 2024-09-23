package com.study.rest.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Car {
    private String model;
    private String color;
}
