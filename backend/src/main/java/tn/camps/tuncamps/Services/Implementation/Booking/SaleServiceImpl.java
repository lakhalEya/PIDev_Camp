package tn.camps.tuncamps.Services.Implementation.Booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.camps.tuncamps.Persistence.Entities.Booking.Sale;

import tn.camps.tuncamps.Persistence.Repositories.Booking.SaleRepository;
import tn.camps.tuncamps.Services.Interfaces.Booking.SaleService;

import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Override
    public Sale findById(int id) {
        return saleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sale not found with id: " + id));
    }

    @Override
    public List<Sale> findAll() {
        return saleRepository.findAll();
    }

    @Override
    public Sale createSale(Sale sale) {
        return saleRepository.save(sale);
    }

    @Override
    public Sale updateSale(Sale sale) {
        if (!saleRepository.existsById(sale.getId())) {
            throw new RuntimeException("Sale not found with id: " + sale.getId());
        }
        return saleRepository.save(sale);
    }

    @Override
    public void deleteSale(int id) {
        if (!saleRepository.existsById(id)) {
            throw new RuntimeException("Sale not found with id: " + id);
        }
        saleRepository.deleteById(id);
    }
}
