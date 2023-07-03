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

import tn.camps.tuncamps.persistence.entity.ecomerce.Product;
import tn.camps.tuncamps.service.ecomerce.ProductServices;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class ProductController {
	@Autowired
	private ProductServices cs;

	@GetMapping(path = "/Products")
	public @ResponseBody Iterable<Product> getAll() {
		// Retrieve all products from the database
		return cs.findAll();
	}

	@GetMapping(path = "/Product/{id}")
	private Optional<Product> getProduct(@PathVariable int id) {

		// Retrieve a specific product by its ID from the database
		return cs.findbyid(id);
	}

	@PostMapping(path = "/Product")
	private Product addProduct(@RequestBody Product product) {
		// Add a new product to the database
		if (product != null) {
			return this.cs.create(product);
		} else {
			return null;
		}
	}

	@PutMapping(path = "/Product/{id}")
	private Product updateProduct(@RequestBody Product product, @PathVariable int id) {
		Product prod = this.cs.findbyid(id).get();
		if (prod != null) {
			// Update an existing product in the database
			return this.cs.create(product);
		}
		return null;

	}

	@DeleteMapping(path = "/Product/{id}")
	private boolean deleteProduct(@PathVariable int id) {
		if (this.cs.findbyid(id) != null) {
			return this.cs.Delete(id);
		}
		return false;
	}
}
