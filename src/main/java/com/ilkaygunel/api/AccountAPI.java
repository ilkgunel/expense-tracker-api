package com.ilkaygunel.api;

import com.ilkaygunel.dto.AccountExpensesOutputDto;
import com.ilkaygunel.dto.AccountInputDTO;
import com.ilkaygunel.dto.AccountOutputDTO;
import com.ilkaygunel.service.AccountService;
import com.ilkaygunel.service.ExpenseService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@Data
public class AccountAPI {

    private final AccountService accountService;
    private final ExpenseService expenseService;

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountOutputDTO> createUser(@RequestBody AccountInputDTO accountInputDTO) {
        AccountOutputDTO accountOutputDTO = accountService.saveAccount(accountInputDTO);
        return new ResponseEntity<>(accountOutputDTO, HttpStatus.CREATED);
    }

    @GetMapping(value = "/expenses", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AccountExpensesOutputDto>> accountExpenses() {
        return new ResponseEntity<>(expenseService.getExpensesOfCurrentUser(), HttpStatus.OK);
    }

}
