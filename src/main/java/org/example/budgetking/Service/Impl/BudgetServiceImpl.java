package org.example.budgetking.Service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.budgetking.DTO.BudgetDTO;
import org.example.budgetking.DTO.CombinedBudgetItemDTO;
import org.example.budgetking.DTO.Mapper.BudgetMapper;
import org.example.budgetking.Model.Budget;
import org.example.budgetking.Model.Expense;
import org.example.budgetking.Model.Income;
import org.example.budgetking.Repository.BudgetRepository;
import org.example.budgetking.Repository.ExpenseRepository;
import org.example.budgetking.Repository.IncomeRepository;
import org.example.budgetking.Service.BudgetService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class BudgetServiceImpl implements BudgetService {
    private final BudgetRepository budgetRepository;
    private final IncomeRepository incomeRepository;
    private final ExpenseRepository expenseRepository;
    private final BudgetMapper budgetMapper;

    public Page<CombinedBudgetItemDTO> getCombinedBudgetItems(Pageable pageable) {
        List<Income> incomes = incomeRepository.findAll();
        List<Expense> expenses = expenseRepository.findAll();

        List<CombinedBudgetItemDTO> combinedItems = Stream.concat(
                incomes.stream().map(income -> {
                    CombinedBudgetItemDTO item = new CombinedBudgetItemDTO();
                    item.setId(income.getId());
                    item.setDescription(income.getSource());
                    item.setAmount(income.getAmount());
                    item.setDate(income.getDate());
                    item.setCategoryName(income.getCategory().getName());
                    item.setType("income");
                    return item;
                }),
                expenses.stream().map(expense -> {
                    CombinedBudgetItemDTO item = new CombinedBudgetItemDTO();
                    item.setId(expense.getId());
                    item.setDescription(expense.getDescription());
                    item.setAmount(expense.getAmount());
                    item.setDate(expense.getDate());
                    item.setCategoryName(expense.getCategory().getName());
                    item.setType("expense");
                    return item;
                })
        ).collect(Collectors.toList());

        int start = Math.min((int) pageable.getOffset(), combinedItems.size());
        int end = Math.min((start + pageable.getPageSize()), combinedItems.size());

        return new PageImpl<>(combinedItems.subList(start, end), pageable, combinedItems.size());
    }

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
