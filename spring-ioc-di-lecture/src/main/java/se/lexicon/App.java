package se.lexicon;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import se.lexicon.config.ApplicationConfiguration;
import se.lexicon.dao.StudentDAO;
import se.lexicon.model.entity.Student;
import se.lexicon.service.StudentService;

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




        StudentDAO studentDAO = context.getBean(StudentDAO.class);

        Student student = new Student("Erik");
        studentDAO.save(student);
        studentDAO.save(new Student("Tomas"));

        System.out.println("studentDAO");
        studentDAO.findAll().forEach(System.out::println);


        StudentService studentService = context.getBean(StudentService.class);
        System.out.println("studentService");
        studentService.findAll().forEach(System.out::println);


        // Same instance of storage. The Context is shared.
        StudentService studentService1 = context.getBean(StudentService.class);
        System.out.println("studentService1");
        studentService1.findAll().forEach(System.out::println);


    }
}
