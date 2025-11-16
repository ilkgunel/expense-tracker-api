package com.ilkaygunel.service;

import com.ilkaygunel.dto.ExpenseCreationInputDto;
import com.ilkaygunel.dto.ExpenseCreationOutputDto;
import com.ilkaygunel.entity.Account;
import com.ilkaygunel.entity.Expense;
import com.ilkaygunel.mapper.ExpenseMapper;
import com.ilkaygunel.repository.AccountRepository;
import com.ilkaygunel.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final AccountRepository accountRepository;

    public ExpenseCreationOutputDto saveExpense(ExpenseCreationInputDto expenseCreationInputDto) {

        Expense expense = ExpenseMapper.INSTANCE.dtoToEntity(expenseCreationInputDto);

        expense.setAccount(getCurrentLoggedInAccount());

        Expense savedExpense = expenseRepository.save(expense);

        return ExpenseMapper.INSTANCE.entityToDto(savedExpense);
    }

    private Account getCurrentLoggedInAccount() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        String emailOfLoggedInUser = user.getUsername();

        return accountRepository.findByEmail(emailOfLoggedInUser)
                //.map(Account::getId)
                .orElseThrow(() -> new RuntimeException("There is no account with the address: " + emailOfLoggedInUser));
    }
}
