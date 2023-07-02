package tn.camps.tuncamps.service.ecomerce;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import tn.camps.tuncamps.persistence.entity.ecomerce.Product;
import tn.camps.tuncamps.persistence.repository.ecomerce.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductServices extends Abstarctservices<Product> {
	@Autowired
	private ProductRepository pr;

	@Override
	public Iterable<Product> findAll() {
		// TODO Auto-generated method stub
		return pr.findAll();
	}

	@Override
	public Optional<Product> findbyid(int id) {
		// TODO Auto-generated method stub
		return pr.findById(id);
	}

	@Override
	public boolean Delete(int id) {
		pr.deleteById(id);
		return true;
	}

	@Override
	public Product update(Product a) {
		// TODO Auto-generated method stub
		return pr.save(a);
	}

	@Override
	public Product create(Product a) {
		// TODO Auto-generated method stub
		return pr.save(a);
	}

}
