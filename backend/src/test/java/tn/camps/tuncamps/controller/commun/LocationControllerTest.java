package tn.camps.tuncamps.controller.commun;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tn.camps.tuncamps.persistence.entity.commun.Location;
import tn.camps.tuncamps.service.commun.LocationService;

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

    @Test
    @Order(0)
    void createLocation_shouldReturnCreatedLocation() {
        Location location = new Location();
        location.setName("Location Test");
        when(locationService.saveLocation(any(Location.class))).thenReturn(location);
        ResponseEntity<Location> response = locationController.createLocation(location);
        verify(locationService).saveLocation(location);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(location, response.getBody());
    }

    @Test
    @Order(1)
    void updateLocation_shouldReturnUpdatedLocation() {
        Location location = new Location();
        location.setId(1);
        location.setName("Updated Location");
        when(locationService.saveLocation(any(Location.class))).thenReturn(location);
        ResponseEntity<Location> response = locationController.updateLocation(1, location);
        verify(locationService).saveLocation(location);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(location, response.getBody());
    }

    @Test
    void deleteLocation_shouldReturnNoContent() {
        ResponseEntity<Void> response = locationController.deleteLocation(1);
        verify(locationService).deleteLocation(1);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void getLocationById_shouldReturnLocation() {
        Location location = new Location();
        location.setId(1);
        location.setName("Test Location");
        when(locationService.getLocationById(1)).thenReturn(Optional.of(location));
        ResponseEntity<Location> response = locationController.getLocationById(1);
        verify(locationService).getLocationById(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(location, response.getBody());
    }
    @Test
    void getAllLocations_shouldReturnListOfLocations() {
        Location location1 = new Location();
        location1.setId(1);
        location1.setName("Location 1");
        Location location2 = new Location();
        location2.setId(2);
        location2.setName("Location 2");
        List<Location> locations = Arrays.asList(location1, location2);
        when(locationService.getAllLocations()).thenReturn(locations);
        ResponseEntity<List<Location>> response = locationController.getAllLocations();
        verify(locationService).getAllLocations();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(locations, response.getBody());
    }

}