package com.ilkaygunel.mapper;

import com.ilkaygunel.dto.AccountExpensesOutputDto;
import com.ilkaygunel.dto.ExpenseCreationInputDto;
import com.ilkaygunel.dto.ExpenseCreationOutputDto;
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
    @Mapping(target = "expenseId", source = "id")
    @Mapping(target = "expenseLocation", source = "location")
    ExpenseCreationOutputDto entityToDto(Expense expense);

    @Mapping(target = "expenseAccountMail", source = "account.email" )
    @Mapping(target = "categoryCode", source = "category.code")
    AccountExpensesOutputDto expenseEntityToAccountExpensesOutputDto(Expense expense);

}
