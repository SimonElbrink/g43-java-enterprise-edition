package se.lexicon.jpaworkshoplibrary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.jpaworkshoplibrary.data.AppUserDao;
import se.lexicon.jpaworkshoplibrary.data.AuthorDao;
import se.lexicon.jpaworkshoplibrary.data.BookDao;
import se.lexicon.jpaworkshoplibrary.data.BookLoanDao;
import se.lexicon.jpaworkshoplibrary.entity.*;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootApplication
public class JpaWorkshopLibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaWorkshopLibraryApplication.class, args);
    }

}

@Transactional
@Component
class MyCommandLineRunner implements CommandLineRunner {

    @Autowired
    public MyCommandLineRunner(AppUserDao appUserDao, BookDao bookDao, AuthorDao authorDao, BookLoanDao bookLoanDao) {
        this.appUserDao = appUserDao;
        this.bookDao = bookDao;
        this.authorDao = authorDao;
        this.bookLoanDao = bookLoanDao;
    }


    AppUserDao appUserDao;

    BookDao bookDao;

    AuthorDao authorDao;

    BookLoanDao bookLoanDao;



    @Override
    public void run(String... args) throws Exception {


        seedingData();



        Optional<AppUser> roudabe = appUserDao.findAppUserByUsername("roudabehadnani");

        roudabe.ifPresent(System.out::println);




    }

    private void seedingData() throws InterruptedException {
        AppUser martinChilling = appUserDao.create(new AppUser("martinchilling", "ThisIsImportant",
                new Details("martin.chilling@mail.com", "Martin Chilling", LocalDate.parse("1960-04-05"))
        ));


        Book harryPotterDH = bookDao.create(new Book("9780545139700", "Harry Potter and the Deathly Hallows", 14));

        Author j_k_Rowling = authorDao.create(new Author("J.K.", "Rowling"));

        //Adds Author and book relationship.
        j_k_Rowling.addBook(harryPotterDH);

        LocalDate dateOfBorrow = LocalDate.parse("2020-01-01");

        BookLoan martinBorrowsHarryPotter = bookLoanDao.create(new BookLoan(
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
