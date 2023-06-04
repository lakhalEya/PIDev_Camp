package tn.camps.tuncamps.services.interfaces.booking;



import tn.camps.tuncamps.persistence.entities.booking.Sale;

import java.util.List;

public interface SaleService {
    Sale findById(int id);
    List<Sale> findAll();
    Sale createSale(Sale sale);
    Sale updateSale(Sale sale);
    void deleteSale(int id);
}
