package tn.camps.tuncamps.persistence.repository.commun;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.camps.tuncamps.persistence.entity.commun.Tariff;

@Repository
public interface TariffRepository extends JpaRepository<Tariff, Integer> {
}

