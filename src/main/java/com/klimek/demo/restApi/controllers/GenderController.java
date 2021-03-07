package com.klimek.demo.restApi.controllers;

import com.klimek.demo.restApi.dto.GenderDTO;
import com.klimek.demo.restApi.dto.TokenDTO;
import com.klimek.demo.restApi.services.GenderQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping(value = "/api/gender")
public class GenderController {

    @Autowired
    private GenderQueryService genderQueryService;

    @GetMapping(value = "/tokens")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<TokenDTO>> getAllTokenList() {
        return new ResponseEntity<>(genderQueryService.getAllTokenList(), HttpStatus.OK);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GenderDTO> getGenderByVariantAndInputData(@RequestParam(value = "variant") @NotBlank String variant, @RequestParam(value = "inputData") String inputData) {
        return new ResponseEntity<GenderDTO>(genderQueryService.getGenderByVariantAndInputData(variant, inputData), HttpStatus.OK);
    }


}
