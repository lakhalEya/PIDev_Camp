package tn.camps.tuncamps.service.commun;
import tn.camps.tuncamps.persistence.entity.commun.Location;

import java.util.List;
import java.util.Optional;

public interface LocationService {
    List<Location> getAllLocations();

    Optional<Location> getLocationById(int id);

    Location saveLocation(Location location);

    void deleteLocation(int id);
}
