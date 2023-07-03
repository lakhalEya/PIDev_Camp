package tn.camps.tuncamps.controller.ecomerce;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

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
import tn.camps.tuncamps.persistence.entity.ecomerce.CartItem;
import tn.camps.tuncamps.persistence.entity.ecomerce.Order;
import tn.camps.tuncamps.service.ecomerce.CartItemsServices;
import tn.camps.tuncamps.service.ecomerce.CartServices;
import tn.camps.tuncamps.service.ecomerce.OrderServices;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class OrderController {
	@Autowired
	private OrderServices cs;
	@Autowired
	private CartServices css;

	@GetMapping(path = "/Orders")
	public @ResponseBody Iterable<Order> getAll() {
		return cs.findAll();
	}

	@GetMapping(path = "/Order/{id}")
	private Optional<Order> getOrder(@PathVariable int id) {
		return cs.findbyid(id);

	}

	@PostMapping(path = { "/Order/{id}" })
	private Order addOrder(@RequestBody Order Order, @PathVariable int id) {
		Cart carte = this.css.findbyid(id).get();

		if (Order != null && cs.findByCart_IdCart(id) == null) {
			Order.setCart(carte);
			// return Order ;
			Order.setTotal(carte.getTotal());
			return this.cs.create(Order);
		} else {

			return null;
		}

	}

	@PutMapping(path = { "/Order" })
	private Order updateOrders(@RequestBody Order Order) {

		return this.cs.create(Order);
	}

	@DeleteMapping(path = "/Order/{id}")
	private boolean DeleteOrder(@PathVariable int id) {
		return this.cs.Delete(id);

	}

}
