package se.lexicon.spring_bootjpalecture.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import se.lexicon.spring_bootjpalecture.entity.Student;

import java.time.LocalDate;
import java.util.List;


public interface StudentRepository extends CrudRepository<Student,Integer> {

    // SELECT * FROM students WHERE first_name = ? - Native Query
    // SELECT s FROM Student s WHERE s.firstName = :fn - JPQL Query
    @Query("SELECT s FROM Student s WHERE s.firstName = :fn")
    List<Student> findAllByFirstName(@Param("fn") String firstName);

    @Query("SELECT s FROM Student s WHERE LOWER(s.firstName) = LOWER(:fn)")
    List<Student> findAllByFirstNameIgnoreCase(@Param("fn") String firstName);

    @Query("SELECT s FROM Student s WHERE LOWER(CONCAT(s.firstName, ' ', s.lastName)) LIKE LOWER(CONCAT('%', :name, '%')) ")
    List<Student> findByName(@Param("name") String name);
//    List<Student> findStudentsByFirstNameContainsIgnoreCaseOrLastNameContainsIgnoreCase(String firstName, String lastName);



    //Query Creation for methods
    List<Student> findStudentsByEmailIgnoreCase(String email);

    @Query("SELECT s FROM Student s WHERE s.birthDate BETWEEN :startDate AND :endDate")
    List<Student> findAllByBirthDateBetween(@Param("startDate")LocalDate start, @Param("endDate") LocalDate end);





    List<Student> findStudentsByCoursesNameIgnoreCase(String courseName);

    long countStudentsById(int id);


}
