package se.lexicon.spring_bootjpalecture;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.lexicon.spring_bootjpalecture.model.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.time.LocalDate;

@Transactional
@Component
public class MyCommandLineRunner implements CommandLineRunner {

    //Recommended to use @PersistenceContext instead
//    @Autowired
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Hello from A Spring Boot Application - CommandLineRunner!");

        Student student = new Student("Simon", "Elbrink",
                "simon@lexicon.se",
                LocalDate.parse("1997-01-18"),
                true);

        entityManager.persist(student);


        // Finding Student By ID
        student = entityManager.find(Student.class,1);
                System.out.println(student);


        //Find ALL
        Query query = entityManager.createQuery("SELECT s FROM Student s");
        query.getResultList().forEach(System.out::println);




    }
}
