package com.study.rest.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CommonResponseDto {
    private String message;
    private int count;
    private boolean success;

    // 성공, 실패 여부를 알기 위해서 하는 거임
    public static CommonResponseDto ofDefault(int count) {
        // 빌더로 CommonResponseDto 객체 만듦(매개변수로 받은 걸 활용해서 객체 만들어서 리턴)
        return CommonResponseDto.builder()
                .message(count > 0 ? "Successful" : "Failed")           // 0보다 크면 "성공", 아니면 "실패"
                .count(count)
                .success(count > 0)
                .build();
    }
}