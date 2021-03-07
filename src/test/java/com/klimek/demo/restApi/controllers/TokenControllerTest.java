package com.klimek.demo.restApi.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TokenControllerTest {

    final static String testUrlSingleToken = "/api/gender?variant=token&inputData=Jan";
    final static String testUrlStringOfTokens = "/api/gender?variant=string&inputData=Jan Maria Rokita";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TokenRepository repository;

    @Test
    public void should_get_all_gender_tokens() throws Exception {

        mvc.perform(MockMvcRequestBuilders
                .get("/api/gender/tokens")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].gender").exists());
    }

    @Test
    public void should_get_gender_by_chosen_variant_and_given_tokens() throws Exception {

        mvc.perform(MockMvcRequestBuilders
                .get(testUrlSingleToken)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.variant").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.inputData").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.gender").exists())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.variant").value("token"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.inputData").value("Jan"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.gender").value("M"));

        mvc.perform(MockMvcRequestBuilders
                .get(testUrlStringOfTokens)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.variant").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.inputData").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.gender").exists())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.variant").value("string"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.inputData").value("Jan Maria Rokita"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.gender").value("INCONCLUSIVE"));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}