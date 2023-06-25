package tn.camps.tuncamps.controller.parc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tn.camps.tuncamps.persistence.entity.parc.Equipment;
import tn.camps.tuncamps.service.parc.EquipmentService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EquipmentControllerTest {
/*
    @Mock
    private EquipmentService equipmentService;

    @InjectMocks
    private EquipmentController equipmentController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateEquipment() {
        Equipment equipment = new Equipment();
        equipment.setName("Equipment 1");
        when(equipmentService.createEquipment(equipment)).thenReturn(equipment);

        ResponseEntity<Equipment> response = equipmentController.createEquipment(equipment);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(equipment, response.getBody());

        verify(equipmentService, times(1)).createEquipment(equipment);
        verifyNoMoreInteractions(equipmentService);
    }

    @Test
    public void testGetEquipmentById() {
        int equipmentId = 1;
        Equipment equipment = new Equipment();
        equipment.setId(equipmentId);
        when(equipmentService.getEquipmentById(equipmentId)).thenReturn(equipment);

        ResponseEntity<Equipment> response = equipmentController.getEquipmentById(equipmentId);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(equipment, response.getBody());

        verify(equipmentService, times(1)).getEquipmentById(equipmentId);
        verifyNoMoreInteractions(equipmentService);
    }

    @Test
    public void testGetAllEquipment() {
        Equipment equipment1 = new Equipment();
        equipment1.setId(1);
        Equipment equipment2 = new Equipment();
        equipment2.setId(2);
        List<Equipment> equipmentList = Arrays.asList(equipment1, equipment2);
        when(equipmentService.getAllEquipment()).thenReturn(equipmentList);

        ResponseEntity<List<Equipment>> response = equipmentController.getAllEquipment();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(equipmentList, response.getBody());

        verify(equipmentService, times(1)).getAllEquipment();
        verifyNoMoreInteractions(equipmentService);
    }


    @Test
    public void testUpdateEquipment() {
        int equipmentId = 1;
        Equipment equipment = new Equipment();
        equipment.setId(equipmentId);
        when(equipmentService.updateEquipment(equipment)).thenReturn(equipment);

        ResponseEntity<Equipment> response = equipmentController.updateEquipment(equipmentId, equipment);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(equipment, response.getBody());

        verify(equipmentService, times(1)).updateEquipment(equipment);
        verifyNoMoreInteractions(equipmentService);
    }

    @Test
    public void testDeleteEquipment() {
        int equipmentId = 1;

        ResponseEntity<Void> response = equipmentController.deleteEquipment(equipmentId);

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(equipmentService, times(1)).deleteEquipment(equipmentId);
        verifyNoMoreInteractions(equipmentService);
    }
*/
}