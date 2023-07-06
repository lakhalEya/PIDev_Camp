package tn.camps.tuncamps.persistence.repository.ecomerce;



import org.springframework.data.jpa.repository.JpaRepository;

import tn.camps.tuncamps.persistence.entity.ecomerce.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
	 Order findByCartIdCart(int idCart);
}
