package com.klimek.demo.restApi.utils;

import com.klimek.demo.restApi.entities.Token;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


@Component
public class CsvUtils {

    public static String TYPE = "text/csv";
    static String[] HEADERs = {"name", "gender", "Great Britain", "Ireland", "U.S.A.", "Italy", "Malta", "Portugal", "Spain",
            "France", "Belgium", "Luxembourg", "the Netherlands", "East Frisia", "Germany", "Austria", "Swiss", "Iceland",
            "Denmark", "Norway", "Sweden", "Finland", "Estonia", "Latvia", "Lithuania", "Poland", "Czech Republic", "Slovakia",
            "Hungary", "Romania", "Bulgaria", "Bosnia and Herzegovina", "Croatia", "Kosovo", "Macedonia", "Montenegro", "Serbia",
            "Slovenia", "Albania", "Greece", "Russia", "Belarus", "Moldova", "Ukraine", "Armenia", "Azerbaijan", "Georgia",
            "Kazakhstan/Uzbekistan,etc.", "Turkey", "Arabia/Persia", "Israel", "China", "India/Sri Lanka", "Japan", "Korea",
            "Vietnam", "other countries"};


    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<Token> csvToGenderList(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withDelimiter(';'));) {

            List<Token> tokens = new ArrayList<Token>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Token token = new Token(
                        csvRecord.get("name"),
                        csvRecord.get("gender"));
                tokens.add(token);
            }

            return tokens;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
}
