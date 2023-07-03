package tn.camps.tuncamps.controller.ecomerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.camps.tuncamps.persistence.entity.ecomerce.Allocation;
import tn.camps.tuncamps.persistence.entity.ecomerce.Allocationitem;
import tn.camps.tuncamps.persistence.entity.ecomerce.CartItem;
import tn.camps.tuncamps.persistence.entity.ecomerce.Product;
import tn.camps.tuncamps.service.ecomerce.AllocationServices;
import tn.camps.tuncamps.service.ecomerce.AllocationitemServices;
import tn.camps.tuncamps.service.ecomerce.ProductServices;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class AllocationitemController {
	@Autowired
	private AllocationServices cs;
	@Autowired
	private ProductServices ps;
	@Autowired
	private AllocationitemServices cis;

	@GetMapping(path = "/locitems")
	public @ResponseBody Iterable<Allocationitem> getAll() {
		return cis.findAll();
	}

	@GetMapping(path = "/locitem/{id}")
	private Allocationitem getlocitem(@PathVariable int id) {
	
			return cis.findbyid(id).get();

	

	}

	
	@PostMapping(path = "/locitem/{id}")
	public Allocationitem addlocitem(@RequestBody Allocationitem locitem, @PathVariable int id) {
		if (locitem != null) {

			Allocation allocation = cs.findbyid(id).get();
			if (allocation == null) {
				return null;
			}

			Product p = locitem.getProduct();
			if (p != null) {
				Product prod = ps.findbyid(p.getIdProduct()).get();
				locitem.setPrice(prod.getPrice() * locitem.getQuantity());
			}
			locitem.setAllocation(allocation);
			return this.cis.create(locitem);
		}
		return null;

	}

	@PutMapping(path = { "/locitem" })
	private Allocationitem updatelocitem(@RequestBody Allocationitem locitem) {

		return this.cis.create(locitem);
	}

	@DeleteMapping(path = "/locitem/{id}")
	private boolean Deletelocitem(@PathVariable int id) {
		return this.cis.Delete(id);

	}

}
