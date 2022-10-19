package se.lexicon.spring_bootrestful_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.spring_bootrestful_api.model.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(String RoleName);
}
