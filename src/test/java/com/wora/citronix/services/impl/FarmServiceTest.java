package com.wora.citronix.services.impl;

import com.wora.citronix.exceptions.EntityNotFoundException;
import com.wora.citronix.mappers.FarmMapper;
import com.wora.citronix.models.DTOs.farmDtos.FarmDto;
import com.wora.citronix.models.entities.Farm;
import com.wora.citronix.repositories.FarmRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FarmServiceTest {
    @Mock
    private FarmRepository farmRepository;

    @Mock
    private FarmMapper farmMapper;

    @InjectMocks
    private FarmService sut;


    @Test
    @DisplayName("findById() Should Throw Exception When ID is null")
    void findById_ShouldThrowExceptionWhenIdIsNull() {
        Long id = null;

        when(farmRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> sut.findById(id));

    }

    @Test
    @DisplayName("findById() Should Throw Exception When ID is invalid")
    void findById_ShouldThrowExceptionWhenIdIsInvalid() {
        Long id = 9999L;

        when(farmRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> sut.findById(id));

    }

    @Test
    @DisplayName("findById() Should Return Entity When ID is Valid")
    void findById_ShouldReturnEntityWhenIdIsValid() {
        Long id = 1L;
        LocalDate creationDate = LocalDate.parse("2020-12-12");
        Farm farm = new Farm(id, "azura", "Ait melloul", 200.10, creationDate, List.of(),List.of());
        FarmDto farmDto = new FarmDto(id, "azura", "Ait melloul", 200.10, creationDate, List.of(),List.of());

        when(farmRepository.findById(id)).thenReturn(Optional.of(farm));
        when(farmMapper.toDto(farm)).thenReturn(farmDto);

        FarmDto result = sut.findById(id);
        assertNotNull(result);
        assertEquals(farmDto, result);
        verify(farmRepository).findById(id);
        verify(farmMapper).toDto(farm);
    }

    @Test
    @DisplayName("search() Should Return Matching Farms When All Filters Are Provided")
    void search_ShouldReturnMatchingFarmsWhenAllFiltersAreProvided(){
        String name = "Farm A";
        String localization = "Location X";
        Double surface = 100.0;
        LocalDate creationDate = LocalDate.of(2023, 1, 1);

        List<Farm> farms = List.of(new Farm(1L, name, localization, surface, creationDate, List.of(),List.of()));
        List<FarmDto> expectedDtos = List.of(new FarmDto(1L, name, localization, surface, creationDate, List.of(),List.of()));

        when(farmRepository.search(name, localization, surface, creationDate)).thenReturn(farms);
        when(farmMapper.toDto(any(Farm.class))).thenReturn(expectedDtos.getFirst());

        List<FarmDto> result = sut.search(name, localization, surface, creationDate);
        assertNotNull(result);
        assertEquals(expectedDtos, result);
        verify(farmRepository).search(name, localization, surface, creationDate);
        verify(farmMapper, times(farms.size())).toDto(any(Farm.class));

    }

    @Test
    @DisplayName("search() Should Return Empty List When No Farms Match")
    void search_ShouldReturnEmptyListWhenNoFarmsMatch() {
        String name = "Nonexistent Farm";
        String localization = "Nonexistent Location";
        Double surface = 500.0;
        LocalDate creationDate = LocalDate.of(2020, 1, 1);

        when(farmRepository.search(name, localization, surface, creationDate)).thenReturn(Collections.emptyList());

        List<FarmDto> result = sut.search(name, localization, surface, creationDate);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(farmRepository).search(name, localization, surface, creationDate);
        verifyNoInteractions(farmMapper);
    }


    @Test
    @DisplayName("search() Should Return Matching Farms When Some Filters Are Null")
    void search_ShouldReturnMatchingFarmsWhenSomeFiltersAreNull() {
        String name = null;
        String localization = "Location Y";
        Double surface = null;
        LocalDate creationDate = null;

        List<Farm> farms = List.of(new Farm(2L, "Farm B", "Location Y", 200.0, null, List.of(),List.of()));
        List<FarmDto> expectedDtos = List.of(new FarmDto(2L, "Farm B", "Location Y", 200.0, null, List.of(), List.of()));

        when(farmRepository.search(name, localization, surface, creationDate)).thenReturn(farms);
        when(farmMapper.toDto(any(Farm.class))).thenReturn(expectedDtos.getFirst());

        List<FarmDto> result = sut.search(name, localization, surface, creationDate);

        assertNotNull(result);
        assertEquals(expectedDtos, result);
        verify(farmRepository).search(name, localization, surface, creationDate);
        verify(farmMapper, times(farms.size())).toDto(any(Farm.class));
    }

}