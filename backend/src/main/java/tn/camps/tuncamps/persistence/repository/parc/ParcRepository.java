package tn.camps.tuncamps.persistence.repository.parc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    @Query("SELECT MIN(p.rating) FROM Parc p WHERE p.idParc IN (:parcIds)")
     Double getMinRatingRange(@Param("parcIds") List<Integer> parcIds);

    @Query("SELECT MAX(p.rating) FROM Parc p WHERE p.idParc IN (:parcIds)")
    Double getMaxRatingRange(@Param("parcIds") List<Integer> parcIds);

    @Query("SELECT MIN(p.maxCapacity)  FROM Parc p WHERE p.idParc IN (:parcIds)")
    Double getMinCapacityRange(List<Integer> parcIds);

    @Query("SELECT MAX(p.maxCapacity) FROM Parc p WHERE p.idParc IN (:parcIds)")
    Double getMaxCapacityRange(List<Integer> parcIds);

    @Query("SELECT DISTINCT amenity FROM Parc p JOIN p.amenities amenity WHERE p.idParc IN (:parcIds)")
    List<String> getAllAmenities(List<Integer> parcIds);

    @Query("SELECT DISTINCT p.location.city.city FROM Parc p WHERE p.idParc IN (:parcIds)")
    List<String> getAllCities(List<Integer> parcIds);

    @Query("SELECT DISTINCT p.location.city.country FROM Parc p WHERE p.idParc IN (:parcIds)")
    List<String> getAllCountries(List<Integer> parcIds);

    @Query("SELECT DISTINCT p.category FROM Parc p WHERE p.idParc IN (:parcIds)")
    List<String> getAllcategories(List<Integer> parcIds);
}