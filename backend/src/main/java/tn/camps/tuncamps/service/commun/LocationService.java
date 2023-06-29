package tn.camps.tuncamps.service.commun;
import tn.camps.tuncamps.persistence.entity.commun.Location;

import java.util.List;
import java.util.Optional;

public interface LocationService {
    List<Location> getAllLocations();

    /*
     * Implement pagination in the getAllLocations method to retrieve locations in smaller chunks
     * instead of fetching all locations at once. This can improve performance and reduce memory consumption,
     *  especially when dealing with a large number of locations.
     */

    List<Location> getAllLocations(int pageNumber, int pageSize);


    List<Location> getAllLocationsSorted(String sortBy);

    List<Location> getAllLocationsFiltered(String filterBy, String filterValue);


    Optional<Location> getLocationById(int id);

    Location createLocation(Location location);

    void deleteLocation(int id);
}
