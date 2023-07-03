package tn.camps.tuncamps.persistence.entity.ecomerce;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity

@Table(name = "Product")

public class Product implements Serializable {
	public Product() {
		super();
	}

	public Product(int idProduct, int barcode, String name, double price, int stockQuantity, String category,
			String owner, Boolean reorder, String description, String image, TypeProprietaire typeProprietaire,
			List<CartItem> cartItems, List<Allocationitem> allocationitems) {
		super();
		this.idProduct = idProduct;
		this.barcode = barcode;
		this.name = name;
		this.price = price;
		this.stockQuantity = stockQuantity;
		this.category = category;
		this.owner = owner;
		this.reorder = reorder;
		this.description = description;
		this.image = image;
		this.typeProprietaire = typeProprietaire;
		this.cartItems = cartItems;
		Allocationitems = allocationitems;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idProduct;
	private int barcode;
	private String name;
	private double price;
	private int stockQuantity;
	private String category;
	private String owner;
	private Boolean reorder;
	private String description;
	private String image;
	@Enumerated(EnumType.STRING)
	private TypeProprietaire typeProprietaire;

	public enum TypeProprietaire {
		PP, PM
	}

	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
	private List<CartItem> cartItems;
	@OneToMany(mappedBy = "allocation", cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Allocationitem> Allocationitems;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Product other = (Product) obj;

		return idProduct == other.idProduct;
	}

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public int getBarcode() {
		return barcode;
	}

	public void setBarcode(int barcode) {
		this.barcode = barcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Boolean getReorder() {
		return reorder;
	}

	public void setReorder(Boolean reorder) {
		this.reorder = reorder;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public TypeProprietaire getTypeProprietaire() {
		return typeProprietaire;
	}

	public void setTypeProprietaire(TypeProprietaire typeProprietaire) {
		this.typeProprietaire = typeProprietaire;
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public List<Allocationitem> getAllocationitems() {
		return Allocationitems;
	}

	public void setAllocationitems(List<Allocationitem> allocationitems) {
		Allocationitems = allocationitems;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Product [idProduct=" + idProduct + ", barcode=" + barcode + ", name=" + name + ", price=" + price
				+ ", stockQuantity=" + stockQuantity + ", category=" + category + ", owner=" + owner + ", reorder="
				+ reorder + ", description=" + description + ", image=" + image + ", typeProprietaire="
				+ typeProprietaire + ", cartItems=" + cartItems + ", Allocationitems=" + Allocationitems + "]";
	}
}
