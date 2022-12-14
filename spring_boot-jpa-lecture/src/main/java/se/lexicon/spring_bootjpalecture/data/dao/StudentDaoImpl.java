package se.lexicon.spring_bootjpalecture.data.dao;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.spring_bootjpalecture.exceptions.EntityNotFoundException;
import se.lexicon.spring_bootjpalecture.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository // Component = Bean
public class StudentDaoImpl implements StudentDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public Student save(Student student) {
        if (student == null) throw new IllegalArgumentException("Student Was Null");
        entityManager.persist(student);
        return student;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Student> findById(int id) {
        if (id <= 0) throw new IllegalArgumentException("Invalid ID");
        // SQL - SELECT * FROM students WHERE id = ?    JPQL - SELECT s FROM Student s WHERE s.id = ?
        Student student = entityManager.find(Student.class, id); // For Finding by ID ONLY
        return Optional.ofNullable(student);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Student> findAll() {
        TypedQuery<Student> selectQuery = entityManager.createQuery("SELECT s FROM Student s", Student.class); // SQL - SELECT * FROM students

        return selectQuery.getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Student> findByFirstName(String firstName) {

        TypedQuery<Student> selectQuery = entityManager.createQuery("SELECT s FROM Student s WHERE s.firstName = :fn", Student.class);
        selectQuery.setParameter("fn", firstName);

        return selectQuery.getResultList();
    }

    @Transactional(rollbackFor = EntityNotFoundException.class)
    @Override
    public void remove(Student student) {
        findById(student.getId()).orElseThrow(() -> new EntityNotFoundException("data not found"));
        entityManager.remove(student);
    }

    @Transactional
    @Override
    public Student update(Student student) {

        return entityManager.merge(student);
    }

//    @Transactional
    @Override
    public List<Student> saveStudents(List<Student> students) {
        students.forEach(this::save);
        return students;
    }
}
