package com.ilkaygunel.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class ExpenseOutputDto implements Serializable {
    private Long id;
    private String categoryCode;
    private BigDecimal amount;
    private LocalDate expenseDate;
    private String expenseAccountMail;
    private String location;
}
