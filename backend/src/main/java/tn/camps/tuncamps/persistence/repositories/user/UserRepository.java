package tn.camps.tuncamps.persistence.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.camps.tuncamps.persistence.entities.user.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
