package com.ilkaygunel.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class ExpenseCreationInputDto {

    private String categoryCode;
    private BigDecimal amount;
    private LocalDate expenseDate;
    private String expenseLocation;

}
