package tn.camps.tuncamps.persistence.repository.commun;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.camps.tuncamps.persistence.entity.commun.Tariff;

public interface TariffRepository extends JpaRepository<Tariff, Integer> {
}

