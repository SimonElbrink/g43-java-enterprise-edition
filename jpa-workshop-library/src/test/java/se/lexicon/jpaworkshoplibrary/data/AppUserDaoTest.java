package se.lexicon.jpaworkshoplibrary.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.jpaworkshoplibrary.entity.AppUser;
import se.lexicon.jpaworkshoplibrary.entity.Details;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager
@DirtiesContext
@Transactional
class AppUserDaoTest {


    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AppUserDao appUserDao;

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
        AppUser foundAppUser = appUserDao.findById(testObject.getAppUserId());

        assertNotNull(foundAppUser);
    }

    @Test
    void findAll() {

        Collection<AppUser> found = appUserDao.findAll();

        assertNotNull(found);
        assertEquals(2, found.size());


    }

    @Test
    void create() {
        AppUser appUser = new AppUser("Maggan123","ThisIsASecret", null);

        AppUser persistedUser = appUserDao.create(appUser);

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
        appUserDao.delete(testObject.getAppUserId());

        //Check if simon is removed.
        assertNull(entityManager.find(AppUser.class, testObject.getAppUserId()));
    }
}