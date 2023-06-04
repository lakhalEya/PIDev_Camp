package tn.camps.tuncamps.services.interfaces.commun;

import tn.camps.tuncamps.persistence.entities.commun.Currency;

import java.util.List;

public interface CurrencyService {
    Currency findById(int id);
    List<Currency> findAll();
    Currency createCurrency(Currency currency);
    Currency updateCurrency(Currency currency);
    void deleteCurrency(int id);
}
