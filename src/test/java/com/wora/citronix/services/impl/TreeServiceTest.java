package com.wora.citronix.services.impl;

import com.wora.citronix.exceptions.EntityNotFoundException;
import com.wora.citronix.exceptions.InsufficientFieldSurfaceException;
import com.wora.citronix.exceptions.PlantingDateException;
import com.wora.citronix.mappers.TreeMapper;
import com.wora.citronix.models.DTOs.treeDtos.CreateTreeDto;
import com.wora.citronix.models.entities.Farm;
import com.wora.citronix.models.entities.Field;
import com.wora.citronix.models.entities.Tree;
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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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
        assertThrows(NullPointerException.class, () -> sut.save(createTreeDto));

    }

    @Test
    @DisplayName("save() Should throw Exception When Field Not found")
    void save_ShouldThrowExceptionWhenFieldNotFound() {
        LocalDate plantingDate = LocalDate.parse("2020-06-06");
        Long fieldId = 9999L;

        CreateTreeDto createTreeDto = new CreateTreeDto(plantingDate, fieldId);
        given(fieldService.getFieldEntityById(fieldId)).willThrow(new EntityNotFoundException("field", fieldId));
        assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(() -> sut.save(createTreeDto))
                .withMessage("field with the id " + fieldId + " not found");
    }

    @Test
    @DisplayName("save() Should throw Exception When Date Is Not Between Five And Seven Months")
    void save_ShouldThrowExceptionWhenDateIsNotBetweenFiveAndSevenMonths() {
        LocalDate plantingDate = LocalDate.parse("2020-02-02");
        LocalDate creationDate = LocalDate.parse("2020-01-01");

        Farm farm = new Farm(1L, "NAME Y", "LOCAL X", 200.0, creationDate, List.of());
        Field field = new Field(1L, "name x", 12.2, farm,  List.of(),List.of());

        given(fieldService.getFieldEntityById(field.getId())).willReturn(field);

        Tree tree = new Tree(1L, plantingDate, field, List.of());

        given(treeMapper.toEntity(any(CreateTreeDto.class))).willReturn(tree);

        CreateTreeDto createTreeDto = new CreateTreeDto(plantingDate, field.getId());

        assertThatExceptionOfType(PlantingDateException.class)
                .isThrownBy(() -> sut.save(createTreeDto))
                .withMessage("The planting date must fall within a range of 5 to 7 months from the current date.");
    }

    @Test
    @DisplayName("save() Should Throw Exception When Maximum Number of Trees Exceeded")
    void save_ShouldThrowExceptionWhenMaxNumberOfTreesExceeded() {
        LocalDate plantingDate = LocalDate.parse("2024-06-06");
        LocalDate creationDate = LocalDate.parse("2023-12-01");

        Farm farm = new Farm(1L, "Farm Y", "Local X", 200.0, creationDate, List.of());
        Field field = new Field(1L, "Field X", 1.0, farm, List.of(), List.of());
        Tree tree = new Tree(null, plantingDate, field, List.of());

        CreateTreeDto createTreeDto = new CreateTreeDto(plantingDate, field.getId());

        given(fieldService.getFieldEntityById(field.getId())).willReturn(field);
        given(treeMapper.toEntity(any(CreateTreeDto.class))).willReturn(tree);
        given(treeRepository.countByFieldId(field.getId())).willReturn(100);

        assertThatExceptionOfType(InsufficientFieldSurfaceException.class)
                .isThrownBy(() -> sut.save(createTreeDto))
                .withMessage("Maximum number of trees (100) exceeded for this field.");
    }


}