package tn.camps.tuncamps.persistence.repository.commun;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import tn.camps.tuncamps.persistence.entity.commun.City;

import java.util.Optional;

public interface CityRepository  extends JpaRepository<City, Integer> {
    Optional<City> findByPostalCodeAndCountryAndCity(String postalCode, String country, String city);
    @Modifying
    @Query(value = "ALTER TABLE city AUTO_INCREMENT = 1", nativeQuery = true)
    void resetIdValue();
}
