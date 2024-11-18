package com.wora.citronix.services.inter;

import com.wora.citronix.models.DTOs.fieldDtos.CreateFieldDto;
import com.wora.citronix.models.DTOs.fieldDtos.FieldDto;
import com.wora.citronix.models.DTOs.fieldDtos.UpdateFieldDto;
import com.wora.citronix.services.GenericService;

public interface IFieldService extends GenericService<CreateFieldDto, UpdateFieldDto, FieldDto, Long> {
}
