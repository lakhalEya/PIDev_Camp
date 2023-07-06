package tn.camps.tuncamps.controller.parc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.camps.tuncamps.persistence.entity.parc.Activity;
import tn.camps.tuncamps.service.parc.ActivityService;

import java.util.List;

@RestController
@RequestMapping("/activities")
public class ActivityController {
    private final ActivityService activityService;

    @Autowired
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping
    public ResponseEntity<List<Activity>> getAllActivities() {
        List<Activity> activities = activityService.getAllActivities();
        return ResponseEntity.ok(activities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Activity> getActivityById(@PathVariable("id") Integer id) {
        return activityService.getActivityById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createActivity(@RequestBody Activity activity) {
        try {
            Activity createdActivity = activityService.createActivity(activity);
            return new ResponseEntity<>(createdActivity, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @PostMapping("/{id}")
    @ResponseBody

    public ResponseEntity<Activity> updateActivity(
            @PathVariable("id") Integer id,
            @RequestBody Activity activity
    ) {
        return activityService.getActivityById(id)
                .map(existingActivity -> {
                    Activity updatedActivity = activityService.updateActivity(id, activity);
                    return ResponseEntity.status(HttpStatus.OK).body(updatedActivity);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable("id") Integer id) {
        activityService.deleteActivity(id);
        return ResponseEntity.noContent().build();
    }
}