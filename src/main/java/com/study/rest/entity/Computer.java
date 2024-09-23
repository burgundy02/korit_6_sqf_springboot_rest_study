package com.study.rest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 레포지토리에서 mapper에서 save만들고 나서 여기
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Computer {
    private int computerId;
    private String company;
    private String cpu;
    private int ram;
    private int ssd;
}
