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

import tn.camps.tuncamps.persistence.entity.ecomerce.Cart;
import tn.camps.tuncamps.service.ecomerce.CartServices;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class CartController {
	@Autowired
	private CartServices cs;

	@GetMapping(path = "/Carts")
	public @ResponseBody Iterable<Cart> getAll() {
		return cs.findAll();
	}

	@GetMapping(path = "/Cart/{id}")
	private Optional<Cart> getCart(@PathVariable int id) {
		return cs.findbyid(id);

	}

	@PostMapping(path = "/Cart")
	private Cart getCart(@RequestBody Cart crt) {
		return cs.create(crt);

	}

	@GetMapping(path = "/vidercart/{id}")
	private Boolean vidercart(@PathVariable int id) {
		Cart cart = cs.findbyid(id).orElse(null);
		if (cart == null) {
			return false; // Cart not found
		}

		// Remove all items from the cart
	//	cart.getCartItems().clear();

		// Save the cart
		try {
			cs.update(cart);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false; // Error occurred while saving the cart
		}
	}

	@PutMapping(path = { "/Cart" })
	private Cart updateCarts(@RequestBody Cart Cart) {

		return this.cs.create(Cart);
	}

	@DeleteMapping(path = "/Cart/{id}")
	private boolean DeleteCart(@PathVariable int id) {
		return this.cs.Delete(id);

	}

}
