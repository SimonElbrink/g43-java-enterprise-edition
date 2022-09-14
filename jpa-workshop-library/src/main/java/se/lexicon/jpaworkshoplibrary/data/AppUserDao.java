package se.lexicon.jpaworkshoplibrary.data;

import se.lexicon.jpaworkshoplibrary.entity.AppUser;

import java.util.Collection;

public interface AppUserDao {

    AppUser findById(int id);
    Collection<AppUser> findAll();
    AppUser create(AppUser appUser);
    AppUser update(AppUser appUser);
    void delete(int id);
}
