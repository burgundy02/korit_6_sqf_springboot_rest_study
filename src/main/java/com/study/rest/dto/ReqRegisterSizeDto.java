package com.study.rest.dto;

import com.study.rest.entity.Size;
import lombok.Data;

@Data
public class ReqRegisterSizeDto {
    private String sizeName;

    public Size toEntity() {
        return Size.builder()
                .sizeName(sizeName)
                .build();
    }
}
