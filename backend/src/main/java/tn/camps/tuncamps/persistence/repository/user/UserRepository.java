package tn.camps.tuncamps.persistence.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.camps.tuncamps.persistence.entity.user.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
