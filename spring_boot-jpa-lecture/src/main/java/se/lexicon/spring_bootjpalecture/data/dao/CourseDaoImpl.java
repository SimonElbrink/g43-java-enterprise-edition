package se.lexicon.spring_bootjpalecture.data.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.spring_bootjpalecture.entity.Course;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class CourseDaoImpl implements CourseDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(rollbackFor = IllegalArgumentException.class)
    @Override
    public Course save(Course course) {
        if (course == null) throw new IllegalArgumentException("Course not allowed to be null");

        if (course.getId() <= 0) {
            entityManager.persist(course); // All New object
            return course;
        } else {
            entityManager.merge(course); // Attached an already saved object.
        }
        return course;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Course> findById(int id) {
        Course course = entityManager.find(Course.class, id);
        return Optional.ofNullable(course);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Course> findAll() {
        return  entityManager.createQuery("select c from Course c", Course.class).getResultList();
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void remove(Course course) {
        findById(course.getId()).ifPresent(entityManager::remove);
    }
}
