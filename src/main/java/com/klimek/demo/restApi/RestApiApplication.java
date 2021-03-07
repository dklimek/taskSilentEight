package com.klimek.demo.restApi;

import com.klimek.demo.restApi.entities.Token;
import com.klimek.demo.restApi.repositories.TokenRepository;
import com.klimek.demo.restApi.utils.CsvUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class RestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApiApplication.class, args);
    }

}

@Component
class DemoCommandLineRunner implements CommandLineRunner {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private CsvUtils csvUtils = new CsvUtils();

    @Override
    public void run(String... args) throws Exception {

        final String fileName = "firstnames_PolandAndGreatBritain.csv";

        InputStream is = this.getFileFromResourceAsStream(fileName);

        List<Token> tokenToDb = csvUtils.csvToGenderList(is);
        tokenRepository.saveAll(tokenToDb);

    }

    private InputStream getFileFromResourceAsStream(String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }

    }

}