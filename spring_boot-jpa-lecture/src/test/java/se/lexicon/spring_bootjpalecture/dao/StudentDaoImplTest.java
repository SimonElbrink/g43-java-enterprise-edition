package se.lexicon.spring_bootjpalecture.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.spring_bootjpalecture.model.Student;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    @Test
    void save() {
        Student student = new Student("Test", "Testsson","test@Testsson.tt", LocalDate.parse("2020-02-29"),true);
        studentDao.save(student);

        assertNotEquals(0, student.getId());
    }


    @Test
    void findById() {

        Student student = new Student("f", "l", "e", LocalDate.MAX, false);

        Integer id = entityManager.persistAndGetId(student, Integer.class);
        entityManager.clear();

        Optional<Student> studentDaoById = studentDao.findById(id);

        assertTrue(studentDaoById.isPresent());
        assertEquals(id, studentDaoById.get().getId());
    }

    @Test
    void findByFirstName() {

        List<Student> found = studentDao.findByFirstName("Simon");
        found.forEach(System.out::println);

        assertFalse(found.isEmpty());

    }
}