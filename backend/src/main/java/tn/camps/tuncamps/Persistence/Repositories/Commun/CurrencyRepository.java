package tn.camps.tuncamps.Persistence.Repositories.Commun;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.camps.tuncamps.Persistence.Entities.Commun.Currency;


public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
}
