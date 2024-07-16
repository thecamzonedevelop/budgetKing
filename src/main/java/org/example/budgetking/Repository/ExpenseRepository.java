package org.example.budgetking.Repository;

import org.example.budgetking.DTO.CategoryTotal;
import org.example.budgetking.Model.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.time.LocalDate;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    Page<Expense> findAllByEnabledTrue(Pageable pageable);
    Page<Expense> findAllByDateBetweenAndEnabledTrue(LocalDate start, LocalDate end, Pageable pageable);

    @Query("SELECT SUM(e.amount) FROM Expense e WHERE e.enabled = true")
    Double sumAllExpenses();

    @Query("SELECT new org.example.budgetking.DTO.CategoryTotal(e.category, SUM(e.amount)) FROM Expense e WHERE e.enabled = true GROUP BY e.category")
    List<CategoryTotal> sumExpensesByCategory();
}
