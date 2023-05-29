package tn.camps.tuncamps.persistence.entity.ecomerce;

import lombok.Getter;
import lombok.Setter;
import tn.camps.tuncamps.persistence.entity.commun.Brand;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
@Getter
@Setter
public class Product {
   @Id
    private int idProduct;
    private int barcode;
    private String name;
    private double price;

    private int  stockQuantity;
    private String  category;
    private String  owner;
    private Boolean  reorder;







}
