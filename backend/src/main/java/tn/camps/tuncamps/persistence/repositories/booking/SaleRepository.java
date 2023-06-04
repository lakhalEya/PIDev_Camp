package tn.camps.tuncamps.persistence.repositories.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.camps.tuncamps.persistence.entities.booking.Sale;


public interface SaleRepository extends JpaRepository<Sale, Integer> {
}
