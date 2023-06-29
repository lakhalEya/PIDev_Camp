package tn.camps.tuncamps.controller.commun;

import org.junit.Before;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
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

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(locationController).build();
    }
    @Test
    public void testCreateLocation_Success() throws Exception {
        // Prepare mock data
        Location location = new Location();
        location.setId(1);
        location.setName("Location 1");
        location.setLatitude(40.7128);
        location.setLongitude(-74.0060);

        // Mock the service method
        Mockito.when(locationService.createLocation(Mockito.any(Location.class))).thenReturn(location);

        // Perform the POST request
        mockMvc.perform(MockMvcRequestBuilders.post("/locations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Location 1\",\"latitude\":40.7128,\"longitude\":-74.0060}"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Location 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.latitude").value(40.7128))
                .andExpect(MockMvcResultMatchers.jsonPath("$.longitude").value(-74.0060));

        // Verify the service method is called
        Mockito.verify(locationService).createLocation(Mockito.any(Location.class));
    }

    @Test
    public void testCreateLocation_DuplicateCoordinates() throws Exception {
        // Prepare mock data
        Location location = new Location();
        location.setName("Location 2");
        location.setLatitude(40.7128);
        location.setLongitude(-74.0060);

        // Mock the service method to throw an IllegalArgumentException
        Mockito.when(locationService.createLocation(Mockito.any(Location.class)))
                .thenThrow(new IllegalArgumentException("A location with the same latitude and longitude already exists."));

        // Perform the POST request
        mockMvc.perform(MockMvcRequestBuilders.post("/locations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Location 2\",\"latitude\":40.7128,\"longitude\":-74.0060}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("A location with the same latitude and longitude already exists."));

        // Verify the service method is called
        Mockito.verify(locationService).createLocation(Mockito.any(Location.class));
    }

    @Test
    @Order(1)
    void updateLocation_shouldReturnUpdatedLocation() {
        Location location = new Location();
        location.setId(1);
        location.setName("Updated Location");
        when(locationService.createLocation(any(Location.class))).thenReturn(location);
        ResponseEntity<Location> response = locationController.updateLocation(1, location);
        verify(locationService).createLocation(location);
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