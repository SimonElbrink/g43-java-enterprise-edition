package se.lexicon;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import se.lexicon.config.ApplicationConfiguration;
import se.lexicon.model.entity.Student;

import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws JsonProcessingException {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

        Stream.of(context.getBeanDefinitionNames()).forEach(System.out::println);


        //Java Object - Init ourselves
        String messages = new Scanner(System.in).nextLine();


//       Java Object - find Spring Bean in Spring container (Context)
        Scanner scanner = context.getBean("scanner", Scanner.class);
        String message = scanner.nextLine();
        System.out.println("Prints:" + message);


        ObjectMapper objectMapper = context.getBean(ObjectMapper.class);
        System.out.println(objectMapper.writeValueAsString(new Student("Erik")));


    }
}
