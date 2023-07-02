package tn.camps.tuncamps.persistence.entity.ecomerce;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name = "Order")

public class Order implements Serializable {
	public Order() {
		super();
	}

	public Order(int id, Date shipped, String ordered, Double total, String shippingAdresse, String status, Cart cart) {
		super();
		this.id = id;
		this.shipped = shipped;
		Ordered = ordered;
		Total = total;
		this.shippingAdresse = shippingAdresse;
		this.status = status;
		this.cart = cart;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private Date shipped;
	private String Ordered;
	private Double Total;
	private String shippingAdresse;
	private String status;
	// Other properties and mappings

	@OneToOne
	@JoinColumn(name = "cart_id")
	private Cart cart;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getShipped() {
		return shipped;
	}

	public void setShipped(Date shipped) {
		this.shipped = shipped;
	}

	public String getOrdered() {
		return Ordered;
	}

	public void setOrdered(String ordered) {
		Ordered = ordered;
	}

	public Double getTotal() {
		return Total;
	}

	public void setTotal(Double total) {
		Total = total;
	}

	public String getShippingAdresse() {
		return shippingAdresse;
	}

	public void setShippingAdresse(String shippingAdresse) {
		this.shippingAdresse = shippingAdresse;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", shipped=" + shipped + ", Ordered=" + Ordered + ", Total=" + Total
				+ ", shippingAdresse=" + shippingAdresse + ", status=" + status + ", cart=" + cart + "]";
	}

}
