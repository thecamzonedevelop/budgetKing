package org.example.budgetking.Service;

import org.example.budgetking.DTO.ExpenseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.time.LocalDate;

public interface ExpenseService {
    ExpenseDTO createExpense(ExpenseDTO expenseDTO);
    ExpenseDTO updateExpense(Long id, ExpenseDTO expenseDTO);
    void deleteExpense(Long id);
    Page<ExpenseDTO> getAllExpenses(Pageable pageable);
    Page<ExpenseDTO> getExpensesBetweenDates(LocalDate start, LocalDate end, Pageable pageable);


}
