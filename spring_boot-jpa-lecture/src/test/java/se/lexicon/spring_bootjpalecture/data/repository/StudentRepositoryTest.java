package se.lexicon.spring_bootjpalecture.data.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import se.lexicon.spring_bootjpalecture.entity.Student;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StudentRepositoryTest {


    public List<Student> students(){
        return Arrays.asList(
                new Student("Martin","Chilling","martin.chilling@mail.com", LocalDate.parse("1970-01-01"),true),
                new Student("Charles","Chilling","charles.chilling@mail.com",LocalDate.parse("2000-05-05"),true),
                new Student("Mika","Chilling","mika.chilling@mail.com",LocalDate.parse("1972-04-04"),true),
                new Student("Milla","Chilling","milla.chilling@mail.com",LocalDate.parse("2003-06-06"),true)

        );
    }

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    StudentRepository testRepo;

    Student testStudent;

    @BeforeEach
    void setUp() {
        List<Student> persistedStudents = students().stream()
                .map(entityManager::persist)
                .collect(Collectors.toList());

        testStudent = persistedStudents.get(0);
    }

    @Test
    void test_findByBirthDateBetween() {

        LocalDate startDate = LocalDate.parse("1990-01-01");
        LocalDate endDate = LocalDate.parse("2005-01-01");

        List<Student> foundStudents = testRepo.findAllByBirthDateBetween(startDate,endDate);

        
        assertFalse(foundStudents.isEmpty());
        assertEquals(2,foundStudents.size());
    }

    @Test
    void test_findByName() {

        String partOfAName ="a chill";

        List<Student> found = testRepo.findByName(partOfAName);

        assertEquals(2,found.size());
    }
}









