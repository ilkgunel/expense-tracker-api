package com.ilkaygunel.mapper;

import com.ilkaygunel.dto.CategoryOutputDto;
import com.ilkaygunel.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryOutputDto entityToDto(Category category);

}
