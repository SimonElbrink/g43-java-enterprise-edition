package se.lexicon.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;
public class ApplicationConfiguration {

    @Bean
    // Give bean Name "scanner" (Same as method name)
    public Scanner scanner(){
        return new Scanner(System.in);
    }

    @Bean
    public ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        return objectMapper;
    }

}
