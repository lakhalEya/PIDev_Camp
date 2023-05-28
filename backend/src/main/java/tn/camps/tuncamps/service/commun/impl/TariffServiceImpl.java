package tn.camps.tuncamps.service.commun.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.camps.tuncamps.persistence.entity.commun.Tariff;
import tn.camps.tuncamps.persistence.repository.commun.TariffRepository;
import tn.camps.tuncamps.service.commun.TariffService;

import java.util.List;

@Service
public class TariffServiceImpl implements TariffService {
    @Autowired
    private TariffRepository tariffRepository;

    @Override
    public Tariff findById(int id) {
        return tariffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tariff not found with id: " + id));
    }

    @Override
    public List<Tariff> findAll() {
        return tariffRepository.findAll();
    }

    @Override
    public Tariff createTariff(Tariff tariff) {
        return tariffRepository.save(tariff);
    }

    @Override
    public Tariff updateTariff(Tariff tariff) {
        if (!tariffRepository.existsById(tariff.getId())) {
            throw new RuntimeException("Tariff not found with id: " + tariff.getId());
        }
        return tariffRepository.save(tariff);
    }

    @Override
    public void deleteTariff(int id) {
        if (!tariffRepository.existsById(id)) {
            throw new RuntimeException("Tariff not found with id: " + id);
        }
        tariffRepository.deleteById(id);
    }
}