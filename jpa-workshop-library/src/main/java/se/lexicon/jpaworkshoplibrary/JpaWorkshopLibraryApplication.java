package se.lexicon.jpaworkshoplibrary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.jpaworkshoplibrary.data.repository.AppUserRepository;
import se.lexicon.jpaworkshoplibrary.data.repository.AuthorRepository;
import se.lexicon.jpaworkshoplibrary.data.repository.BookRepository;
import se.lexicon.jpaworkshoplibrary.data.repository.BookLoanRepository;
import se.lexicon.jpaworkshoplibrary.entity.*;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootApplication
public class JpaWorkshopLibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaWorkshopLibraryApplication.class, args);
    }

}

@Profile("dev")
@Transactional
@Component
class MyCommandLineRunner implements CommandLineRunner {

    @Autowired
    public MyCommandLineRunner(AppUserRepository appUserRepository, BookRepository bookRepository, AuthorRepository authorRepository, BookLoanRepository bookLoanRepository) {
        this.appUserRepository = appUserRepository;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.bookLoanRepository = bookLoanRepository;
    }


    AppUserRepository appUserRepository;

    BookRepository bookRepository;

    AuthorRepository authorRepository;

    BookLoanRepository bookLoanRepository;



    @Override
    public void run(String... args) throws Exception {
        seedingData();

        Optional<AppUser> roudabe = appUserRepository.findAppUserByUsername("roudabehadnani");

        roudabe.ifPresent(System.out::println);


    }

    private void seedingData() throws InterruptedException {
        AppUser martinChilling = appUserRepository.save(new AppUser("martinchilling", "ThisIsImportant",
                new Details("martin.chilling@mail.com", "Martin Chilling", LocalDate.parse("1960-04-05"))
        ));


        Book harryPotterDH = bookRepository.save(new Book("9780545139700", "Harry Potter and the Deathly Hallows", 14));

        Author j_k_Rowling = authorRepository.save(new Author("J.K.", "Rowling"));

        //Adds Author and book relationship.
        j_k_Rowling.addBook(harryPotterDH);

        LocalDate dateOfBorrow = LocalDate.parse("2020-01-01");

        BookLoan martinBorrowsHarryPotter = bookLoanRepository.save(new BookLoan(
                dateOfBorrow,
                dateOfBorrow.plusDays(14),
                false,
                null,
                harryPotterDH));

        martinChilling.addBookLoan(martinBorrowsHarryPotter);

        Thread.sleep(1000);

        martinChilling.getBookLoans().forEach(System.out::println);
    }
}
