package tn.camps.tuncamps.persistence.repository.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.camps.tuncamps.persistence.entity.booking.Sale;


@Repository
public interface SaleRepository extends JpaRepository<Sale, Integer> {
}
