package org.example.budgetking.Service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.budgetking.DTO.BudgetDTO;
import org.example.budgetking.DTO.Mapper.BudgetMapper;
import org.example.budgetking.Model.Budget;
import org.example.budgetking.Repository.BudgetRepository;
import org.example.budgetking.Repository.ExpenseRepository;
import org.example.budgetking.Repository.IncomeRepository;
import org.example.budgetking.Service.BudgetService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BudgetServiceImpl implements BudgetService {
    private final BudgetRepository budgetRepository;
    private final IncomeRepository incomeRepository;
    private final ExpenseRepository expenseRepository;
    private final BudgetMapper budgetMapper;


    public BudgetDTO getBudget() {
        Budget budget = budgetRepository.findById(1L).orElse(new Budget());
        return budgetMapper.toDto(budget);
    }


    public BudgetDTO resetBudget() {
        incomeRepository.deleteAll();
        expenseRepository.deleteAll();
        Budget budget = budgetRepository.findById(1L).orElse(new Budget());
        budget.getIncomes().clear();
        budget.getExpenses().clear();
        budgetRepository.save(budget);
        return budgetMapper.toDto(budget);
    }


    public BudgetDTO createBudget(Budget budget) {
        Budget savedBudget = budgetRepository.save(budget);
        return budgetMapper.toDto(savedBudget);
    }
    public Double getTotalMoney() {
        Double totalIncome = incomeRepository.sumAllIncomes();
        Double totalExpense = expenseRepository.sumAllExpenses();
        if (totalIncome == null) {
            totalIncome = 0.0;
        }
        if (totalExpense == null) {
            totalExpense = 0.0;
        }
        return totalIncome - totalExpense;
}
}
