package org.example.budgetking.Repository;

import org.example.budgetking.DTO.CategoryTotal;
import org.example.budgetking.Model.Income;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface IncomeRepository extends JpaRepository<Income, Long> {
    Page<Income> findAllByEnabledTrue(Pageable pageable);
    Page<Income> findAllByDateBetweenAndEnabledTrue(LocalDate start, LocalDate end, Pageable pageable);
    // In IncomeRepository.java
    @Query("SELECT SUM(i.amount) FROM Income i WHERE i.enabled = true")
    Double sumAllIncomes();

    @Query("SELECT new org.example.budgetking.DTO.CategoryTotal(i.category, SUM(i.amount)) FROM Income i WHERE i.enabled = true GROUP BY i.category")
    List<CategoryTotal> sumIncomesByCategory();
}
