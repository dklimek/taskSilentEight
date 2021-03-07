package com.klimek.demo.restApi.services;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

class ServiceIntegrationTest {


    private TokenRepository repository = Mockito.mock(TokenRepository.class);

    @Test
    void should_return_gender_by_variant_and_inputData() {

        GenderQueryServiceImpl genderQueryService = new GenderQueryServiceImpl(repository);

        Token token = new Token(1L, "Jan", "M");
        GenderDTO expectResponse = new GenderDTO("token", "Jan", "M");
        Mockito.when(repository.findTopByName("Jan")).thenReturn(Optional.of(token));
        GenderDTO actualGenderResponse = genderQueryService.getGenderByVariantAndInputData("token", "Jan");
        Assertions.assertEquals(actualGenderResponse, expectResponse);
    }

    @Test
    void should_find_list_of_gender() {
        GenderQueryServiceImpl genderQueryService = new GenderQueryServiceImpl(repository);
        List<Token> tokenList = new ArrayList<Token>();
        tokenList.add(new Token(1L, "Jan", "M"));
        tokenList.add(new Token(2L, "Maria", "F"));

        List<TokenDTO> expectResponse = new ArrayList<TokenDTO>();
        expectResponse.add(new TokenDTO(1L, "Jan", "M"));
        expectResponse.add(new TokenDTO(2L, "Maria", "F"));

        Mockito.when(repository.findAll()).thenReturn((Iterable) tokenList);
        List<TokenDTO> actualGenderResponse = genderQueryService.getAllTokenList();
        Assertions.assertEquals(actualGenderResponse, expectResponse);
    }


}