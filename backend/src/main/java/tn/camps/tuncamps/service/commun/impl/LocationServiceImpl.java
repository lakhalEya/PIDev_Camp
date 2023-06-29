package tn.camps.tuncamps.service.commun.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import tn.camps.tuncamps.persistence.entity.commun.Location;
import tn.camps.tuncamps.persistence.repository.commun.LocationRepository;
import tn.camps.tuncamps.service.commun.LocationService;

import javax.persistence.criteria.Path;
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
    public List<Location> getAllLocations(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Location> locationPage = locationRepository.findAll(pageable);
        return locationPage.getContent();
    }

    @Override
    public List<Location> getAllLocationsSorted(String sortBy) {
        Sort sort = Sort.by(sortBy);
        return locationRepository.findAll(sort);
    }

    @Override
    public List<Location> getAllLocationsFiltered(String filterBy, String filterValue) {
        Specification<Location> spec = createLocationSpecification(filterBy, filterValue);
        return locationRepository.findAll(spec);
    }

    private Specification<Location> createLocationSpecification(String filterBy, String filterValue) {
        return (root, query, criteriaBuilder) -> {
            if (filterBy != null && filterValue != null) {
                Path<String> filterField = root.get(filterBy);
                return criteriaBuilder.like(filterField, "%" + filterValue + "%");
            }
            return null;
        };
    }


    @Override
    public Optional<Location> getLocationById(int id) {
        return locationRepository.findById(id);
    }



    @Override
    public Location createLocation(Location location) {
        Optional<Location> existingLocation = locationRepository.findByLatitudeAndLongitude(
                location.getLatitude(), location.getLongitude());
        if (existingLocation.isPresent()) {
            throw new IllegalArgumentException("A location with the same latitude and longitude already exists.");
        }
        return locationRepository.save(location);
    }

    @Override
    public void deleteLocation(int id) {
        locationRepository.deleteById(id);
    }
}
