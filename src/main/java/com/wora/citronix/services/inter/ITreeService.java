package com.wora.citronix.services.inter;

import com.wora.citronix.models.DTOs.treeDtos.CreateTreeDto;
import com.wora.citronix.models.DTOs.treeDtos.TreeDto;
import com.wora.citronix.models.DTOs.treeDtos.UpdateTreeDto;
import com.wora.citronix.services.GenericService;

public interface ITreeService extends GenericService<CreateTreeDto, UpdateTreeDto, TreeDto, Long> {
}
