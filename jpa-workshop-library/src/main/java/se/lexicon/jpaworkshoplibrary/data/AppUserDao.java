package se.lexicon.jpaworkshoplibrary.data;

import se.lexicon.jpaworkshoplibrary.entity.AppUser;

import java.util.Collection;
import java.util.Optional;

public interface AppUserDao {
    AppUser findById(int id);

    Collection<AppUser> findAll();

    Optional<AppUser> findAppUserByUsername(String username);

    AppUser create(AppUser appUser);

    AppUser update(AppUser appUser);

    void delete(int id);
}
