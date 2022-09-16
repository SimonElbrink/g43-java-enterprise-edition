package se.lexicon.spring_bootjpalecture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.spring_bootjpalecture.data.dao.BookDao;
import se.lexicon.spring_bootjpalecture.data.dao.StudentDao;
import se.lexicon.spring_bootjpalecture.data.repository.AddressRepository;
import se.lexicon.spring_bootjpalecture.data.repository.BookRepository;
import se.lexicon.spring_bootjpalecture.data.repository.CourseRepository;
import se.lexicon.spring_bootjpalecture.data.repository.StudentRepository;
import se.lexicon.spring_bootjpalecture.entity.Address;
import se.lexicon.spring_bootjpalecture.entity.Book;
import se.lexicon.spring_bootjpalecture.entity.Course;
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

@Profile(value = "dev") // linked to profile spring.profiles.active in Application.properties
@Transactional
@Component
class MyCommandLineRunner implements CommandLineRunner {


	@Autowired
	public MyCommandLineRunner(StudentRepository studentRepo, BookRepository bookRepository, AddressRepository addressRepository, CourseRepository courseRepository, EntityManager entityManager) {
		this.studentRepo = studentRepo;
		this.bookRepository = bookRepository;
		this.addressRepository = addressRepository;
		this.courseRepository = courseRepository;
		this.entityManager = entityManager;
	}

	private final StudentRepository studentRepo;

	private final BookRepository bookRepository;

	private final AddressRepository addressRepository;

	private final CourseRepository courseRepository;

	private final EntityManager entityManager;


	@Override
	public void run(String... args) {

		System.out.println("Hello from A Spring Boot Application - CommandLineRunner!");



		System.out.println("---- Example - No Cascade");
		Student simon = new Student("Simon", "Elbrink",
				"simon@lexicon.se",
				LocalDate.parse("1997-01-18"),
				true);
		simon = studentRepo.save(simon);

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
		mehrdad = studentRepo.save(mehrdad);

		Address address2 = new Address("Test","Storgatan 1","12345");
		mehrdad.setAddress(address2); // Set Relationship - no manual saving first.

		entityManager.flush();
		System.out.println("mehrdad = " + mehrdad);


		System.out.println("---- Example - Add Book ");
		// Book Save
		Book javaCoreF = bookRepository.save(new Book("Java Core : Fundamentals vol I 12 Edition"));
		Book javaCoreA = bookRepository.save(new Book("Java Core : Advanced vol II 12 Edition"));
		Book springB = bookRepository.save(new Book("Spring Framework Basics"));


		//Handling The Relationship - By hand directly

//		List<Book> simonsBooks = new ArrayList<>(Arrays.asList(
//				javaCoreF, javaCoreA, springB
//		));
		// Set Student to Books.
//		javaCoreF.setStudent(simon);
//		javaCoreA.setStudent(simon);
//		springB.setStudent(simon);

		//Set Books to Student.
//		simon.setListOfOwnedBooks(simonsBooks);


		//Handle the Relationship with convenience methods
		simon.addBook(javaCoreF);
		simon.addBook(javaCoreA);
		simon.addBook(springB);

		//or (Not Implemented)
//		simon.addBooks(javaCoreF, javaCoreA, springB);

		simon.getListOfOwnedBooks().forEach(System.out::println);




		mehrdad.addBook(new Book("Spring Framework Basics"));
		entityManager.flush();

		mehrdad.getListOfOwnedBooks().forEach(System.out::println);





		Book aBook = new Book("ABook"); // id=0, name:"Abook", student: null
		bookRepository.save(aBook);// id=5, name:"Abook", student: null
		// abook part of PersistenceContext


//		aBook.setName("UpdatedBook"); // Trigger an update in database = because it's in Persistence Context
//		bookRepository.update(aBook); // not needed for updating data.


//		//Detached - Same value as aBook but NOT in the persistence context == not Same Object
		Book ABook = new Book(5,"ABook",null);// id=5, name:"Abook", student: null

//		Remove will show detached. when try to remove with Object. (Implementation Removed)
//		bookRepository.delete(ABook);

		//Attached ABook = bring to context
		bookRepository.save(ABook);
		
//		ABook.setName("UpdatedBook");


		bookRepository.deleteById(aBook.getId()); // Removes the entity from Persistence Context = Remove Data Row in Database.


		System.out.println("FINDBYFIRSTNAME");

		studentRepo.findAllByFirstNameIgnoreCase("simon").forEach(System.out::println);


		studentRepo.findAllByBirthDateBetween(LocalDate.parse("1990-01-01"), LocalDate.parse("2000-01-01"));



		Course course = new Course("JavaGroup43");
		course = courseRepository.save(course);

		simon.addCourse(course);
		mehrdad.addCourse(course);


		System.out.println("findByCourseName");
		studentRepo.findStudentsByCoursesNameIgnoreCase("javagroup43").forEach(System.out::println);



	}
}
