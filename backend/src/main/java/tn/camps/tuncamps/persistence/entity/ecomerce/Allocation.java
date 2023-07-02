package tn.camps.tuncamps.persistence.entity.ecomerce;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "allocation")

public class Allocation implements Serializable {
	public Allocation() {
		super();
	}
	public Allocation(int id, List<Allocationitem> allocationitems) {
		super();
		this.id = id;
		Allocationitems = allocationitems;
	}
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@OneToMany(mappedBy = "allocation", orphanRemoval = true)
	private List<Allocationitem> Allocationitems;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
		return "Allocation [id=" + id + ", Allocationitems=" + Allocationitems + "]";
	}

}