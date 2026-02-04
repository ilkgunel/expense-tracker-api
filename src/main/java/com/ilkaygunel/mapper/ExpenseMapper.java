package com.ilkaygunel.mapper;

import com.ilkaygunel.dto.ExpenseOutputDto;
import com.ilkaygunel.dto.ExpenseCreationInputDto;
import com.ilkaygunel.entity.Expense;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {

    ExpenseMapper INSTANCE = Mappers.getMapper(ExpenseMapper.class);

    @Mapping(target = "location", source = "expenseLocation")
    Expense dtoToEntity(ExpenseCreationInputDto expenseCreationInputDto);

    @Mapping(target = "categoryCode", source = "category.code")
    @Mapping(target = "expenseAccountMail", source = "account.email" )
    ExpenseOutputDto entityToDto(Expense expense);

}
