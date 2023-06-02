package tn.camps.tuncamps.Controllers.Booking;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.camps.tuncamps.Persistence.Entities.Booking.Sale;
import tn.camps.tuncamps.Services.Interfaces.Booking.SaleService;


import java.util.List;

@RestController
@RequestMapping("/sales")
public class SaleController {
    @Autowired
    private SaleService saleService;



    @GetMapping("/getOne/{id}")
    public ResponseEntity<Sale> getSaleById(@PathVariable int id) {
        try {
            Sale sale = saleService.findById(id);
            return ResponseEntity.ok(sale);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Sale>> getAllSales() {
        List<Sale> sales = saleService.findAll();
        return ResponseEntity.ok(sales);
    }

    @PostMapping("/add")
    public ResponseEntity<Sale> createSale(@RequestBody Sale sale) {
        Sale createdSale = saleService.createSale(sale);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSale);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Sale> updateSale(@PathVariable int id, @RequestBody Sale sale) {
        sale.setId(id);
        try {
            Sale updatedSale = saleService.updateSale(sale);
            return ResponseEntity.ok(updatedSale);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable int id) {
        try {
            saleService.deleteSale(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
