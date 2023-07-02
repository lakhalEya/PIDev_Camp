package tn.camps.tuncamps.controller.ecomerce;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import tn.camps.tuncamps.persistence.entity.ecomerce.Cart;
import tn.camps.tuncamps.persistence.entity.ecomerce.CartItem;
import tn.camps.tuncamps.persistence.entity.ecomerce.Product;
import tn.camps.tuncamps.persistence.repository.ecomerce.CartitemProdRepository;
import tn.camps.tuncamps.service.ecomerce.CartItemsServices;
import tn.camps.tuncamps.service.ecomerce.CartServices;
import tn.camps.tuncamps.service.ecomerce.ProductServices;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class CartItemController {
	@Autowired
	private CartServices cs;
	@Autowired
	private ProductServices ps;
	@Autowired
	private CartItemsServices cis;

	@GetMapping(path = "/items")
	public @ResponseBody Iterable<CartItem> getAll() {
		return cis.findAll();
	}

	@GetMapping(path = "/item/{id}")
	private CartItem getItem(@PathVariable int id) {
	
			return cis.findbyid(id).get();



	}
/*
	@GetMapping(path = "/item/{idi}/{idc}")
	public boolean removitem(@PathVariable int idi, @PathVariable int idc) {
		CartItem item = this.cis.findbyid(idi).get();
		if (item != null) {

			Cart cart = cs.findbyid(idc).get();
			if (cart == null) {
				return false;
			}

			if (cart.getCartItems().contains(item)) {

				cart.getCartItems().remove(item);

				cs.create(cart);

				return true;
			} else {

				return false;
			}
		}
		return false;
	}
*/
	@PostMapping(path = "/item/{id}")
	public CartItem addItem(@RequestBody CartItem cart, @PathVariable int id) {
		if (cart != null) {

			Cart mycart = cs.findbyid(id).get();
			if (mycart == null) {
				return null;
			}

			Product p = cart.getProduct();
			if (p != null) {
				Product prod = ps.findbyid(p.getIdProduct()).get();
				cart.setPrice(prod.getPrice() * cart.getQuantity());
			}
			cart.setCart(mycart);
			return this.cis.create(cart);
		}
		return null;

	}

	@PutMapping(path = { "/item" })
	private CartItem updateItem(@RequestBody CartItem Cart) {

		return this.cis.create(Cart);
	}

	@DeleteMapping(path = "/item/{id}")
	private boolean DeleteItem(@PathVariable int id) {
		return this.cis.Delete(id);

	}

}
