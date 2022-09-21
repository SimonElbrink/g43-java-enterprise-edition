package se.lexicon.jpaworkshoplibrary.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.lexicon.jpaworkshoplibrary.entity.AppUser;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {

    @Query("SELECT a FROM AppUser a WHERE a.username = :username")
    Optional<AppUser> findAppUserByUsername(@Param("username") String username);







}
