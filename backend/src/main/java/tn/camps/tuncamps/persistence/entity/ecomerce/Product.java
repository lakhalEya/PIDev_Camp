package tn.camps.tuncamps.persistence.entity.ecomerce;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
@Getter
@Setter
public class Product {
   @Id
    private int idProduct;
}
