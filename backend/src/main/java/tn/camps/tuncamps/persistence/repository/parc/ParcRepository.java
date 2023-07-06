package tn.camps.tuncamps.persistence.repository.parc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.camps.tuncamps.persistence.entity.commun.Location;
import tn.camps.tuncamps.persistence.entity.parc.Parc;
import tn.camps.tuncamps.persistence.entity.parc.ParcCategory;

import java.util.List;

@Repository
public interface ParcRepository extends JpaRepository<Parc, Integer>, JpaSpecificationExecutor<Parc> {
    boolean existsByLocation(Location location);
    boolean existsByLocationLatitudeAndLocationLongitude(Double latitude, Double longitude);
    List<Parc> findByLocationCityCountry(String country);

    Long countByStatus(Parc.ParcStatus status);

    @Query(value = "SELECT category FROM parc GROUP BY category ORDER BY COUNT(*) DESC LIMIT 1", nativeQuery = true)
    ParcCategory findMostUsedCategory();

    List<Parc> findByStatus(Parc.ParcStatus status);

    List<Parc> findByCategory(ParcCategory category);

    @Query("SELECT p FROM Parc p WHERE :amenity MEMBER OF p.amenities")
    List<Parc> findByAmenity(String amenity);
}