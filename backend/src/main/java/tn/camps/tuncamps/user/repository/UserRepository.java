package tn.camps.tuncamps.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <UserEntity, Integer> {
UserEntity findByUsername(String username);

boolean existsByEmail(String email);
boolean existsByUsername(String userName);

}
