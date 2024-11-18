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

}