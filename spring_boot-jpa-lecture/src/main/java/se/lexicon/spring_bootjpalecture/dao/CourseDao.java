package se.lexicon.spring_bootjpalecture.dao;

import se.lexicon.spring_bootjpalecture.entity.Course;

import java.util.List;
import java.util.Optional;

public interface CourseDao {

    Course save(Course course);

    Optional<Course> findById(int id);

    List<Course> findAll();

    void remove(Course course);
}
