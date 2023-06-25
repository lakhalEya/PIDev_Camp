package tn.camps.tuncamps.persistence.repositories.parc;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.camps.tuncamps.persistence.entities.commun.Tariff;
import tn.camps.tuncamps.persistence.entities.parc.Parc;

public interface ParcRepository extends JpaRepository<Parc, Integer> {
}
