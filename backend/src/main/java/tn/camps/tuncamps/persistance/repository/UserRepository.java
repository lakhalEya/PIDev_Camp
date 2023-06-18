package tn.camps.tuncamps.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.camps.tuncamps.persistance.entity.User;
@Repository
public interface UserRepository extends JpaRepository <User, Integer> {


}
