package tn.camps.tuncamps.service.commun.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.camps.tuncamps.persistence.entity.commun.City;
import tn.camps.tuncamps.persistence.entity.commun.Location;
import tn.camps.tuncamps.persistence.repository.commun.CityRepository;
import tn.camps.tuncamps.persistence.repository.commun.LocationRepository;
import tn.camps.tuncamps.service.commun.LocationService;

import javax.persistence.criteria.Path;
import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private CityRepository cityRepository;


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
    public Optional<Location> getLocationByCoordinates(double longitude, double latitude) {
        return locationRepository.findByLatitudeAndLongitude(latitude, longitude);
    }


    @Override
    public Location createLocation(Location location) {
        Optional<City> existingCity = cityRepository.findByPostalCodeAndCountryAndCity(
                location.getCity().getPostalCode(),
                location.getCity().getCountry(),
                location.getCity().getCity());

        if (!existingCity.isPresent()) {
            throw new IllegalArgumentException("The specified city "
                    + " \n [Country : " + location.getCity().getCountry()
                    + " \n City: " + location.getCity().getCity()
                    + " \n PostalCode: " + location.getCity().getPostalCode()
                    + "]\n does not exist.");
        }

        location.setCity(existingCity.get());

        Optional<Location> existingLocation = locationRepository.findByLatitudeAndLongitude(
                location.getLatitude(), location.getLongitude());

        if (existingLocation.isPresent()) {
            throw new IllegalArgumentException("A location with the same latitude and longitude already exists.");
        }

        return locationRepository.save(location);
    }


    @Override
    public Location updateLocation(int id, Location location) {
        Optional<Location> existingLocation = locationRepository.findById(id);

        if (!existingLocation.isPresent())
            throw new IllegalArgumentException("the location with the id " + id + " doesn't exists.");

        if (existingLocation.get().getLongitude() != location.getLongitude() ||
                existingLocation.get().getLatitude() != location.getLatitude()) {
            if (locationRepository.existsLocationByLatitudeAndLongitudeAndIdIsNot(location.getLatitude(), location.getLongitude(), id))
                throw new IllegalArgumentException("another location with the same Longitude and Latitude already exists.");
        }

        if (!existingLocation.get().getCity().getCity().isEmpty() && (existingLocation.get().getCity().getPostalCode() != location.getCity().getPostalCode() ||
                existingLocation.get().getCity().getCountry() != location.getCity().getCountry() ||
                existingLocation.get().getCity().getCity() != location.getCity().getCity())) {
            Optional<City> existingCity = cityRepository.findByPostalCodeAndCountryAndCity(
                    location.getCity().getPostalCode(),
                    location.getCity().getCountry(),
                    location.getCity().getCity());

            if (!existingCity.isPresent()) {
                throw new IllegalArgumentException("The specified city "
                        + " \n [Country : " + location.getCity().getCountry()
                        + " \n City: " + location.getCity().getCity()
                        + " \n PostalCode: " + location.getCity().getPostalCode()
                        + "]\n does not exist.");
            }
            location.setId(id);
            location.setCity(existingCity.get());
        }

        return locationRepository.save(location);
}

    @Override
    public Location enableLocation(int id) {
        Optional<Location> existingLocation = locationRepository.findById(id);

        if (!existingLocation.isPresent())
            throw new IllegalArgumentException("the location with the id + " + id + " doesn't exists.");
        existingLocation.get().setDisponibilite(true);
        return locationRepository.save(existingLocation.get());

    }

    @Override
    public Location disableLocation(int id) {
        Optional<Location> existingLocation = locationRepository.findById(id);
        if (!existingLocation.isPresent())
            throw new IllegalArgumentException("the location with the id + " + id + " doesn't exists.");
        existingLocation.get().setDisponibilite(false);
        return locationRepository.save(existingLocation.get());

    }
    @Override
    public void deleteLocation(int id) {
        Optional<Location> locationData = locationRepository.findById(id);
        if (locationData.isPresent()) {
            Location location = locationData.get();
            try {
                locationRepository.delete(location);
            } catch (Exception e) {
                throw new RuntimeException("Failed to delete location with id: " + id, e);
            }
        } else {
            throw new IllegalArgumentException("Location not found with id: " + id);
        }
    }

    @Override
    public void deleteLocationByCoordinates(double longitude, double latitude) {
        Optional<Location> locationData = locationRepository.findByLatitudeAndLongitude(latitude, longitude);
        if (locationData.isPresent()) {
            Location location = locationData.get();
            try {
                locationRepository.delete(location);
            } catch (Exception e) {
                throw new RuntimeException("Failed to delete location with longitude: " + longitude + " and latitude " + latitude, e);
            }
        } else {
            throw new IllegalArgumentException("Location not found with longitude: " + longitude + " and latitude " + latitude);
        }
    }


}
