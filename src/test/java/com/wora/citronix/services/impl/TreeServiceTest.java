package com.wora.citronix.services.impl;

import com.wora.citronix.exceptions.EntityNotFoundException;
import com.wora.citronix.mappers.TreeMapper;
import com.wora.citronix.models.DTOs.treeDtos.CreateTreeDto;
import com.wora.citronix.repositories.TreeRepository;
import com.wora.citronix.services.inter.IFieldService;
import com.wora.citronix.services.inter.ITreeService;
import net.bytebuddy.implementation.bytecode.Throw;
import org.assertj.core.api.Assertions;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class TreeServiceTest {

    @Mock
    private TreeRepository treeRepository;
    @Mock
    private TreeMapper treeMapper;
    @Mock
    private IFieldService fieldService;
    @InjectMocks
    private TreeService sut;

    @Test
    @DisplayName("save() Should Throw Exception When TreeDto Is Null")
    void save_ShouldThrowExceptionWhenTreeDtoIsNull() {
        CreateTreeDto createTreeDto = null;
        assertThrows(NullPointerException.class , () -> sut.save(createTreeDto));

    }

    @Test
    @DisplayName("save() Should throw Exception When Field Not found")
    void save_ShouldThrowExceptionWhenFieldNotFound(){
        LocalDate plantingDate = LocalDate.parse("2020-06-06");
        Long fieldId = 9999L;

        CreateTreeDto createTreeDto = new CreateTreeDto(plantingDate, fieldId);
        given(fieldService.getFieldEntityById(fieldId)).willThrow(new EntityNotFoundException("field", fieldId));
        assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(() -> sut.save(createTreeDto))
                .withMessage("field with the id " + fieldId +" not found");
    }
}