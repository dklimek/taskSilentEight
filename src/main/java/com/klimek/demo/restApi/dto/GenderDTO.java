package com.klimek.demo.restApi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public class GenderDTO {

    private String variant;
    private String inputData;
    private String gender;

}
