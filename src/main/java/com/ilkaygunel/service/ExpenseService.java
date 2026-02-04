package com.ilkaygunel.service;

import com.ilkaygunel.dto.ExpenseOutputDto;
import com.ilkaygunel.dto.ExpenseCreationInputDto;
import com.ilkaygunel.entity.Account;
import com.ilkaygunel.entity.Expense;
import com.ilkaygunel.mapper.ExpenseMapper;
import com.ilkaygunel.repository.AccountRepository;
import com.ilkaygunel.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final AccountRepository accountRepository;
    private final CategoryService categoryService;

    //@CachePut(value = "accountExpenses", key = "#result.expenseAccountMail")
    @CacheEvict(value = "accountExpenses", key = "#result.expenseAccountMail")
    public ExpenseOutputDto saveExpense(ExpenseCreationInputDto expenseCreationInputDto) {

        Expense expense = ExpenseMapper.INSTANCE.dtoToEntity(expenseCreationInputDto);

        expense.setAccount(getCurrentLoggedInAccount());
        expense.setCategory(categoryService.findCategoryByCategoryCode(expenseCreationInputDto.getCategoryCode()));

        Expense savedExpense = expenseRepository.save(expense);

        return ExpenseMapper.INSTANCE.entityToDto(savedExpense);
    }

    @Cacheable(value = "singleAccountExpense", key = "#currentLoggedInAccountEmail + '_' + #expenseId")
    public ExpenseOutputDto getSingleExpense(String currentLoggedInAccountEmail, Long expenseId) {
        System.out.println("Going to the Database for single expense with email --> " + currentLoggedInAccountEmail);

        Optional<Expense> expense = expenseRepository.findByAccountEmailAndId(currentLoggedInAccountEmail, expenseId);

        return expense
                .map(ExpenseMapper.INSTANCE::entityToDto)
                .orElseThrow(() -> new RuntimeException("There is no expense with id and email!"));
    }

    @Cacheable(value = "accountExpenses", key = "#currentLoggedInAccountEmail")
    public List<ExpenseOutputDto> getExpensesOfCurrentUser(String currentLoggedInAccountEmail) {

        System.out.println("Going to the Database for: " + currentLoggedInAccountEmail);

        List<Expense> expenseList = expenseRepository.findByAccountEmail(currentLoggedInAccountEmail);

        return expenseList.stream().map(ExpenseMapper.INSTANCE::entityToDto).collect(Collectors.toList());
    }

    @CacheEvict(value = "accountExpenses", key = "#currentLoggedInAccountEmail")
    public void deleteExpense(String currentLoggedInAccountEmail, Long expenseId) {
        expenseRepository.deleteById(expenseId);
    }

    private Account getCurrentLoggedInAccount() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        String emailOfLoggedInUser = user.getUsername();

        return accountRepository.findByEmail(getCurrentLoggedInAccountEmail())
                .orElseThrow(() -> new RuntimeException("There is no account with the address: " + emailOfLoggedInUser));
    }

    private String getCurrentLoggedInAccountEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return user.getUsername();
    }
}
