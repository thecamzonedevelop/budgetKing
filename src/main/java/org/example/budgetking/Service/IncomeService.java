package org.example.budgetking.Service;

import org.example.budgetking.DTO.IncomeDTO;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import java.time.LocalDate;

public interface IncomeService {
    IncomeDTO createIncome(IncomeDTO incomeDTO);
    IncomeDTO updateIncome(Long id, IncomeDTO incomeDTO);
    void deleteIncome(Long id);
    Page<IncomeDTO> getAllIncomes(Pageable pageable);
    Page<IncomeDTO> getIncomesBetweenDates(LocalDate start, LocalDate end, Pageable pageable);
}
