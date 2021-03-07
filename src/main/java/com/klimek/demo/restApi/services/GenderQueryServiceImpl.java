package com.klimek.demo.restApi.services;

import com.klimek.demo.restApi.dto.GenderDTO;
import com.klimek.demo.restApi.dto.TokenDTO;
import com.klimek.demo.restApi.exception.TokenNotfoundException;
import com.klimek.demo.restApi.exception.VariantBadRequestException;
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

        tokenRepository.findAll().forEach(token -> {
            genderList.add(new TokenDTO(token.getId(), token.getName(), token.getGender()));
        });

        return genderList;
    }

    @Override
    public GenderDTO getGenderByVariantAndInputData(String variant, String inputData) {

        String[] tokenList = inputData.trim().split(" ");

        if (variant.equals("token")) {
            if (tokenRepository.findTopByName(tokenList[0]).isPresent()) {
                return new GenderDTO(variant, inputData, tokenRepository.findTopByName(tokenList[0]).get().getGender());
            } else {
                throw new TokenNotfoundException();
            }
        } else if (variant.equals("string")) {
            int result = 0;
            for (String token : tokenList) {

                if (tokenRepository.findTopByName(token).isPresent()) {
                    String tokenGender = tokenRepository.findTopByName(token).get().getGender();
                    result += (tokenGender.contains("M") ? 1 : -1);
                }
            }
            String gender = result > 0 ? "Male" : result < 0 ? "Female" : "INCONCLUSIVE";
            return new GenderDTO(variant, inputData, gender);
        } else {
            throw new VariantBadRequestException();
        }


    }

}