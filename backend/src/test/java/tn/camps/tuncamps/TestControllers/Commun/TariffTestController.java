package tn.camps.tuncamps.TestControllers.Commun;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tn.camps.tuncamps.Controllers.Commun.TariffController;
import tn.camps.tuncamps.Persistence.Entities.Commun.Tariff;
import tn.camps.tuncamps.Services.Interfaces.Commun.TariffService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TariffControllerTest {
    @Mock
    private TariffService tariffService;

    @InjectMocks
    private TariffController tariffController;

    @Test
    public void testGetTariffById_ReturnsTariff() {
        Tariff tariff = new Tariff();
        tariff.setId(1);
        when(tariffService.findById(1)).thenReturn(tariff);

        ResponseEntity<Tariff> response = tariffController.getTariffById(1);

        verify(tariffService, times(1)).findById(1);
        assertSame(HttpStatus.OK, response.getStatusCode());
        assertSame(tariff, response.getBody());
    }

    @Test
    public void testGetTariffById_TariffNotFound_ReturnsNotFoundStatus() {
        when(tariffService.findById(1)).thenThrow(new RuntimeException("Tariff not found"));

        ResponseEntity<Tariff> response = tariffController.getTariffById(1);

        verify(tariffService, times(1)).findById(1);
        assertSame(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testGetAllTariffs_ReturnsTariffsList() {
        List<Tariff> tariffs = new ArrayList<>();
        tariffs.add(new Tariff());
        tariffs.add(new Tariff());
        when(tariffService.findAll()).thenReturn(tariffs);

        ResponseEntity<List<Tariff>> response = tariffController.getAllTariffs();

        verify(tariffService, times(1)).findAll();
        assertSame(HttpStatus.OK, response.getStatusCode());
        assertSame(tariffs, response.getBody());
    }

    @Test
    public void testCreateTariff_ReturnsCreatedStatusAndTariff() {
        Tariff tariff = new Tariff();
        tariff.setId(1);
        when(tariffService.createTariff(any(Tariff.class))).thenReturn(tariff);

        ResponseEntity<Tariff> response = tariffController.createTariff(new Tariff());

        verify(tariffService, times(1)).createTariff(any(Tariff.class));
        assertSame(HttpStatus.CREATED, response.getStatusCode());
        assertSame(tariff, response.getBody());
    }

    @Test
    public void testUpdateTariff_ReturnsUpdatedTariff() {
        Tariff tariff = new Tariff();
        tariff.setId(1);
        when(tariffService.updateTariff(any(Tariff.class))).thenReturn(tariff);

        ResponseEntity<Tariff> response = tariffController.updateTariff(1, new Tariff());

        verify(tariffService, times(1)).updateTariff(any(Tariff.class));
        assertSame(HttpStatus.OK, response.getStatusCode());
        assertSame(tariff, response.getBody());
    }

    @Test
    public void testUpdateTariff_TariffNotFound_ReturnsNotFoundStatus() {
        when(tariffService.updateTariff(any(Tariff.class))).thenThrow(new RuntimeException("Tariff not found"));

        ResponseEntity<Tariff> response = tariffController.updateTariff(1, new Tariff());

        verify(tariffService, times(1)).updateTariff(any(Tariff.class));
        assertSame(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testDeleteTariff_ReturnsNoContentStatus() {
        ResponseEntity<Void> response = tariffController.deleteTariff(1);

        verify(tariffService, times(1)).deleteTariff(1);
        assertSame(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testDeleteTariff_TariffNotFound_ReturnsNotFoundStatus() {
        doThrow(new RuntimeException("Tariff not found")).when(tariffService).deleteTariff(1);

        ResponseEntity<Void> response = tariffController.deleteTariff(1);

        verify(tariffService, times(1)).deleteTariff(1);
        assertSame(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
}