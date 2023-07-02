package tn.camps.tuncamps.service.parc;

import tn.camps.tuncamps.persistence.entity.parc.Equipment;

import java.util.List;

public interface EquipmentService {
    Equipment createEquipment(Equipment equipment);
    Equipment updateEquipment(Equipment equipment);
    Equipment getEquipmentById(int id);
    List<Equipment> getAllEquipment();
    void deleteEquipment(int id);
}