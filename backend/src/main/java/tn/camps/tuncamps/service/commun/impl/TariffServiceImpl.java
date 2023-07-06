package tn.camps.tuncamps.service.commun.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.camps.tuncamps.persistence.entity.commun.Tariff;
import tn.camps.tuncamps.persistence.repository.commun.TariffRepository;
import tn.camps.tuncamps.service.commun.TariffService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TariffServiceImpl implements TariffService {
    @Autowired
    private TariffRepository tariffRepository;

    @Override
    public Optional<Tariff> findById(int id) {
        return tariffRepository.findById(id);
    }

    @Override
    public List<Tariff> findAll() {
        return tariffRepository.findAll();
    }

    @Override
    @Transactional
    public Tariff createTariff(Tariff tariff) {
        // Check mandatory fields
        if (tariff.getPrice() <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0.");
        }
        if (tariff.getDuration() <= 0) {
            throw new IllegalArgumentException("Duration must be greater than 0.");
        }
        if (tariff.getCurrency() == null) {
            throw new IllegalArgumentException("Currency is mandatory.");
        }
        if (tariff.getValidityStartDate() == null || tariff.getApplicableStartTime() == null) {
            throw new IllegalArgumentException("Validity start date and applicable start time are mandatory.");
        }

        // Check date relationships
        Date currentDate = new Date();
        if (tariff.getValidityStartDate().after(tariff.getValidityEndDate())) {
            throw new IllegalArgumentException("Validity start date cannot be after validity end date.");
        }
        if (tariff.getValidityStartDate().before(currentDate)) {
            throw new IllegalArgumentException("Validity start date cannot be in the past.");
        }
        if (tariff.getApplicableStartTime().isAfter(tariff.getApplicableEndTime())) {
            throw new IllegalArgumentException("Applicable start time cannot be after applicable end time.");
        }

        // Check participants relationship
        if (tariff.getMinimumParticipants() > tariff.getMaximumParticipants()) {
            throw new IllegalArgumentException("Minimum participants cannot be greater than maximum participants.");
        }

        // Save the tariff
        return tariffRepository.saveAndFlush(tariff);
    }

    @Override
    @Transactional
    public Tariff updateTariff(int id , Tariff updatedTariff) {
        Tariff existingTariff = tariffRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tariff not found."));

        if (updatedTariff.getPrice() != existingTariff.getPrice()) {
            if (updatedTariff.getPrice() <= 0) {
                throw new IllegalArgumentException("Price must be greater than 0.");
            }
            existingTariff.setPrice(updatedTariff.getPrice());
        }

        if (updatedTariff.getDuration() != existingTariff.getDuration()) {
            if (updatedTariff.getDuration() <= 0) {
                throw new IllegalArgumentException("Duration must be greater than 0.");
            }
            existingTariff.setDuration(updatedTariff.getDuration());
        }

        if (!updatedTariff.getCurrency().equals(existingTariff.getCurrency())) {
            if (updatedTariff.getCurrency() == null) {
                throw new IllegalArgumentException("Currency is mandatory.");
            }
            existingTariff.setCurrency(updatedTariff.getCurrency());
        }

        if (!updatedTariff.getValidityStartDate().equals(existingTariff.getValidityStartDate())) {
            if (updatedTariff.getValidityStartDate() == null) {
                throw new IllegalArgumentException("Validity start date is mandatory.");
            }
            if (updatedTariff.getValidityStartDate().after(updatedTariff.getValidityEndDate())) {
                throw new IllegalArgumentException("Validity start date cannot be after validity end date.");
            }
            Date currentDate = new Date();
            if (updatedTariff.getValidityStartDate().before(currentDate)) {
                throw new IllegalArgumentException("Validity start date cannot be in the past.");
            }
            existingTariff.setValidityStartDate(updatedTariff.getValidityStartDate());
        }

        if (!updatedTariff.getApplicableStartTime().equals(existingTariff.getApplicableStartTime())) {
            if (updatedTariff.getApplicableStartTime() == null) {
                throw new IllegalArgumentException("Applicable start time is mandatory.");
            }
            if (updatedTariff.getApplicableStartTime().isAfter(updatedTariff.getApplicableEndTime())) {
                throw new IllegalArgumentException("Applicable start time cannot be after applicable end time.");
            }
            existingTariff.setApplicableStartTime(updatedTariff.getApplicableStartTime());
        }

        if (updatedTariff.getMinimumParticipants() != existingTariff.getMinimumParticipants()) {
            if (updatedTariff.getMinimumParticipants() > updatedTariff.getMaximumParticipants()) {
                throw new IllegalArgumentException("Minimum participants cannot be greater than maximum participants.");
            }
            existingTariff.setMinimumParticipants(updatedTariff.getMinimumParticipants());
        }

        if (updatedTariff.getMaximumParticipants() != existingTariff.getMaximumParticipants()) {
            if (updatedTariff.getMinimumParticipants() > updatedTariff.getMaximumParticipants()) {
                throw new IllegalArgumentException("Minimum participants cannot be greater than maximum participants.");
            }
            existingTariff.setMaximumParticipants(updatedTariff.getMaximumParticipants());
        }

        // Save the updated tariff
        return tariffRepository.save(existingTariff);
    }


    @Override
    public void deleteTariff(int id) {
        if (!tariffRepository.existsById(id)) {
            throw new RuntimeException("Tariff not found with id: " + id);
        }
        tariffRepository.deleteById(id);
    }
}