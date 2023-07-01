package tn.camps.tuncamps.persistence.repository.parc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.camps.tuncamps.persistence.entity.commun.Location;
import tn.camps.tuncamps.persistence.entity.parc.Parc;

@Repository
public interface ParcRepository extends JpaRepository<Parc, Integer> {
    boolean existsByLocation(Location location);

}