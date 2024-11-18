package com.wora.citronix.services.impl;

import com.wora.citronix.mappers.FieldMapper;
import com.wora.citronix.models.DTOs.fieldDtos.CreateFieldDto;
import com.wora.citronix.repositories.FieldRepository;
import com.wora.citronix.services.inter.IFarmService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
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



}