package tn.camps.tuncamps.service.parc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.camps.tuncamps.persistence.entity.commun.Location;
import tn.camps.tuncamps.persistence.entity.commun.Tariff;
import tn.camps.tuncamps.persistence.entity.parc.Activity;
import tn.camps.tuncamps.persistence.entity.parc.Parc;
import tn.camps.tuncamps.persistence.repository.commun.LocationRepository;
import tn.camps.tuncamps.persistence.repository.commun.TariffRepository;
import tn.camps.tuncamps.persistence.repository.parc.ActivityRepository;
import tn.camps.tuncamps.persistence.repository.parc.ParcRepository;
import tn.camps.tuncamps.service.commun.LocationService;
import tn.camps.tuncamps.service.commun.TariffService;
import tn.camps.tuncamps.service.parc.ActivityService;

import java.util.*;

@Service
public class ActivityServiceImpl implements ActivityService {
    private final ParcRepository parcRepository;
    private final LocationRepository locationRepository;
    private final LocationService locationService;

    private final TariffService tariffService;
    private final ActivityRepository activityRepository;


    @Autowired
    public ActivityServiceImpl(ActivityRepository activityRepository,ParcRepository parcRepository, LocationRepository locationRepository, LocationService locationService, TariffService tariffService) {
        this.activityRepository = activityRepository;
        this.parcRepository = parcRepository;
        this.locationRepository = locationRepository;
        this.locationService = locationService;
        this.tariffService = tariffService;
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
        if (activity.getName() == null || activity.getStartDate() == null || activity.getEndDate() == null || activity.getMaxParticipants() == 0) {
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

            int totalMinParticipants = activityRepository.sumMinParticipantsByParcAndTime(activity.getParc().getIdParc(), activity.getStartDate(), activity.getEndDate());
            int parcMaxCapacity = activity.getParc().getMaxCapacity();

            // Control for parc minimum participants
            if (parcMaxCapacity - totalMinParticipants < activity.getMinParticipants()) {
                throw new IllegalArgumentException("The specified parc cannot accommodate the minimum number of participants for the activity at the given time.");
            }

            // Control for parc maximum capacity
            if (activity.getMaxParticipants() > parcMaxCapacity) {
                throw new IllegalArgumentException("The specified parc cannot accommodate the maximum number of participants for the activity.");
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
        if (!activity.getTariff().isEmpty()) {
            Set<Tariff> activityTariffList = new HashSet<>();
            for(Tariff tariff : activity.getTariff()) {
                Tariff createdTariff = tariffService.createTariff(tariff);
                activityTariffList.add(createdTariff);
            }
                activity.setTariff(activityTariffList);
        }

        Date currentDate = new Date();
        activity.setCreationDate(currentDate);
        activity.setLastModificationDate(currentDate);

        // Other validation and business logic

        return activityRepository.save(activity);
    }

    @Override
    @Transactional
    public Activity updateActivity(int id , Activity activity) {
        // Validate mandatory fields
        if (activity.getName() == null || activity.getStartDate() == null || activity.getEndDate() == null || activity.getMaxParticipants() == 0) {
            throw new IllegalArgumentException("Name, startDate, endDate, and maxParticipants are mandatory fields.");
        }

        if (activity.getMaxParticipants() <= 5) {
            throw new IllegalArgumentException("maxParticipants can't be less than 5");
        }

        // Check if the activity exists
        Optional<Activity> existingActivity = activityRepository.findById(id);
        if (!existingActivity.isPresent()) {
            throw new IllegalArgumentException("The specified activity does not exist.");
        }

        Activity persistedActivity = existingActivity.get();

        // Validate either parc or location is set, but not both
        if ((activity.getParc() == null && activity.getLocation() == null) || (activity.getParc() != null && activity.getLocation() != null)) {
            throw new IllegalArgumentException("Either parc or location must be set (but not both).");
        }

        // Handle parc updates
        if (activity.getParc() != null) {
            // Check if the parc has changed
            if (persistedActivity.getParc() == null || persistedActivity.getParc().equals(activity.getParc())) {
                Optional<Parc> existingParc = parcRepository.findById(activity.getParc().getIdParc());
                if (!existingParc.isPresent()) {
                    throw new IllegalArgumentException("The specified parc does not exist.");
                }

                int totalMinParticipants = activityRepository.sumMinParticipantsByParcAndTime(activity.getParc().getIdParc(), activity.getStartDate(), activity.getEndDate());
                int parcMaxCapacity = activity.getParc().getMaxCapacity();

                // Control for parc minimum participants
                if (parcMaxCapacity - totalMinParticipants < activity.getMinParticipants()) {
                    throw new IllegalArgumentException("The specified parc cannot accommodate the minimum number of participants for the activity at the given time.");
                }

                // Control for parc maximum capacity
                if (activity.getMaxParticipants() > parcMaxCapacity) {
                    throw new IllegalArgumentException("The specified parc cannot accommodate the maximum number of participants for the activity.");
                }
                activity.setLocation(null);
            }
        }

        // Handle location updates
        if (activity.getLocation() != null) {
            // Check if the location has changed
            if (persistedActivity.getLocation() == null || !persistedActivity.getLocation().equals(activity.getLocation())) {
                Optional<Location> existingLocation = locationRepository.findByLatitudeAndLongitude(
                        activity.getLocation().getLatitude(), activity.getLocation().getLongitude());

                if (existingLocation.isPresent()) {
                    activity.setLocation(existingLocation.get());
                } else {
                    Location createdLocation = locationService.createLocation(activity.getLocation());
                    activity.setLocation(createdLocation);
                }
                activity.setParc(null);

            }
        }

        // Check if tariff exists based on ID, create new tariff if it doesn't exist
        if (!activity.getTariff().isEmpty()) {
            Set<Tariff> activityTariffList = new HashSet<>();
            for (Tariff tariff : activity.getTariff()) {
                Tariff createdTariff = tariffService.createTariff(tariff);
                activityTariffList.add(createdTariff);
            }
            activity.setTariff(activityTariffList);
        }

        Date currentDate = new Date();
        activity.setLastModificationDate(currentDate);

        // Other validation and business logic

        return activityRepository.save(activity);
    }


    @Override
    public void deleteActivity(Integer id) {
        activityRepository.deleteById(id);
    }
}