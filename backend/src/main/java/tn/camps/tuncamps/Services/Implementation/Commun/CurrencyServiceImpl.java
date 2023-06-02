package tn.camps.tuncamps.Services.Implementation.Commun;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.camps.tuncamps.Persistence.Entities.Commun.Currency;
import tn.camps.tuncamps.Persistence.Repositories.Commun.CurrencyRepository;
import tn.camps.tuncamps.Services.Interfaces.Commun.CurrencyService;

import java.util.List;

@Service
public class CurrencyServiceImpl  implements CurrencyService {
    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    public Currency findById(int id) {
        return currencyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Currency not found with id: " + id));
    }

    @Override
    public List<Currency> findAll() {
        return currencyRepository.findAll();
    }

    @Override
    public Currency createCurrency(Currency currency) {
        return currencyRepository.save(currency);
    }

    @Override
    public Currency updateCurrency(Currency currency) {
        if (!currencyRepository.existsById(currency.getId())) {
            throw new RuntimeException("Currency not found with id: " + currency.getId());
        }
        return currencyRepository.save(currency);
    }

    @Override
    public void deleteCurrency(int id) {
        if (!currencyRepository.existsById(id)) {
            throw new RuntimeException("Currency not found with id: " + id);
        }
        currencyRepository.deleteById(id);
    }
}
