package tn.camps.tuncamps.service.parc;

public interface ParcStatisticService {
    long getParcCount();

    long getEnabledParcCount();

    String getMostUsedCategory();
}
