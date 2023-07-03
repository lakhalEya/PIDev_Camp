package tn.camps.tuncamps.service.ecomerce;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import tn.camps.tuncamps.persistence.entity.ecomerce.Order;
import tn.camps.tuncamps.persistence.repository.ecomerce.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderServices extends Abstarctservices<Order> {
	@Autowired
	private OrderRepository pr;

	@Override
	public Iterable<Order> findAll() {
		// TODO Auto-generated method stub
		return pr.findAll();
	}

	@Override
	public Optional<Order> findbyid(int id) {
		// TODO Auto-generated method stub
		return pr.findById(id);
	}

	@Override
	public boolean Delete(int id) {
		pr.deleteById(id);
		return true;
	}

	@Override
	public Order update(Order a) {
		// TODO Auto-generated method stub
		return pr.save(a);
	}

	@Override
	public Order create(Order a) {
		// TODO Auto-generated method stub
		return pr.save(a);
	}

	public Order findByCart_IdCart(int id) {
		return pr.findByCart_IdCart(id);
	}
}
