package tn.camps.tuncamps.service.parc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.camps.tuncamps.persistence.entity.commun.Location;
import tn.camps.tuncamps.persistence.entity.parc.Parc;
import tn.camps.tuncamps.persistence.repository.commun.LocationRepository;
import tn.camps.tuncamps.persistence.repository.parc.ParcRepository;
import tn.camps.tuncamps.service.commun.LocationService;
import tn.camps.tuncamps.service.parc.ParcService;

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

        if (existingParc.get().getLocation() != null && (existingParc.get().getLocation().getLongitude() != parc.getLocation().getLongitude() ||
                existingParc.get().getLocation().getLatitude() != parc.getLocation().getLatitude())) {

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
        parcRepository.deleteById(parcId);
    }

    @Override
    public Parc getParcById(int parcId) {
        return parcRepository.findById(parcId)
                .orElseThrow(() -> new NoSuchElementException("Parc not found with ID: " + parcId));
    }

    @Override
    public List<Parc> getAllParcs() {
        return parcRepository.findAll();
    }
}
