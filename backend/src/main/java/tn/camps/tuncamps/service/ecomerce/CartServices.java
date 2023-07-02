package tn.camps.tuncamps.service.ecomerce;

import java.util.Optional;
import org.springframework.stereotype.Service;


import org.springframework.beans.factory.annotation.Autowired;

import tn.camps.tuncamps.persistence.entity.ecomerce.Cart;
import tn.camps.tuncamps.persistence.entity.ecomerce.Product;
import tn.camps.tuncamps.persistence.repository.ecomerce.CartRepository;
import org.springframework.stereotype.Service;

@Service

public class CartServices extends Abstarctservices<Cart> {
	@Autowired
	private CartRepository cr;

	@Override
	public Iterable<Cart> findAll() {
		// TODO Auto-generated method stub
		return cr.findAll();
	}

	@Override
	public Optional<Cart> findbyid(int id) {
		// TODO Auto-generated method stub
		return cr.findById(id);
	}

	@Override
	public boolean Delete(int id) {
		// TODO Auto-generated method stub
		cr.deleteById(id);
		return true;
	}

	@Override
	public Cart update(Cart a) {
		// TODO Auto-generated method stub
		return  cr.save(a);
	}

	@Override
	public Cart create(Cart a) {
		// TODO Auto-generated method stub
		return cr.save(a);
	}

}
