package tn.camps.tuncamps.controller.parc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tn.camps.tuncamps.persistence.entity.parc.Parc;
import tn.camps.tuncamps.service.parc.ParcService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ParcControllerTest {

    @Mock
    private ParcService parcService;

    @InjectMocks
    private ParcController parcController;


    @Test
    void createParc_shouldReturnCreatedParc() {
        // Create a sample Parc object
        Parc parc = new Parc();
        parc.setName("Parc Test");

        // Mock the service method
        when(parcService.createParc(any(Parc.class))).thenReturn(parc);

        // Call the controller method
        ResponseEntity<Parc> response = parcController.createParc(parc);

        // Verify the service method was called with the correct parameter
        verify(parcService).createParc(parc);

        // Verify the response status code and the returned Parc object
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(parc, response.getBody());
    }

    @Test
    void updateParc_shouldReturnUpdatedParc() {
        // Create a sample Parc object
        Parc parc = new Parc();
        parc.setIdParc(1);
        parc.setName("Updated Parc");

        // Mock the service method
        when(parcService.updateParc(any(Parc.class))).thenReturn(parc);

        // Call the controller method
        ResponseEntity<Parc> response = parcController.updateParc(1, parc);

        // Verify the service method was called with the correct parameter
        verify(parcService).updateParc(parc);

        // Verify the response status code and the returned Parc object
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(parc, response.getBody());
    }

    @Test
    void deleteParc_shouldReturnNoContent() {
        // Call the controller method
        ResponseEntity<Void> response = parcController.deleteParc(1);

        // Verify the service method was called with the correct parameter
        verify(parcService).deleteParc(1);

        // Verify the response status code
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void getParcById_shouldReturnParc() {
        // Create a sample Parc object
        Parc parc = new Parc();
        parc.setIdParc(1);
        parc.setName("Test Parc");

        // Mock the service method
        when(parcService.getParcById(1)).thenReturn(parc);

        // Call the controller method
        ResponseEntity<Parc> response = parcController.getParcById(1);

        // Verify the service method was called with the correct parameter
        verify(parcService).getParcById(1);

        // Verify the response status code and the returned Parc object
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(parc, response.getBody());
    }


    @Test
    void getAllParcs_shouldReturnListOfParcs() {
        // Create sample Parc objects
        Parc parc1 = new Parc();
        parc1.setIdParc(1);
        parc1.setName("Parc 1");

        Parc parc2 = new Parc();
        parc2.setIdParc(2);
        parc2.setName("Parc 2");

        List<Parc> parcs = Arrays.asList(parc1, parc2);

        // Mock the service method
        when(parcService.getAllParcs()).thenReturn(parcs);

        // Call the controller method
        ResponseEntity<List<Parc>> response = parcController.getAllParcs();

        // Verify the service method was called
        verify(parcService).getAllParcs();

        // Verify the response status code and the returned list of Parcs
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(parcs, response.getBody());
    }
}