package com.wora.citronix.services.inter;

import com.wora.citronix.models.DTOs.fieldDtos.CreateFieldDto;
import com.wora.citronix.models.DTOs.fieldDtos.FieldDto;
import com.wora.citronix.models.DTOs.fieldDtos.UpdateFieldDto;
import com.wora.citronix.models.entities.Farm;
import com.wora.citronix.models.entities.Field;
import com.wora.citronix.services.GenericService;

public interface IFieldService extends GenericService<CreateFieldDto, UpdateFieldDto, FieldDto, Long> {
    Double calculateFieldsSurface(Farm farm);
    Field getFieldEntityById(Long id);
}
