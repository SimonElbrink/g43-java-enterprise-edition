package se.lexicon.jpaworkshoplibrary.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.lexicon.jpaworkshoplibrary.entity.AppUser;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {

    @Query("SELECT a FROM AppUser a WHERE a.username = :username")
    Optional<AppUser> findAppUserByUsername(@Param("username") String username);

    List<AppUser> findAppUsersByUsernameContaining(String username);

    List<AppUser> findAppUsersByUserDetails_BirthDateBefore(LocalDate dateBefore);
    List<AppUser> findAppUsersByUserDetails_BirthDateAfter(LocalDate dateAfter);
    List<AppUser> findAppUsersByUserDetails_BirthDateBetween(LocalDate afterDate,LocalDate beforeDate);
    Optional<AppUser> findAppUserByUserDetailsEmail(String email);


    /**
     * Finding who is borrowing a specific book by there ISBN
     * @param ISBN the international standard book number for a book.
     * @return appUsers borrowing a specific book.
     */
    List<AppUser> findAppUsersByBookLoans_Book_Isbn(String ISBN);

    @Modifying
    @Query("UPDATE AppUser a SET a.password = :pwd WHERE a.username = :username")
    void updatePasswordByUsername(@Param("username") String username, @Param("pwd") String newPassword);






}
