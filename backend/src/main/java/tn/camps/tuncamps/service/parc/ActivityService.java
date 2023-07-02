package tn.camps.tuncamps.service.parc;

import tn.camps.tuncamps.persistence.entity.parc.Activity;

import java.util.List;
import java.util.Optional;

public interface ActivityService {
    List<Activity> getAllActivities();
    Optional<Activity> getActivityById(Integer id);
    Activity createActivity(Activity activity);
    Activity updateActivity(Integer id, Activity activity);
    void deleteActivity(Integer id);
}
