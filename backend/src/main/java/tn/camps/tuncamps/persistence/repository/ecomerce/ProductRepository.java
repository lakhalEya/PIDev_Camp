package tn.camps.tuncamps.persistence.repository.ecomerce;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.camps.tuncamps.persistence.entity.ecomerce.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
