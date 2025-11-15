package com.ilkaygunel.service;

import com.ilkaygunel.dto.ExpenseCreationInputDto;
import com.ilkaygunel.dto.ExpenseCreationOutputDto;
import com.ilkaygunel.entity.Expense;
import com.ilkaygunel.mapper.ExpenseMapper;
import com.ilkaygunel.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseCreationOutputDto saveExpense(ExpenseCreationInputDto expenseCreationInputDto) {

        Expense expense = ExpenseMapper.INSTANCE.dtoToEntity(expenseCreationInputDto);
        Expense savedExpense = expenseRepository.save(expense);

        return ExpenseMapper.INSTANCE.entityToDto(savedExpense);
    }
}
