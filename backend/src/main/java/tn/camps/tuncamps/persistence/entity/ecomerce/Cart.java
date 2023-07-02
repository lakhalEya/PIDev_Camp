package tn.camps.tuncamps.persistence.entity.ecomerce;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name = "Cart")

public class Cart implements Serializable{
	public Cart() {
		super();
	}
	public Cart(int idCart, Boolean promotionAdded, Boolean empty, Double total, List<CartItem> cartItems,
			Order order) {
		super();
		this.idCart = idCart;
		this.promotionAdded = promotionAdded;
		this.empty = empty;
		this.total = total;
		this.cartItems = cartItems;
		this.order = order;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idCart;
	private Boolean promotionAdded;
	private Boolean empty;
	private Double total;

	@OneToMany(mappedBy = "cart", orphanRemoval = true)
	private List<CartItem> cartItems;
	@JsonIgnore
	@OneToOne(mappedBy = "cart", optional = true, orphanRemoval = true)
	private Order order;
	public int getIdCart() {
		return idCart;
	}
	public void setIdCart(int idCart) {
		this.idCart = idCart;
	}
	public Boolean getPromotionAdded() {
		return promotionAdded;
	}
	public void setPromotionAdded(Boolean promotionAdded) {
		this.promotionAdded = promotionAdded;
	}
	public Boolean getEmpty() {
		return empty;
	}
	public void setEmpty(Boolean empty) {
		this.empty = empty;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public List<CartItem> getCartItems() {
		return cartItems;
	}
	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Cart [idCart=" + idCart + ", promotionAdded=" + promotionAdded + ", empty=" + empty + ", total=" + total
				+ ", cartItems=" + cartItems + ", order=" + order + "]";
	}
}