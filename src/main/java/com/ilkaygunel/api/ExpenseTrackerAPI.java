package com.ilkaygunel.api;

import com.ilkaygunel.dto.ExpenseCreationInputDto;
import com.ilkaygunel.dto.ExpenseCreationOutputDto;
import com.ilkaygunel.service.ExpenseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/expense")
@RequiredArgsConstructor
@Tag(name = "Expense Management", description = "Services for tracking and managing user expenses.")
public class ExpenseTrackerAPI {

    private final ExpenseService expenseService;

    @Operation(summary = "Expense Creation", description = "Saves a new expense with category, amount and date.")
    @SuppressWarnings("unused")
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExpenseCreationOutputDto> createExpense(@RequestBody ExpenseCreationInputDto expenseCreationInputDto) {
        ExpenseCreationOutputDto expenseCreationOutputDto = expenseService.saveExpense(expenseCreationInputDto);
        return new ResponseEntity<>(expenseCreationOutputDto, HttpStatus.CREATED);
    }

    @Operation(summary = "Expense Deletion", description = "Deletes an existing expense with the id")
    @SuppressWarnings("unused")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id, Principal principal) {
        expenseService.deleteExpense(principal.getName(), id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
