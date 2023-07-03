package tn.camps.tuncamps.persistence.repository.ecomerce;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.camps.tuncamps.persistence.entity.ecomerce.CartItem;

public interface CartitemProdRepository extends JpaRepository<CartItem, Integer> {
}
