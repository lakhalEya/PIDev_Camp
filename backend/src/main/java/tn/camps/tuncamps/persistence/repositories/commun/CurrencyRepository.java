package tn.camps.tuncamps.persistence.repositories.commun;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.camps.tuncamps.persistence.entities.commun.Currency;


public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
}
