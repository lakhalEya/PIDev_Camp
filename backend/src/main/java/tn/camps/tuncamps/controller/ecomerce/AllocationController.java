package tn.camps.tuncamps.controller.ecomerce;

import java.util.Optional;

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
import tn.camps.tuncamps.service.ecomerce.AllocationServices;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class
AllocationController {
	@Autowired
	private AllocationServices cs;

	@GetMapping(path = "/Allocations")
	public @ResponseBody Iterable<Allocation> getAll() {
		return cs.findAll();
	}

	@GetMapping(path = "/Allocation/{id}")
	private Optional<Allocation> getAllocation(@PathVariable int id) {
		return cs.findbyid(id);

	}

	@PostMapping(path = "/Allocation")
	private Allocation getAllocation(@RequestBody Allocation crt) {
		return cs.create(crt);

	}

	@GetMapping(path = "/viderAllocation/{id}")
	private Boolean viderAllocation(@PathVariable int id) {
		Allocation Allocation = cs.findbyid(id).orElse(null);
		if (Allocation == null) {
			return false; // Allocation not found
		}

		Allocation.getAllocationitems().clear();

		try {
			cs.update(Allocation);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false; // Error occurred while saving the Allocation
		}
	}

	@PutMapping(path = { "/Allocation" })
	private Allocation updateAllocations(@RequestBody Allocation Allocation) {

		return this.cs.create(Allocation);
	}

	@DeleteMapping(path = "/Allocation/{id}")
	private boolean DeleteAllocation(@PathVariable int id) {
		return this.cs.Delete(id);

	}

}
