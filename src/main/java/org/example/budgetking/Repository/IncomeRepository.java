package org.example.budgetking.Repository;

import org.example.budgetking.Model.Income;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface IncomeRepository extends JpaRepository<Income, Long> {
    Page<Income> findAllByEnabledTrue(Pageable pageable);
    Page<Income> findAllByDateBetweenAndEnabledTrue(LocalDate start, LocalDate end, Pageable pageable);
    @Query("SELECT COALESCE(SUM(i.amount), 0.0) FROM Income i")
    Double sumAllIncomes();
}
