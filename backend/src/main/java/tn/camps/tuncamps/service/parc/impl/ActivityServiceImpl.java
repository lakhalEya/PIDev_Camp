package tn.camps.tuncamps.service.parc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.camps.tuncamps.persistence.entity.parc.Activity;
import tn.camps.tuncamps.persistence.repository.parc.ActivityRepository;
import tn.camps.tuncamps.service.parc.ActivityService;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityRepository activityRepository;

    @Override
    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    @Override
    public Optional<Activity> getActivityById(Integer id) {
        return activityRepository.findById(id);
    }

    @Override
    public Activity createActivity(Activity activity) {
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