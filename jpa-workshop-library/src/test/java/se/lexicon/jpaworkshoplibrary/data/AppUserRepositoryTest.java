package se.lexicon.jpaworkshoplibrary.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import se.lexicon.jpaworkshoplibrary.data.repository.AppUserRepository;
import se.lexicon.jpaworkshoplibrary.entity.AppUser;
import se.lexicon.jpaworkshoplibrary.entity.Details;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AppUserRepositoryTest {


    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AppUserRepository appUserRepository;

    private AppUser testObject;


    public List<AppUser> appUsers (){
        return Arrays.asList(
                new AppUser("simonelbrink","ThisIsASecretPassword",
                        new Details("simon@lexicon.se","Simon Elbrink", LocalDate.parse("1997-03-18"))),
                new AppUser("ulfbengtsson","ThisIsAnOtherPassword",
                        new Details("ulf@lexicon.se","Ulf Bengtsson", LocalDate.parse("1982-08-25")))
        );
    }

    @BeforeEach
    void setUp() {
         List<AppUser> persistedAppUsers =
                 appUsers().stream()
                         .map(entityManager::persist)
                         .collect(Collectors.toList());

         testObject = persistedAppUsers.get(0);
    }

    @Test
    void findById() {
        Optional<AppUser> foundAppUser = appUserRepository.findById(testObject.getAppUserId());

        assertTrue(foundAppUser.isPresent());
    }

    @Test
    void findAll() {

        Collection<AppUser> found = appUserRepository.findAll();

        assertNotNull(found);
        assertEquals(2, found.size());


    }

    @Test
    void create() {
        AppUser appUser = new AppUser("Maggan123","ThisIsASecret", null);

        AppUser persistedUser = appUserRepository.save(appUser);

        assertNotNull(persistedUser);
        assertNotEquals(0,persistedUser.getAppUserId());
        assertNotNull(entityManager.find(AppUser.class, persistedUser.getAppUserId()));
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
        //Check if simon is in database.
        assertNotNull(entityManager.find(AppUser.class, testObject.getAppUserId()));

        //remove simon by id.
        appUserRepository.deleteById(testObject.getAppUserId());

        //Check if simon is removed.
        assertNull(entityManager.find(AppUser.class, testObject.getAppUserId()));
    }
}