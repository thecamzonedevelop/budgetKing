package org.example.budgetking.DTO;

import lombok.Data;

import java.util.List;

@Data
public class BudgetDTO {
    private Long id;
    private List<IncomeDTO> incomes;
    private List<ExpenseDTO> expenses;
}
