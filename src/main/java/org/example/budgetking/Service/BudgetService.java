package org.example.budgetking.Service;

import org.example.budgetking.DTO.BudgetDTO;
import org.example.budgetking.Model.Budget;

public interface BudgetService {
    BudgetDTO getBudget();
    BudgetDTO resetBudget();
    BudgetDTO createBudget(Budget budget);
    Double getTotalMoney();
}
