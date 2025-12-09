package com.ilkaygunel.repository;

import com.ilkaygunel.entity.Expense;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends CrudRepository<Expense, Long> {
    List<Expense> findByAccountEmail(String email);

    Optional<Expense> findByAccountEmailAndId(String email, Long id);
}
