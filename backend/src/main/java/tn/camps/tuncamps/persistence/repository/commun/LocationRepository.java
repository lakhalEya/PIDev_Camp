package tn.camps.tuncamps.persistence.repository.commun;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import tn.camps.tuncamps.persistence.entity.commun.Location;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer>, JpaSpecificationExecutor<Location> {

    Optional<Location> findByLatitudeAndLongitude(double latitude, double longitude);
    List<Location> findByDisponibilite(boolean status);

    List<Location> findByCityCountry(String country);

    Boolean existsLocationByLatitudeAndLongitudeAndIdIsNot(double latitude, double longitude, int id);

}
