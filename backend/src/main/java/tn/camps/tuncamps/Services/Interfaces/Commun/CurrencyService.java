package tn.camps.tuncamps.Services.Interfaces.Commun;

import tn.camps.tuncamps.Persistence.Entities.Commun.Currency;

import java.util.List;

public interface CurrencyService {
    Currency findById(int id);
    List<Currency> findAll();
    Currency createCurrency(Currency currency);
    Currency updateCurrency(Currency currency);
    void deleteCurrency(int id);
}
