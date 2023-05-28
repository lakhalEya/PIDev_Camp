package tn.camps.tuncamps.service.commun.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.camps.tuncamps.persistence.entity.commun.Location;
import tn.camps.tuncamps.persistence.repository.LocationRepository;
import tn.camps.tuncamps.service.LocationService;

import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;


    @Override
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    @Override
    public Optional<Location> getLocationById(int id) {
        return locationRepository.findById(id);
    }

    @Override
    public Location saveLocation(Location location) {
        return locationRepository.save(location);
    }

    @Override
    public void deleteLocation(int id) {
        locationRepository.deleteById(id);
    }
}
