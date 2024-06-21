package org.example.budgetking.Controller;

import lombok.RequiredArgsConstructor;
import org.example.budgetking.DTO.ExpenseDTO;
import org.example.budgetking.Repository.ExpenseRepository;
import org.example.budgetking.Service.ExpenseService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {
    private final ExpenseService expenseService;
    private final ExpenseRepository ExpenseRepository;

    @PostMapping
    public ResponseEntity<ExpenseDTO> createExpense(@RequestBody ExpenseDTO expenseDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(expenseService.createExpense(expenseDTO));
    }

    @PostMapping("/update")
    public ResponseEntity<ExpenseDTO> updateExpense(@RequestBody ExpenseDTO expenseDTO) {
        return ResponseEntity.ok(expenseService.updateExpense(expenseDTO));
    }

    @PostMapping("/delete")
    public ResponseEntity<Void> deleteExpense(@RequestParam Long id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<ExpenseDTO>> getAllExpenses(Pageable pageable) {
        return ResponseEntity.ok(expenseService.getAllExpenses(pageable));
    }

    @GetMapping("/report")
    public ResponseEntity<Page<ExpenseDTO>> getExpensesBetweenDates(@RequestParam LocalDate start, @RequestParam LocalDate end, Pageable pageable) {
        return ResponseEntity.ok(expenseService.getExpensesBetweenDates(start, end, pageable));
    }

    @GetMapping("/total")
    public ResponseEntity<Double> getTotalExpenses() {
        return ResponseEntity.ok(ExpenseRepository.sumAllExpenses());
    }
}
