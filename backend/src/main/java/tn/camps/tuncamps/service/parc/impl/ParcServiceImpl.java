package tn.camps.tuncamps.service.parc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.camps.tuncamps.persistence.entity.commun.Location;
import tn.camps.tuncamps.persistence.entity.parc.Parc;
import tn.camps.tuncamps.persistence.entity.parc.ParcCategory;
import tn.camps.tuncamps.persistence.repository.commun.LocationRepository;
import tn.camps.tuncamps.persistence.repository.parc.ParcRepository;
import tn.camps.tuncamps.service.commun.LocationService;
import tn.camps.tuncamps.service.parc.ParcService;

import javax.persistence.criteria.Path;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional

public class ParcServiceImpl implements ParcService {

    private final ParcRepository parcRepository;
    private final LocationRepository locationRepository;
    private final LocationService locationService;


    @Autowired
    public ParcServiceImpl(ParcRepository parcRepository, LocationRepository locationRepository, LocationService locationService) {
        this.parcRepository = parcRepository;
        this.locationRepository = locationRepository;
        this.locationService = locationService;
    }
    @Override
    public List<Parc> getParcsByIds(List<Integer> parcIds) {
        return parcRepository.findAllById(parcIds);
    }

    @Override
    @Transactional
    public Parc createParc(Parc parc) {
        if (parc.getLocation() != null) {

            Optional<Location> location = locationRepository.findByLatitudeAndLongitude(parc.getLocation().getLatitude(), parc.getLocation().getLongitude());

            if (location.isPresent()) {
                if (parcRepository.existsByLocationLatitudeAndLocationLongitude(location.get().getLatitude(), location.get().getLongitude())) {
                    throw new IllegalArgumentException("Another parc with the same location already exists.");
                }
                parc.setLocation(location.get());
            } else {
                Location newLocation = locationService.createLocation(parc.getLocation());
                parc.setLocation(newLocation);
            }
        } else {
            throw new IllegalArgumentException("The location is mandatory for the parc");

        }
        if (parc.getMaxCapacity() < 10) {
            throw new IllegalArgumentException("the Max Capacity should be greater than 10.");
        }

        Date currentDate = new Date();
        parc.setCreationDate(currentDate);
        parc.setLastUpdateDate(currentDate);

        parc.setStatus(Parc.ParcStatus.ENABLED);

        return parcRepository.saveAndFlush(parc);
    }

    @Override
    @Transactional
    public Parc updateParc(int id, Parc parc) {
        Optional<Parc> existingParc = parcRepository.findById(id);
        if (!existingParc.isPresent())
            throw new IllegalArgumentException("the parc with the id " + id + " doesn't exists.");

        if (parc.getCreationDate() != null && existingParc.get().getCreationDate().compareTo(parc.getCreationDate()) != 0)
            throw new IllegalArgumentException("You can't modify the creation date");

        parc.setCreationDate(existingParc.get().getCreationDate());

        if (existingParc.get().getLocation() != null && (existingParc.get().getLocation().getLongitude() != parc.getLocation().getLongitude() || existingParc.get().getLocation().getLatitude() != parc.getLocation().getLatitude())) {

            Optional<Location> location = locationRepository.findByLatitudeAndLongitude(parc.getLocation().getLatitude(), parc.getLocation().getLongitude());

            if (location.isPresent()) {
                if (parcRepository.existsByLocationLatitudeAndLocationLongitude(location.get().getLatitude(), location.get().getLongitude())) {
                    throw new IllegalArgumentException("Another parc with the same location already exists.");
                }
                parc.setLocation(location.get());
            } else {
                Location newLocation = locationService.createLocation(parc.getLocation());
                parc.setLocation(newLocation);
            }
        } else {
            parc.setLocation(existingParc.get().getLocation());

        }

        if (parc.getMaxCapacity() < 10) {
            throw new IllegalArgumentException("the Max Capacity should be greater than 10.");
        }
        Date currentDate = new Date();
        parc.setLastUpdateDate(currentDate);

        return parcRepository.saveAndFlush(parc);
    }

    @Override
    public void deleteParc(int parcId) {
        Optional<Parc> parc = parcRepository.findById(parcId);
        if (parc.isPresent()) {
            try {
                parcRepository.deleteById(parcId);
            } catch (Exception e) {
                throw new RuntimeException("Failed to delete parc with id: " + parcId, e);
            }
        } else {
            throw new IllegalArgumentException("parc not found with id: " + parcId);
        }
    }


    @Override
    public Parc getParcById(int parcId) {
        return parcRepository.findById(parcId).orElseThrow(() -> new NoSuchElementException("Parc not found with ID: " + parcId));
    }

    @Override
    public List<Parc> getParcByCountry(String country) {
        return parcRepository.findByLocationCityCountry(country);
    }

    @Override
    public List<Parc> getAllParcsFiltered(String filterBy, String filterValue) {
        Specification<Parc> spec = createParcSpecification(filterBy, filterValue);
        return parcRepository.findAll(spec);
    }

    private Specification<Parc> createParcSpecification(String filterBy, String filterValue) {
        return (root, query, criteriaBuilder) -> {
            if (filterBy != null && filterValue != null) {
                Path<String> filterField = root.get(filterBy);
                return criteriaBuilder.like(filterField, "%" + filterValue + "%");
            }
            return null;
        };
    }

    @Override
    public List<Parc> getParcByCategory(ParcCategory category) {
        return parcRepository.findByCategory(category);
    }

    @Override
    public List<Parc> getParcByAmenities(String amenities) {
        return parcRepository.findByAmenity(amenities);
    }

    @Override
    public List<Parc> getParcByDisponibility(Parc.ParcStatus status) {
        return parcRepository.findByStatus(status);
    }
    @Override
    public List<Parc> getAllParcsSorted(String sortBy) {
        Sort sort = Sort.by(sortBy);
        return parcRepository.findAll(sort);
    }
    @Override
    public List<Parc> getAllParcs() {
        return parcRepository.findAll();
    }


    @Override
    public Parc enableParc(int id) {
        Optional<Parc> existingParc = parcRepository.findById(id);
        if (!existingParc.isPresent())
            throw new IllegalArgumentException("the location with the id + " + id + " doesn't exists.");
        existingParc.get().setStatus(Parc.ParcStatus.DISABLED);
        return parcRepository.save(existingParc.get());

    }

    @Override
    public Parc disableParc(int id) {
        Optional<Parc> existingParc = parcRepository.findById(id);
        if (!existingParc.isPresent())
            throw new IllegalArgumentException("the location with the id + " + id + " doesn't exists.");
        existingParc.get().setStatus(Parc.ParcStatus.DISABLED);
        return parcRepository.save(existingParc.get());

    }


}
