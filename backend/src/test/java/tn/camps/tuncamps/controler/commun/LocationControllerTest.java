package tn.camps.tuncamps.controler.commun;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tn.camps.tuncamps.persistence.entity.commun.Location;
import tn.camps.tuncamps.service.LocationService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class LocationControllerTest {

    @Mock
    private LocationService locationService;

    @InjectMocks
    private LocationController locationController;

    @Test
    public void testGetAllLocations() {
        // Prepare
        Location location1 = new Location(1, "Location 1", "Description 1", true, 42.123, 24.567);
        Location location2 = new Location(2, "Location 2", "Description 2", false, 39.876, 12.345);
        List<Location> locations = Arrays.asList(location1, location2);

        when(locationService.getAllLocations()).thenReturn(locations);

        // Execute
        ResponseEntity<List<Location>> response = locationController.getAllLocations();

        // Verify
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(locations);

        verify(locationService, times(1)).getAllLocations();
    }



    @Test
    public void testGetLocationById() {
        // Prepare
        Location location = new Location(1, "Location 1", "Description 1", true, 42.123, 24.567);
        Optional<Location> optionalLocation = Optional.of(location);

        when(locationService.getLocationById(eq(1))).thenReturn(optionalLocation);
        when(locationService.getLocationById(eq(2))).thenReturn(Optional.empty());

        // Execute
        ResponseEntity<Location> response1 = locationController.getLocationById(1);
        ResponseEntity<Location> response2 = locationController.getLocationById(2);

        // Verify
        assertThat(response1.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response1.getBody()).isEqualTo(location);

        assertThat(response2.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response2.getBody()).isNull();

        verify(locationService, times(1)).getLocationById(eq(1));
        verify(locationService, times(1)).getLocationById(eq(2));
    }


}