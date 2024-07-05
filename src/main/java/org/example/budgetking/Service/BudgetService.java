package org.example.budgetking.Service;

import org.example.budgetking.DTO.BudgetDTO;
import org.example.budgetking.DTO.CombinedBudgetItemDTO;
import org.example.budgetking.Model.Budget;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface BudgetService {
    BudgetDTO getBudget();
    Page<CombinedBudgetItemDTO> getCombinedBudgetItems(Pageable pageable);
    BudgetDTO resetBudget();
    BudgetDTO createBudget(Budget budget);
    Double getTotalMoney();

    Page<CombinedBudgetItemDTO> getBudgetItemsBetweenDates(LocalDate start, LocalDate end, String type, Pageable pageable);
}
