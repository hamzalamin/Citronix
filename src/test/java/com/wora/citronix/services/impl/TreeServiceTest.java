package com.wora.citronix.services.impl;

import com.wora.citronix.mappers.TreeMapper;
import com.wora.citronix.models.DTOs.treeDtos.CreateTreeDto;
import com.wora.citronix.services.inter.IFieldService;
import com.wora.citronix.services.inter.ITreeService;
import net.bytebuddy.implementation.bytecode.Throw;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class TreeServiceTest {

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
}