package org.example.budgetking.Service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.budgetking.DTO.ExpenseDTO;
import org.example.budgetking.DTO.Mapper.ExpenseMapper;
import org.example.budgetking.Model.Budget;
import org.example.budgetking.Model.Category;
import org.example.budgetking.Model.Expense;
import org.example.budgetking.Repository.BudgetRepository;
import org.example.budgetking.Repository.CategoryRepository;
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
    private final CategoryRepository categoryRepository;


    public ExpenseDTO createExpense(ExpenseDTO expenseDTO) {
        Expense expense = expenseMapper.toEntity(expenseDTO);
        Category category = categoryRepository.findById(expenseDTO.getCategoryID()).orElseThrow(() -> new RuntimeException("Category not found"));
        Budget budget = budgetRepository.findById(1L).orElseThrow(() -> new RuntimeException("Budget not found"));
        expense.setCategory(category);
        expense.setBudget(budget);
        expense.setEnabled(true);
        return expenseMapper.toDto(expenseRepository.save(expense));
    }


    public ExpenseDTO updateExpense(ExpenseDTO expenseDTO) {
        Expense expense = expenseRepository.findById(expenseDTO.getId()).orElseThrow(() -> new RuntimeException("Expense not found"));
        Category category = categoryRepository.findById(expenseDTO.getCategoryID()).orElseThrow(() -> new RuntimeException("Category not found"));
        expense.setDescription(expenseDTO.getDescription());
        expense.setAmount(expenseDTO.getAmount());
        expense.setDate(expenseDTO.getDate());
        expense.setCategory(category);
        expense.setRemarks(expenseDTO.getRemarks());
        return expenseMapper.toDto(expenseRepository.save(expense));
    }


    public void deleteExpense(Long id){
        Expense expense = expenseRepository.findById(id).orElseThrow(() -> new RuntimeException("Expense not found"));
        expense.setEnabled(false);
        expenseRepository.save(expense);
    }

    @Override
    public Page<ExpenseDTO> getAllExpenses(Pageable pageable) {
        return expenseRepository.findAllByEnabledTrue(pageable).map(this::ExpenseConvertToDto);
    }
    private ExpenseDTO ExpenseConvertToDto(Expense expense) {
        ExpenseDTO dto = new ExpenseDTO();
        dto.setId(expense.getId());
        dto.setAmount(expense.getAmount());
        dto.setDescription(expense.getDescription());
        dto.setRemarks(expense.getRemarks());
        dto.setDate(expense.getDate());
        dto.setCategoryID(expense.getCategory() != null ? expense.getCategory().getId() : null);
        dto.setCategoryName(expense.getCategory() != null ? expense.getCategory().getName() : null);
        dto.setType("expense");
        dto.setEnabled(expense.getEnabled());
        return dto;
    }

    public Page<ExpenseDTO> getExpensesBetweenDates(LocalDate start, LocalDate end, Pageable pageable) {
        return expenseRepository.findAllByDateBetweenAndEnabledTrue(start, end, pageable).map(expenseMapper::toDto);
    }
}
