package se.lexicon.spring_bootjpalecture.data.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.spring_bootjpalecture.entity.Course;

public interface CourseRepository extends CrudRepository<Course, Integer> {
}
