package tn.camps.tuncamps.Services.Interfaces.Booking;



import tn.camps.tuncamps.Persistence.Entities.Booking.Sale;

import java.util.List;

public interface SaleService {
    Sale findById(int id);
    List<Sale> findAll();
    Sale createSale(Sale sale);
    Sale updateSale(Sale sale);
    void deleteSale(int id);
}
