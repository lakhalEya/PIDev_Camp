package tn.camps.tuncamps.service.commun;

import org.springframework.stereotype.Service;
import tn.camps.tuncamps.persistence.entity.commun.Tariff;

import java.util.List;
import java.util.Optional;


public interface TariffService {

    Optional<Tariff> findById(int id);
    List<Tariff> findAll();
    Tariff createTariff(Tariff tariff);
    Tariff updateTariff(int id, Tariff tariff);
    void deleteTariff(int id);
}

