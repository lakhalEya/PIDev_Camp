package tn.camps.tuncamps.persistence.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.camps.tuncamps.persistence.entity.user.User;

@Repository
public interface UserRepository extends JpaRepository <User, Integer> {
User findByUsername(String username);

boolean existsByEmail(String email);
boolean existsByUsername(String userName);

}
