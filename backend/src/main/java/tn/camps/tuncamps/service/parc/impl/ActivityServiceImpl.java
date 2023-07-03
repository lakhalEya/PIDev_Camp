package tn.camps.tuncamps.service.parc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.camps.tuncamps.persistence.entity.commun.Location;
import tn.camps.tuncamps.persistence.entity.commun.Tariff;
import tn.camps.tuncamps.persistence.entity.parc.Activity;
import tn.camps.tuncamps.persistence.entity.parc.Parc;
import tn.camps.tuncamps.persistence.repository.commun.LocationRepository;
import tn.camps.tuncamps.persistence.repository.parc.ActivityRepository;
import tn.camps.tuncamps.persistence.repository.parc.ParcRepository;
import tn.camps.tuncamps.service.commun.LocationService;
import tn.camps.tuncamps.service.parc.ActivityService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ActivityServiceImpl implements ActivityService {
    private final ParcRepository parcRepository;
    private final LocationRepository locationRepository;
    private final LocationService locationService;
    private final ActivityRepository activityRepository;


    @Autowired
    public ActivityServiceImpl(ActivityRepository activityRepository,ParcRepository parcRepository, LocationRepository locationRepository, LocationService locationService) {
        this.activityRepository = activityRepository;
        this.parcRepository = parcRepository;
        this.locationRepository = locationRepository;
        this.locationService = locationService;
    }
    @Override
    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    @Override
    public Optional<Activity> getActivityById(Integer id) {
        return activityRepository.findById(id);
    }

    @Override
    @Transactional
    public Activity createActivity(Activity activity) {
        // Validate mandatory fields
        if (activity.getName() == null || activity.getStartDate() == null || activity.getEndDate() == null || activity.getMaxParticipants() <= 5) {
            throw new IllegalArgumentException("Name, startDate, endDate, and maxParticipants are mandatory fields.");
        }

        if ( activity.getMaxParticipants() <= 5) {
            throw new IllegalArgumentException("maxParticipants can't be less than 5 ");
        }

        // Validate either parc or location is set, but not both
        if ((activity.getParc() == null && activity.getLocation() == null) || (activity.getParc() != null && activity.getLocation() != null)) {
            throw new IllegalArgumentException("Either parc or location must be set (but not both).");
        }

        // Validate parc existence if set
        if (activity.getParc() != null) {
            Optional<Parc> existingParc = parcRepository.findById(activity.getParc().getIdParc());
            if (!existingParc.isPresent()) {
                throw new IllegalArgumentException("The specified parc does not exist.");
            }
        }

        // Handle location existence or create new location if set
        if (activity.getLocation() != null) {
            Optional<Location> existingLocation = locationRepository.findByLatitudeAndLongitude(
                    activity.getLocation().getLatitude(), activity.getLocation().getLongitude());

            if (existingLocation.isPresent()) {
                activity.setLocation(existingLocation.get());
            } else {
                Location createdLocation = locationService.createLocation(activity.getLocation());
                activity.setLocation(createdLocation);
            }
        }

        // Check if tariff exists based on ID, create new tariff if it doesn't exist
//        if (activity.getTariff() != null) {
//            Optional<Tariff> existingTariff = tariffRepository.findById(activity.getTariff().getIdTariff());
//            if (!existingTariff.isPresent()) {
//                Tariff createdTariff = tariffRepository.save(activity.getTariff());
//                activity.setTariff(createdTariff);
//            }
//        }

        Date currentDate = new Date();
        activity.setCreationDate(currentDate);
        activity.setLastModificationDate(currentDate);

        // Other validation and business logic

        return activityRepository.save(activity);
    }

    @Override
    public Activity updateActivity(Integer id, Activity activity) {
        activity.setId(id);
        return activityRepository.save(activity);
    }

    @Override
    public void deleteActivity(Integer id) {
        activityRepository.deleteById(id);
    }
}