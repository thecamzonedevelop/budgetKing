package org.example.budgetking.Repository;

import org.example.budgetking.Model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<Budget, Long> {}
