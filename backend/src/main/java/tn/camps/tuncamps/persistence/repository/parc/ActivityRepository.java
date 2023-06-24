package tn.camps.tuncamps.persistence.repository.parc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.camps.tuncamps.persistence.entity.parc.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer> {
}