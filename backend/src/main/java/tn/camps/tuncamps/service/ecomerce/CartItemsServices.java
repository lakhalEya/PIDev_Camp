package tn.camps.tuncamps.service.ecomerce;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.camps.tuncamps.persistence.entity.ecomerce.CartItem;
import tn.camps.tuncamps.persistence.repository.ecomerce.CartitemProdRepository;

@Service

public class CartItemsServices extends Abstarctservices<CartItem> {
	@Autowired
	private CartitemProdRepository cr;

	@Override
	public Iterable<CartItem> findAll() {
		// TODO Auto-generated method stub
		return cr.findAll();
	}

	@Override
	public Optional<CartItem> findbyid(int id) {
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
	public CartItem update(CartItem a) {
		// TODO Auto-generated method stub
		return cr.save(a);
	}

	@Override
	public CartItem create(CartItem a) {
		// TODO Auto-generated method stub
		return cr.save(a);
	}

}
