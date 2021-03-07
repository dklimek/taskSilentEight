package com.klimek.demo.restApi.services;

import com.klimek.demo.restApi.dto.GenderDTO;
import com.klimek.demo.restApi.dto.TokenDTO;
import com.klimek.demo.restApi.repositories.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenderQueryServiceImpl implements GenderQueryService {

    @Autowired
    private TokenRepository tokenRepository;

    public GenderQueryServiceImpl(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public List<TokenDTO> getAllTokenList() {
        List<TokenDTO> genderList = new ArrayList<>();

        return genderList;
    }

    @Override
    public GenderDTO getGenderByVariantAndInputData(String variant, String inputData) {

        return new GenderDTO();

    }

}