package se.lexicon.spring_bootjpalecture.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.spring_bootjpalecture.entity.Student;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // SpringBootTest Activates the Spring Server.
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // it configures the test DB
@AutoConfigureTestEntityManager
@DirtiesContext // This tells Spring to clear the Application context after the test has run.
@Transactional
class StudentDaoImplTest {

    @Autowired
    StudentDao studentDao;

    @Autowired
    TestEntityManager entityManager;

    Student testObject;

    public List<Student> students(){
        return Arrays.asList(
                new Student("Martin","Chilling","martin.chilling@mail.com",LocalDate.parse("1970-01-01"),true),
                new Student("Charles","Chilling","charles.chilling@mail.com",LocalDate.parse("2000-05-05"),true),
                new Student("Mika","Chilling","mika.chilling@mail.com",LocalDate.parse("1972-04-04"),true),
                new Student("Milla","Chilling","milla.chilling@mail.com",LocalDate.parse("2003-06-06"),true)

        );
    }

    @BeforeEach
    void setUp() {
        List<Student> persistedStudents = students().stream()
                .map(entityManager::persist)
                .collect(Collectors.toList());

        testObject = persistedStudents.get(0);
    }

    @Test
    void save() {
        Student student = new Student("Test", "Testsson","test@Testsson.tt", LocalDate.parse("2020-02-29"),true);
        studentDao.save(student);

        assertNotEquals(0, student.getId());
    }


    @Test
    void findById() {

        Optional<Student> studentDaoById = studentDao.findById(testObject.getId());

        assertTrue(studentDaoById.isPresent());
        assertEquals(testObject, studentDaoById.get());
    }

    @Test
    void findByFirstName() {

        List<Student> found = studentDao.findByFirstName("Milla");
        found.forEach(System.out::println);

        assertFalse(found.isEmpty());

    }
}