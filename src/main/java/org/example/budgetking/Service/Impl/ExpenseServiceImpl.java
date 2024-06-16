package org.example.budgetking.Service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.budgetking.DTO.ExpenseDTO;
import org.example.budgetking.DTO.Mapper.ExpenseMapper;
import org.example.budgetking.Model.Budget;
import org.example.budgetking.Model.Expense;
import org.example.budgetking.Repository.BudgetRepository;
import org.example.budgetking.Repository.ExpenseRepository;
import org.example.budgetking.Service.ExpenseService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final BudgetRepository budgetRepository;
    private final ExpenseMapper expenseMapper;


    public ExpenseDTO createExpense(ExpenseDTO expenseDTO) {
        Expense expense = expenseMapper.toEntity(expenseDTO);
        Budget budget = budgetRepository.findById(1L).orElseThrow(() -> new RuntimeException("Budget not found"));
        expense.setBudget(budget);
        return expenseMapper.toDto(expenseRepository.save(expense));
    }


    public ExpenseDTO updateExpense(Long id, ExpenseDTO expenseDTO) {
        Expense expense = expenseRepository.findById(id).orElseThrow(() -> new RuntimeException("Expense not found"));
        expense.setDescription(expenseDTO.getDescription());
        expense.setAmount(expenseDTO.getAmount());
        expense.setDate(expenseDTO.getDate());
        return expenseMapper.toDto(expenseRepository.save(expense));
    }


    public void deleteExpense(Long id) {
        Expense expense = expenseRepository.findById(id).orElseThrow(() -> new RuntimeException("Expense not found"));
        expense.setEnabled(false);
        expenseRepository.save(expense);
    }

    @Override
    public Page<ExpenseDTO> getAllExpenses(Pageable pageable) {
        return expenseRepository.findAllByEnabledTrue(pageable).map(expenseMapper::toDto);
    }


    public Page<ExpenseDTO> getExpensesBetweenDates(LocalDate start, LocalDate end, Pageable pageable) {
        return expenseRepository.findAllByDateBetweenAndEnabledTrue(start, end, pageable).map(expenseMapper::toDto);
    }
}
