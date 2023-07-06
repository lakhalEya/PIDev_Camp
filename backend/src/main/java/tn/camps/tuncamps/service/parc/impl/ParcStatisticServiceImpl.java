package tn.camps.tuncamps.service.parc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.camps.tuncamps.persistence.entity.parc.Parc;
import tn.camps.tuncamps.persistence.entity.parc.ParcCategory;
import tn.camps.tuncamps.persistence.repository.commun.LocationRepository;
import tn.camps.tuncamps.persistence.repository.parc.ParcRepository;
import tn.camps.tuncamps.service.commun.LocationService;
import tn.camps.tuncamps.service.parc.ParcStatisticService;

@Service

public class ParcStatisticServiceImpl implements ParcStatisticService {

    private final ParcRepository parcRepository;
    private final LocationRepository locationRepository;
    private final LocationService locationService;

    int parcCount;
    int enabledParcCount;
    String mostUsedCategory;

    @Autowired
    public ParcStatisticServiceImpl(ParcRepository parcRepository, LocationRepository locationRepository, LocationService locationService) {
        this.parcRepository = parcRepository;
        this.locationRepository = locationRepository;
        this.locationService = locationService;
    }

    @Override
    public long getParcCount()
    {
        return parcRepository.count();
    }

    @Override
    public long getEnabledParcCount()
    {
        return parcRepository.countByStatus(Parc.ParcStatus.ENABLED);
    }


    @Override
    public String getMostUsedCategory()
    {
        return parcRepository.findMostUsedCategory().toString();
    }


}

