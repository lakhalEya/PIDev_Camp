package tn.camps.tuncamps.persistence.entity.ecomerce;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name = "CartItem")

public class CartItem implements Serializable {
	public CartItem() {
		super();
	}

	public CartItem(int id, double price, int quantity, Cart cart, Product product) {
		super();
		this.id = id;
		this.price = price;
		Quantity = quantity;
		this.cart = cart;
		this.product = product;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private double price;
	private int Quantity;
	@JsonIgnore
	@ManyToOne(optional = true)

	private Cart cart;

	@ManyToOne

	private Product product;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		CartItem other = (CartItem) obj;

		return id == other.id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return Quantity;
	}

	public void setQuantity(int quantity) {
		Quantity = quantity;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "CartItem [id=" + id + ", price=" + price + ", Quantity=" + Quantity + ", cart=" + cart + ", product="
				+ product + "]";
	}
}
