package com.ilkaygunel.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "EXPENSE")
@Getter
@Setter
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CATEGORY")
    private String category;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "EXPENSE_DATE")
    private LocalDate expenseDate;

    @Column(name = "ACCOUNT_ID")
    private Long accountId;
}
