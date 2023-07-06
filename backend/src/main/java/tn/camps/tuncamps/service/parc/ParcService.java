package tn.camps.tuncamps.service.parc;

import tn.camps.tuncamps.persistence.entity.parc.Parc;
import tn.camps.tuncamps.persistence.entity.parc.ParcCategory;

import java.util.List;

public interface ParcService {
    Parc createParc(Parc parc);

    Parc updateParc(int id, Parc parc);

    void deleteParc(int parcId);
    Parc getParcById(int parcId);

    List<Parc> getParcByCountry(String country);

    List<Parc> getAllParcsFiltered(String filterBy, String filterValue);

    List<Parc> getParcByDisponibility(Parc.ParcStatus status);

    List<Parc> getAllParcsSorted(String sortBy);

    List<Parc> getAllParcs();

    Parc enableParc(int id);

    Parc disableParc(int id);

    List<Parc> getParcByCategory(ParcCategory category);

    List<Parc> getParcByAmenities(String amenities);
}
