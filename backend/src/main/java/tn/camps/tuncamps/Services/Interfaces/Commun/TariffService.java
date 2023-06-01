package tn.camps.tuncamps.Services.Interfaces.Commun;

import tn.camps.tuncamps.Persistence.Entities.Commun.Tariff;

import java.util.List;

public interface TariffService {
    Tariff findById(int id);
    List<Tariff> findAll();
    Tariff createTariff(Tariff tariff);
    Tariff updateTariff(Tariff tariff);
    void deleteTariff(int id);
}
