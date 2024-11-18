package com.wora.citronix.services.impl;

import com.wora.citronix.exceptions.EntityNotFoundException;
import com.wora.citronix.exceptions.InsufficientFarmSurfaceException;
import com.wora.citronix.mappers.FieldMapper;
import com.wora.citronix.models.DTOs.fieldDtos.CreateFieldDto;
import com.wora.citronix.models.entities.Farm;
import com.wora.citronix.models.entities.Field;
import com.wora.citronix.repositories.FieldRepository;
import com.wora.citronix.services.inter.IFarmService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FieldServiceTest {

    @Mock
    private FieldRepository fieldRepository;
    @Mock
    private FieldMapper fieldMapper;
    @Mock
    private IFarmService farmService;
    @InjectMocks
    private FieldService sut;


    @Test
    @DisplayName("create() Should Throw Exception When CompetitionDto Is Null")
    void create_ShouldThrowExceptionWhenCompetitionDtoIsNull(){
        CreateFieldDto createFieldDto = null;
        assertThrows(NullPointerException.class, () -> sut.save(createFieldDto));
    }

    @Test
    @DisplayName("save() Should Throw Exception When Farm Not Found")
    void save_ShouldThrowExceptionWhenFarmNotFound() {
        CreateFieldDto createFieldDto = new CreateFieldDto("Field 1", 20.0, 1L);

        when(farmService.getFarmEntityById(1L)).thenThrow(new IllegalArgumentException("Farm not found"));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> sut.save(createFieldDto));

        assertEquals("Farm not found", exception.getMessage());
    }

    @Test
    @DisplayName("save() Should Throw Exception When Field Surface Exceeds Half Farm Surface")
    void save_ShouldThrowExceptionWhenFieldSurfaceExceedsHalfFarmSurface() {
        CreateFieldDto createFieldDto = new CreateFieldDto("Large Field", 60.0, 1L);
        Farm farm = new Farm(1L, "Farm 1", "Localization X", 100.0, LocalDate.now(), List.of());
        Field field = new Field();
        field.setName("Large Field");
        field.setSurface(60.0);

        when(farmService.getFarmEntityById(1L)).thenReturn(farm);
        when(fieldMapper.toEntity(createFieldDto)).thenReturn(field);

        InsufficientFarmSurfaceException exception =
                assertThrows(InsufficientFarmSurfaceException.class, () -> sut.save(createFieldDto));

        assertEquals("Field must be under 50% of the farm surface", exception.getMessage());
    }

    @Test
    @DisplayName("save() Should Throw Exception When Farm Is Not Found")
    void save_ShouldThrowExceptionWhenFarmIsNotFound() {
        CreateFieldDto createFieldDto = new CreateFieldDto("Field 1", 5.0, 99L);

        when(farmService.getFarmEntityById(99L)).thenThrow(new EntityNotFoundException("Farm", 99L));

        EntityNotFoundException exception =
                assertThrows(EntityNotFoundException.class, () -> sut.save(createFieldDto));

        assertEquals("Farm with the id 99 not found", exception.getMessage());
    }







}