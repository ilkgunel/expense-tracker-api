package com.ilkaygunel.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class AccountExpensesOutputDto implements Serializable {
    private String categoryCode;
    private BigDecimal amount;
    private LocalDate expenseDate;
    private String expenseAccountMail;
}
