package com.klimek.demo.restApi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public class TokenDTO {

    private Long id;
    private String name;
    private String gender;


}
