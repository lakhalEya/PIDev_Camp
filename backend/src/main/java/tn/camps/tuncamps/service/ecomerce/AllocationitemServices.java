package tn.camps.tuncamps.service.ecomerce;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.camps.tuncamps.persistence.entity.ecomerce.Allocationitem;
import tn.camps.tuncamps.persistence.repository.ecomerce.AllocationitemRepository;

@Service

public class AllocationitemServices extends Abstarctservices<Allocationitem> {
	@Autowired
	private AllocationitemRepository cr;

	@Override
	public Iterable<Allocationitem> findAll() {
		// TODO Auto-generated method stub
		return cr.findAll();
	}

	@Override
	public Optional<Allocationitem> findbyid(int id) {
		// TODO Auto-generated method stub
		return cr.findById(id);
	}

	@Override
	public boolean Delete(int id) {
		// TODO Auto-generated method stub
		cr.deleteById(id);
		return true;
	}

	@Override
	public Allocationitem update(Allocationitem a) {
		// TODO Auto-generated method stub
		return cr.save(a);
	}

	@Override
	public Allocationitem create(Allocationitem a) {
		// TODO Auto-generated method stub
		return cr.save(a);
	}

}
