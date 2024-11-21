package com.wora.citronix.services.inter;

import com.wora.citronix.models.DTOs.saleDtos.CreateSaleDto;
import com.wora.citronix.models.DTOs.saleDtos.SaleDto;
import com.wora.citronix.models.DTOs.saleDtos.UpdateSaleDto;
import com.wora.citronix.services.GenericService;

public interface ISaleService extends GenericService<CreateSaleDto, UpdateSaleDto, SaleDto, Long> {
}
