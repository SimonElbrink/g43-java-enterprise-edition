package se.lexicon.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import se.lexicon.dao.StudentDAO;
import se.lexicon.dao.StudentDAOImpl;
import se.lexicon.dao.sequencer.Sequencers;
import se.lexicon.dao.sequencer.SequencersImpl;
import se.lexicon.service.StudentService;
import se.lexicon.service.StudentServiceImpl;

import java.util.Scanner;

@Configuration
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

    @Bean
    public Sequencers sequencers(){
        return new SequencersImpl();
    }


    @Bean
    public StudentDAO studentDAO(){
        // StudentDAO needs a dependency of a Sequencer.
        return new StudentDAOImpl(sequencers());
    }

    @Bean
    public StudentService studentService(){
        //Constructor injection
        return new StudentServiceImpl(studentDAO());

    }

}
