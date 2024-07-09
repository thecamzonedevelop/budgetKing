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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.time.LocalDate;
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
        List<Income> incomes = incomeRepository.findAll().stream()
                .filter(Income::isEnabled) // Assuming isEnabled method exists
                .collect(Collectors.toList());
        List<Expense> expenses = expenseRepository.findAll().stream()
                .filter(Expense::isEnabled) // Assuming isEnabled method exists
                .collect(Collectors.toList());

        List<CombinedBudgetItemDTO> combinedItems = Stream.concat(
                incomes.stream().map(income -> {
                    CombinedBudgetItemDTO item = new CombinedBudgetItemDTO();
                    item.setId(income.getId());
                    item.setDescription(income.getSource());
                    item.setAmount(income.getAmount());
                    item.setDate(income.getDate());
                    item.setCategoryName(income.getCategory().getName());
                    item.setType("income");
                    item.setRemarks(income.getRemarks());
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
                    item.setRemarks(expense.getRemarks());
                    return item;
                })
        ).collect(Collectors.toList());
        List<CombinedBudgetItemDTO> combinedItemsSorted = combinedItems.stream()
                .sorted(Comparator.comparing(CombinedBudgetItemDTO::getAmount).reversed())
                .collect(Collectors.toList());
        int start = Math.min((int) pageable.getOffset(), combinedItems.size());
        int end = Math.min((start + pageable.getPageSize()), combinedItems.size());

        return new PageImpl<>(combinedItems.subList(start, end), pageable, combinedItemsSorted.size());
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


public Page<CombinedBudgetItemDTO> getBudgetItemsBetweenDates(LocalDate start, LocalDate end, String type, Pageable pageable) {
    List<CombinedBudgetItemDTO> items = new ArrayList<>();

    if (type == null || type.equalsIgnoreCase("income")) {
        items.addAll(incomeRepository.findAll().stream()
                .filter(Income::isEnabled)
            .filter(income -> !income.getDate().isBefore(start) && !income.getDate().isAfter(end))
            .map(income -> {
                CombinedBudgetItemDTO dto = new CombinedBudgetItemDTO();
                dto.setId(income.getId());
                dto.setType("income");
                dto.setAmount(income.getAmount());
                dto.setDate(income.getDate());
                dto.setDescription(income.getSource());
                dto.setCategoryName(income.getCategory().getName());
                dto.setRemarks(income.getRemarks());
                return dto;
            })
            .collect(Collectors.toList()));
    }

    if (type == null || type.equalsIgnoreCase("expense")) {
        items.addAll(expenseRepository.findAll().stream()
                .filter(Expense::isEnabled)
            .filter(expense -> !expense.getDate().isBefore(start) && !expense.getDate().isAfter(end))
            .map(expense -> {
                CombinedBudgetItemDTO dto = new CombinedBudgetItemDTO();
                dto.setId(expense.getId());
                dto.setType("expense");
                dto.setAmount(expense.getAmount());
                dto.setDate(expense.getDate());
                dto.setDescription(expense.getDescription());
                dto.setCategoryName(expense.getCategory().getName());
                dto.setRemarks(expense.getRemarks());
                return dto;
            })
            .collect(Collectors.toList()));
    }
    List<CombinedBudgetItemDTO> sortedItems = items.stream()
        .sorted(Comparator.comparing(CombinedBudgetItemDTO::getAmount).reversed())
        .collect(Collectors.toList());
    int startOfPage = (int) pageable.getOffset();
    int endOfPage = Math.min((startOfPage + pageable.getPageSize()), items.size());
    List<CombinedBudgetItemDTO> pageContent = items.subList(startOfPage, endOfPage);

    return new PageImpl<>(pageContent, pageable, sortedItems.size());
}
}
