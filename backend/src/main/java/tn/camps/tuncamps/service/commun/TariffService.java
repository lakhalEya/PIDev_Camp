package tn.camps.tuncamps.service.commun;

import org.springframework.stereotype.Service;
import tn.camps.tuncamps.persistence.entity.commun.Tariff;

import java.util.List;


public interface TariffService {

    Tariff findById(int id);
    List<Tariff> findAll();
    Tariff createTariff(Tariff tariff);
    Tariff updateTariff(Tariff tariff);
    void deleteTariff(int id);
}

