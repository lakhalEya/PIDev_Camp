package tn.camps.tuncamps.controller.commun;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tn.camps.tuncamps.persistence.entity.commun.Location;
import tn.camps.tuncamps.service.LocationService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class LocationControllerTest {

    @Mock
    private LocationService locationService;

    @InjectMocks
    private LocationController locationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    void createLocation_shouldReturnCreatedLocation() {
        // Create a sample Location object
        Location location = new Location();
        location.setName("Location Test");

        // Mock the service method
        when(locationService.saveLocation(any(Location.class))).thenReturn(location);

        // Call the controller method
        ResponseEntity<Location> response = locationController.createLocation(location);

        // Verify the service method was called with the correct parameter
        verify(locationService).saveLocation(location);

        // Verify the response status code and the returned Location object
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(location, response.getBody());
    }



    @Test
    void getLocationById_shouldReturnLocation() {
        // Create a sample Location object
        Location location = new Location();
        location.setId(1);
        location.setName("Test Location");

        // Mock the service method
        when(locationService.getLocationById(1)).thenReturn(Optional.of(location));

        // Call the controller method
        ResponseEntity<Location> response = locationController.getLocationById(1);

        // Verify the service method was called with the correct parameter
        verify(locationService).getLocationById(1);

        // Verify the response status code and the returned Location object
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(location, response.getBody());
    }
    @Test
    void getAllLocations_shouldReturnListOfLocations() {
        // Create sample Location objects
        Location location1 = new Location();
        location1.setId(1);
        location1.setName("Location 1");

        Location location2 = new Location();
        location2.setId(2);
        location2.setName("Location 2");

        List<Location> locations = Arrays.asList(location1, location2);

        // Mock the service method
        when(locationService.getAllLocations()).thenReturn(locations);

        // Call the controller method
        ResponseEntity<List<Location>> response = locationController.getAllLocations();

        // Verify the service method was called
        verify(locationService).getAllLocations();

        // Verify the response status code and the returned list of Locations
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(locations, response.getBody());
    }

}