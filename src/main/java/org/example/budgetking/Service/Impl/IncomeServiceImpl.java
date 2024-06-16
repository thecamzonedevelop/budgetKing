package org.example.budgetking.Service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.budgetking.DTO.IncomeDTO;
import org.example.budgetking.DTO.Mapper.IncomeMapper;
import org.example.budgetking.Model.Budget;
import org.example.budgetking.Model.Income;
import org.example.budgetking.Repository.BudgetRepository;
import org.example.budgetking.Repository.IncomeRepository;
import org.example.budgetking.Service.IncomeService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class IncomeServiceImpl implements IncomeService {
    private final IncomeRepository incomeRepository;
    private final BudgetRepository budgetRepository;
    private final IncomeMapper incomeMapper;


    public IncomeDTO createIncome(IncomeDTO incomeDTO) {
        Income income = incomeMapper.toEntity(incomeDTO);
        Budget budget = budgetRepository.findById(1L).orElseThrow(() -> new RuntimeException("Budget not found"));
        income.setBudget(budget);
        return incomeMapper.toDto(incomeRepository.save(income));
    }

    public IncomeDTO updateIncome(Long id, IncomeDTO incomeDTO) {
        Income income = incomeRepository.findById(id).orElseThrow(() -> new RuntimeException("Income not found"));
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
    return incomeRepository.findAllByEnabledTrue(pageable).map(incomeMapper::toDto);
}

public Page<IncomeDTO> getIncomesBetweenDates(LocalDate start, LocalDate end, Pageable pageable) {
    return incomeRepository.findAllByDateBetweenAndEnabledTrue(start, end, pageable).map(incomeMapper::toDto);
}
}
