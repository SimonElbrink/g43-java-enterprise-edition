package se.lexicon.spring_bootjpalecture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.spring_bootjpalecture.dao.StudentDao;
import se.lexicon.spring_bootjpalecture.entity.Address;
import se.lexicon.spring_bootjpalecture.entity.Student;

import javax.persistence.EntityManager;
import java.time.LocalDate;

@SpringBootApplication
public class SpringBootJpaLectureApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpaLectureApplication.class, args);

		System.out.println("Hello from A Spring Boot Application - Main!");
	}

}

@Transactional
@Component
class MyCommandLineRunner implements CommandLineRunner {

	@Autowired
	public MyCommandLineRunner(StudentDao studentDao, EntityManager entityManager) {
		this.studentDao = studentDao;
		this.entityManager = entityManager;
	}

	private final StudentDao studentDao;
	private final EntityManager entityManager;


	@Override
	public void run(String... args) {

		System.out.println("Hello from A Spring Boot Application - CommandLineRunner!");

		System.out.println("---- Example - No Cascade");
		Student simon = new Student("Simon", "Elbrink",
				"simon@lexicon.se",
				LocalDate.parse("1997-01-18"),
				true);
		simon = studentDao.save(simon);

		Address address = new Address("SomeTown","Storgatan 8","36073");
		entityManager.persist(address);
		simon.setAddress(address); // Make a relationship

		entityManager.flush(); // Forcing to execute the update.

		System.out.println("simon = " + simon);


		System.out.println("---- Example - CascadeType.Persist");
		Student mehrdad = new Student("Mehrdad", "Javan",
				"mehrdad@lexicon.se",
				LocalDate.parse("1990-01-01"),
				true);
		mehrdad = studentDao.save(mehrdad);

		Address address2 = new Address("Test","Storgatan 1","12345");
		mehrdad.setAddress(address2); // Set Relationship - no manual saving first.

		entityManager.flush();
		System.out.println("mehrdad = " + mehrdad);
	}
}
