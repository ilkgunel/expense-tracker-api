package com.ilkaygunel.mapper;

import com.ilkaygunel.dto.ExpenseCreationInputDto;
import com.ilkaygunel.dto.ExpenseCreationOutputDto;
import com.ilkaygunel.entity.Expense;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {

    ExpenseMapper INSTANCE = Mappers.getMapper(ExpenseMapper.class);

    Expense dtoToEntity(ExpenseCreationInputDto expenseCreationInputDto);
    ExpenseCreationOutputDto entityToDto(Expense expense);

}
