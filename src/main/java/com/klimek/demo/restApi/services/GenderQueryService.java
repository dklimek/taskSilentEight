package com.klimek.demo.restApi.services;

import com.klimek.demo.restApi.dto.GenderDTO;
import com.klimek.demo.restApi.dto.TokenDTO;

import java.util.List;

public interface GenderQueryService {

    List<TokenDTO> getAllTokenList();

    GenderDTO getGenderByVariantAndInputData(String variant, String inputData);

}

