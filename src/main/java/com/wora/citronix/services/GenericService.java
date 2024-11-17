package com.wora.citronix.services;

import java.util.List;

public interface GenericService<CREATE_DTO, UPDATE_DTO, DTO, ID>{
    DTO save(CREATE_DTO dto);
    DTO findById(ID id);
    DTO update(UPDATE_DTO dto, ID id);
    List<DTO> findAll(Integer pageNumber, Integer size);
    void delete(ID id);
}