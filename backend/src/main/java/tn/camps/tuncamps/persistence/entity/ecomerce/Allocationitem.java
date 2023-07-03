package tn.camps.tuncamps.persistence.entity.ecomerce;

import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "allocationitem")

public class Allocationitem {
	public Allocationitem() {
		super();
	}

	public Allocationitem(int id, Date dateDebut, Date dateFin, double price, int quantity, Allocation allocation,
			Product product) {
		super();
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.price = price;
		Quantity = quantity;
		this.allocation = allocation;
		this.product = product;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int id;
	public Date dateDebut;
	public Date dateFin;
	public double price;
	public int Quantity;

	@JsonIgnore
	@ManyToOne(optional = true)
	@JoinColumn(name = "allocation_id", nullable = true)
	private Allocation allocation;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Allocationitem other = (Allocationitem) obj;

		return id == other.id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
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

	public Allocation getAllocation() {
		return allocation;
	}

	public void setAllocation(Allocation allocation) {
		this.allocation = allocation;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Allocationitem [id=" + id + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", price=" + price
				+ ", Quantity=" + Quantity + ", allocation=" + allocation + ", product=" + product + "]";
	}

}
