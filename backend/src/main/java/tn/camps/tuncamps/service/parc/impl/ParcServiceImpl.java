package tn.camps.tuncamps.service.parc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.camps.tuncamps.persistence.entity.parc.Parc;
import tn.camps.tuncamps.persistence.repository.parc.ParcRepository;
import tn.camps.tuncamps.service.parc.ParcService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ParcServiceImpl implements ParcService {
    @Autowired
    private ParcRepository parcRepository;

    @Override
    public Parc createParc(Parc parc) {
        return parcRepository.save(parc);
    }

    @Override
    public Parc updateParc(Parc parc) {
        return parcRepository.save(parc);
    }

    @Override
    public void deleteParc(int parcId) {
        parcRepository.deleteById(parcId);
    }

    @Override
    public Parc getParcById(int parcId) {
        return parcRepository.findById(parcId)
                .orElseThrow(() -> new NoSuchElementException("Parc not found with ID: " + parcId));
    }

    @Override
    public List<Parc> getAllParcs() {
        return parcRepository.findAll();
    }
}
