package com.ilkaygunel.api;

import com.ilkaygunel.dto.ExpenseCreationInputDto;
import com.ilkaygunel.dto.ExpenseCreationOutputDto;
import com.ilkaygunel.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/expense")
@RequiredArgsConstructor
public class ExpenseTrackerAPI {

    private final ExpenseService expenseService;

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExpenseCreationOutputDto> createExpense(@RequestBody ExpenseCreationInputDto expenseCreationInputDto) {
        ExpenseCreationOutputDto expenseCreationOutputDto = expenseService.saveExpense(expenseCreationInputDto);

        return new ResponseEntity<>(expenseCreationOutputDto, HttpStatus.CREATED);
    }

}
