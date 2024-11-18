package com.wora.citronix.mappers;

import com.wora.citronix.mappers.api.GenericMapper;
import com.wora.citronix.models.DTOs.treeDtos.TreeDto;
import com.wora.citronix.models.entities.Tree;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TreeMapper extends GenericMapper<Tree, TreeDto> {
    Tree toEntity(TreeDto dto);
    TreeDto toDto(Tree tree);

}
