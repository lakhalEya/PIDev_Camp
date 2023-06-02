package tn.camps.tuncamps.TestControllers.Booking;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tn.camps.tuncamps.Controllers.Booking.SaleController;
import tn.camps.tuncamps.Persistence.Entities.Booking.Sale;
import tn.camps.tuncamps.Services.Interfaces.Booking.SaleService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SaleTestController {
    @Mock
    private SaleService saleService;

    @InjectMocks
    private SaleController saleController;

    @Test
    public void testGetSaleById_ReturnsSale() {
        Sale sale = new Sale();
        sale.setId(1);
        when(saleService.findById(1)).thenReturn(sale);

        ResponseEntity<Sale> response = saleController.getSaleById(1);

        verify(saleService, times(1)).findById(1);
        assertSame(HttpStatus.OK, response.getStatusCode());
        assertSame(sale, response.getBody());
    }

    @Test
    public void testGetSaleById_SaleNotFound_ReturnsNotFoundStatus() {
        when(saleService.findById(1)).thenThrow(new RuntimeException("Sale not found"));

        ResponseEntity<Sale> response = saleController.getSaleById(1);

        verify(saleService, times(1)).findById(1);
        assertSame(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testGetAllSales_ReturnsSalesList() {
        List<Sale> sales = new ArrayList<>();
        sales.add(new Sale());
        sales.add(new Sale());
        when(saleService.findAll()).thenReturn(sales);

        ResponseEntity<List<Sale>> response = saleController.getAllSales();

        verify(saleService, times(1)).findAll();
        assertSame(HttpStatus.OK, response.getStatusCode());
        assertSame(sales, response.getBody());
    }

    @Test
    public void testCreateSale_ReturnsCreatedStatusAndSale() {
        Sale sale = new Sale();
        sale.setId(1);
        when(saleService.createSale(any(Sale.class))).thenReturn(sale);

        ResponseEntity<Sale> response = saleController.createSale(new Sale());

        verify(saleService, times(1)).createSale(any(Sale.class));
        assertSame(HttpStatus.CREATED, response.getStatusCode());
        assertSame(sale, response.getBody());
    }

    @Test
    public void testUpdateSale_ReturnsUpdatedSale() {
        Sale sale = new Sale();
        sale.setId(1);
        when(saleService.updateSale(any(Sale.class))).thenReturn(sale);

        ResponseEntity<Sale> response = saleController.updateSale(1, new Sale());

        verify(saleService, times(1)).updateSale(any(Sale.class));
        assertSame(HttpStatus.OK, response.getStatusCode());
        assertSame(sale, response.getBody());
    }

    @Test
    public void testUpdateSale_SaleNotFound_ReturnsNotFoundStatus() {
        when(saleService.updateSale(any(Sale.class))).thenThrow(new RuntimeException("Sale not found"));

        ResponseEntity<Sale> response = saleController.updateSale(1, new Sale());

        verify(saleService, times(1)).updateSale(any(Sale.class));
        assertSame(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testDeleteSale_ReturnsNoContentStatus() {
        ResponseEntity<Void> response = saleController.deleteSale(1);

        verify(saleService, times(1)).deleteSale(1);
        assertSame(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testDeleteSale_SaleNotFound_ReturnsNotFoundStatus() {
        doThrow(new RuntimeException("Sale not found")).when(saleService).deleteSale(1);

        ResponseEntity<Void> response = saleController.deleteSale(1);

        verify(saleService, times(1)).deleteSale(1);
        assertSame(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
}
