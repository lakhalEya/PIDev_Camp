package tn.camps.tuncamps.controller.parc;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;
import tn.camps.tuncamps.persistence.entity.commun.Location;
import tn.camps.tuncamps.persistence.entity.commun.Tariff;
import tn.camps.tuncamps.persistence.entity.parc.Activity;
import tn.camps.tuncamps.service.parc.ActivityService;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ActivityControllerTest {
    private MockMvc mockMvc;

    @Mock
    private ActivityService activityService;

    @InjectMocks
    private ActivityController activityController;

    private ObjectMapper objectMapper = new ObjectMapper();


    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(activityController).build();

    }

    @Test
    public void getAllActivities_ShouldReturnListOfActivities() throws Exception {
        Currency currency = Currency.getInstance("USD");

        Activity activity = new Activity(1, "Activity 1", new Location(1, "Location 1", "Description 1", true, 1.0, 1.0), new Date(), new Date(), "Organizer 1", new Tariff(1, 10.0, "Tariff 1", "Tariff Description", 60/*, currency*/));
        Activity activity2 = new Activity(2, "Activity 2", new Location(2, "Location 2", "Description 2", true, 2.0, 2.0), new Date(), new Date(), "Organizer 2", new Tariff(2, 20.0, "Tariff 2", "Tariff Description", 120/*, currency*/));
        List<Activity> activities = Arrays.asList(activity, activity2);

        when(activityService.getAllActivities()).thenReturn(activities);

        mockMvc.perform(get("/activities"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Activity 1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Activity 2"));

        verify(activityService, times(1)).getAllActivities();
        verifyNoMoreInteractions(activityService);
    }

    @Test
    public void getActivityById_ExistingId_ShouldReturnActivity() throws Exception {
        Currency currency = Currency.getInstance("USD");

        Activity activity = new Activity(1, "Activity 1", new Location(1, "Location 1", "Description 1", true, 1.0, 1.0), new Date(), new Date(), "Organizer 1", new Tariff(1, 10.0, "Tariff 1", "Tariff Description", 60/*, currency*/));

        when(activityService.getActivityById(1)).thenReturn(Optional.of(activity));

        mockMvc.perform(get("/activities/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Activity 1"));

        verify(activityService, times(1)).getActivityById(1);
        verifyNoMoreInteractions(activityService);
    }


    @Test
    public void getActivityById_NonExistingId_ShouldReturnNotFound() throws Exception {
        when(activityService.getActivityById(1)).thenReturn(Optional.empty());

        mockMvc.perform(get("/activities/1"))
                .andExpect(status().isNotFound());

        verify(activityService, times(1)).getActivityById(1);
        verifyNoMoreInteractions(activityService);
    }

    @Test
    public void createActivity_ValidActivity_ShouldReturnCreatedActivity() throws Exception {
        Currency currency = Currency.getInstance("USD");
        Activity activity = new Activity(1, "Activity 1", new Location(1, "Location 1", "Description 1", true, 1.0, 1.0), new Date(), new Date(), "Organizer 1", new Tariff(1, 10.0, "Tariff 1", "Tariff Description", 60/*, currency*/));
        Activity createdActivity = new Activity(1, "Activity 1", new Location(2, "Location 2", "Description 2", true, 2.0, 2.0), new Date(), new Date(), "Organizer 2", new Tariff(2, 20.0, "Tariff 2", "Tariff Description", 120/*, currency*/));

        when(activityService.createActivity(any(Activity.class))).thenReturn(createdActivity);

        mockMvc.perform(post("/activities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectToJson(activity)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Activity 1"));

        verify(activityService, times(1)).createActivity(any(Activity.class));
        verifyNoMoreInteractions(activityService);
    }

//    @Test
//    public void updateActivity_ExistingId_ValidActivity_ShouldReturnUpdatedActivity() throws Exception {
//        // Create test data
//        Currency currency = Currency.getInstance("USD");
//        Activity activity = new Activity(1, "Activity 1", new Location(1, "Location 1", "Description 1", true, 1.0, 1.0), new Date(), new Date(), "Organizer 1", new Tariff(1, 10.0, "Tariff 1", "Tariff Description", 60, currency));
//        Activity updatedActivity = new Activity(1, "Updated Activity 1", new Location(1, "Location 1", "Description 1", true, 1.0, 1.0), new Date(), new Date(), "Organizer 2", new Tariff(1, 10.0, "Tariff 1", "Tariff Description", 60, currency));
//
//        // Mock activity service
//        when(activityService.getActivityById(1)).thenReturn(Optional.of(activity));
//        when(activityService.updateActivity(1, activity)).thenReturn(updatedActivity);
//
//        // Perform the request and assert the response
//        mockMvc.perform(post("/activities/1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(convertObjectToJson(activity)))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//               .andExpect(jsonPath("$.id").value(1))
//               .andExpect(jsonPath("$.name").value("Updated Activity 1"));
//
//        // Verify the activity service interactions
//        verify(activityService, times(1)).getActivityById(1);
//         verify(activityService, times(1)).updateActivity(1, activity);
//        verifyNoMoreInteractions(activityService);
//    }

    @Test
    public void updateActivity_NonExistingId_ShouldReturnNotFound() throws Exception {
        // Create test data
        Activity activity = new Activity(1, "Activity 1", new Location(1, "Location 1", "Description 1", true, 1.0, 1.0), new Date(), new Date(), "Organizer 1", new Tariff(1, 10.0, "Tariff 1", "Tariff Description", 60/*, Currency.getInstance("USD")*/));

        // Mock activity service
        when(activityService.getActivityById(1)).thenReturn(Optional.empty());

        // Perform the request and assert the response
        mockMvc.perform(post("/activities/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectToJson(activity)))
                .andExpect(status().isNotFound());

        // Verify the activity service interactions
        verify(activityService, times(1)).getActivityById(1);
        verifyNoMoreInteractions(activityService);
    }

    @Test
    public void deleteActivity_ExistingId_ShouldReturnNoContent() throws Exception {
        mockMvc.perform(delete("/activities/1"))
                .andExpect(status().isNoContent());

        verify(activityService, times(1)).deleteActivity(1);
        verifyNoMoreInteractions(activityService);
    }
    @Test
    public void deleteActivity_NonExistingId_ShouldReturnNotFound() throws Exception {
        // Set up the mock activity service to throw an exception
        doThrow(NotFoundException.class).when(activityService).deleteActivity(1);

        // Perform the request and assert the response status
        mockMvc.perform(delete("/activities/1"))
                .andExpect(status().isNotFound());

        // Verify the activity service interactions
        verify(activityService, times(1)).deleteActivity(1);
        verifyNoMoreInteractions(activityService);
    }

    private String convertObjectToJson(Object object) throws Exception {
        return objectMapper.writeValueAsString(object);
    }
}
@ResponseStatus(HttpStatus.NOT_FOUND)
class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}