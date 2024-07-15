package org.example.budgetking.Service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.budgetking.DTO.IncomeDTO;
import org.example.budgetking.DTO.Mapper.IncomeMapper;
import org.example.budgetking.Model.Budget;
import org.example.budgetking.Model.Category;
import org.example.budgetking.Model.Income;
import org.example.budgetking.Repository.BudgetRepository;
import org.example.budgetking.Repository.CategoryRepository;
import org.example.budgetking.Repository.IncomeRepository;
import org.example.budgetking.Service.IncomeService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.example.budgetking.DTO.IncomeDTO;

import org.springframework.data.domain.Pageable;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class IncomeServiceImpl implements IncomeService {
    private final IncomeRepository incomeRepository;
    private final BudgetRepository budgetRepository;
    private final IncomeMapper incomeMapper;
    private final CategoryRepository categoryRepository;


    public IncomeDTO createIncome(IncomeDTO incomeDTO) {
        Income income = incomeMapper.toEntity(incomeDTO);
        Category category = categoryRepository.findById(incomeDTO.getCategoryID()).orElseThrow(() -> new RuntimeException("Category not found"));
        Budget budget = budgetRepository.findById(1L).orElseThrow(() -> new RuntimeException("Budget not found"));
        income.setBudget(budget);
        income.setCategory(category);
        income.setEnabled(true);
        return incomeMapper.toDto(incomeRepository.save(income));
    }

    public IncomeDTO updateIncome(IncomeDTO incomeDTO) {
        Income income = incomeRepository.findById(incomeDTO.getId()).orElseThrow(() -> new RuntimeException("Income not found"));
        Category category = categoryRepository.findById(incomeDTO.getCategoryID()).orElseThrow(() -> new RuntimeException("Category not found"));
        income.setCategory(category);
        income.setSource(incomeDTO.getSource());
        income.setAmount(incomeDTO.getAmount());
        income.setDate(incomeDTO.getDate());
        return incomeMapper.toDto(incomeRepository.save(income));
    }


    public void deleteIncome(Long id) {
        Income income = incomeRepository.findById(id).orElseThrow(() -> new RuntimeException("Income not found"));
        income.setEnabled(false);
        incomeRepository.save(income);
    }


    public Page<IncomeDTO> getAllIncomes(Pageable pageable) {
        return incomeRepository.findAllByEnabledTrue(pageable).map(this::IncomeConvertToDto);
    }
    private IncomeDTO IncomeConvertToDto(Income income) {
        IncomeDTO dto = new IncomeDTO();
        dto.setId(income.getId());
        dto.setAmount(income.getAmount());
        dto.setDate(income.getDate());
        dto.setSource(income.getSource());
        dto.setCategoryID(income.getCategory() != null ? income.getCategory().getId() : null);
        dto.setCategoryName(income.getCategory() != null ? income.getCategory().getName() : null);
        dto.setType("Income");
        dto.setRemarks(income.getRemarks());
        dto.setEnabled(income.isEnabled());
        return dto;
    }

public Page<IncomeDTO> getIncomesBetweenDates(LocalDate start, LocalDate end, Pageable pageable) {
    return incomeRepository.findAllByDateBetweenAndEnabledTrue(start, end, pageable).map(incomeMapper::toDto);
}
}
