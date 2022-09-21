package se.lexicon.jpaworkshoplibrary.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import se.lexicon.jpaworkshoplibrary.data.repository.BookLoanRepository;
import se.lexicon.jpaworkshoplibrary.entity.AppUser;
import se.lexicon.jpaworkshoplibrary.entity.Book;
import se.lexicon.jpaworkshoplibrary.entity.BookLoan;
import se.lexicon.jpaworkshoplibrary.entity.Details;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class BookLoanRepositoryImplTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookLoanRepository bookLoanRepository;

    private BookLoan testObject;

    public List<AppUser> appUsers(){
        return Arrays.asList(
                new AppUser("simonelbrink","ThisIsASecretPassword",
                        new Details("simon@lexicon.se","Simon Elbrink", LocalDate.parse("1997-03-18"))),
                new AppUser("ulfbengtsson","ThisIsAnOtherPassword",
                        new Details("ulf@lexicon.se","Ulf Bengtsson", LocalDate.parse("1982-08-25")))
        );
    }


    public List<Book> books(){
        return Arrays.asList(
                new Book("34348934fk","Java Core",15),
                new Book("34348934fk","Java Core Advanced",15)
        );
    }




    @BeforeEach
    void setUp() {
        List<AppUser> persistedAppUsers =
                appUsers().stream()
                        .map(entityManager::persist)
                        .collect(Collectors.toList());

        List<Book> persistedBooks =
                books().stream()
                        .map(entityManager::persist)
                        .collect(Collectors.toList());

        List<BookLoan> bookLoans = new ArrayList<>(
                Arrays.asList(
                        new BookLoan(LocalDate.now(),
                                LocalDate.now().plusDays(50),
                                false,
                                persistedAppUsers.get(0),
                                persistedBooks.get(0)),

                        new BookLoan(LocalDate.now(),
                                LocalDate.now().plusDays(30),
                                false,
                                persistedAppUsers.get(0),
                                persistedBooks.get(1))

                )
        );

        List<BookLoan> persistedBookLoans =  bookLoans.stream()
                .map(entityManager::persist)
                .collect(Collectors.toList());

        testObject = persistedBookLoans.get(0);
    }

    @Test
    void create() {
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
        assertEquals(2, bookLoanRepository.findAll().size());
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}