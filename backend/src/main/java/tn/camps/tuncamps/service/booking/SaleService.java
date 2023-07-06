package tn.camps.tuncamps.service.booking;



import tn.camps.tuncamps.persistence.entity.booking.Sale;

import java.util.List;

public interface SaleService {
    Sale findById(int id);
    List<Sale> findAll();
    Sale createSale(Sale sale);
    Sale updateSale(Sale sale);
    void deleteSale(int id);
}
