package tn.camps.tuncamps.service.parc;

import tn.camps.tuncamps.persistence.entity.parc.Parc;

import java.util.List;

public interface ParcService {
    Parc createParc(Parc parc);
    Parc updateParc(Parc parc);
    void deleteParc(int parcId);
    Parc getParcById(int parcId);
    List<Parc> getAllParcs();
}
