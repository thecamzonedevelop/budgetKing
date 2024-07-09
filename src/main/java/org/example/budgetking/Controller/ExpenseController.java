package org.example.budgetking.Controller;

import lombok.RequiredArgsConstructor;
import org.example.budgetking.DTO.CategoryTotal;
import org.example.budgetking.DTO.DeleteRequest;
import org.example.budgetking.DTO.ExpenseDTO;
import org.example.budgetking.DTO.TotalIncomeDetails;
import org.example.budgetking.Repository.ExpenseRepository;
import org.example.budgetking.Service.ExpenseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ExpenseController {
    private final ExpenseService expenseService;
    private final ExpenseRepository expenseRepository;

    @PostMapping
    public ResponseEntity<ExpenseDTO> createExpense(@RequestBody ExpenseDTO expenseDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(expenseService.createExpense(expenseDTO));
    }

    @PostMapping("/update")
    public ResponseEntity<ExpenseDTO> updateExpense(@RequestBody ExpenseDTO expenseDTO) {
        return ResponseEntity.ok(expenseService.updateExpense(expenseDTO));
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteExpense(@RequestBody DeleteRequest deleteRequest) {
        expenseService.deleteExpense(deleteRequest.getId());
        return ResponseEntity.ok("Delete Successful");
    }

    @GetMapping
    public ResponseEntity<Page<ExpenseDTO>> getAllExpenses(Pageable pageable) {
        Pageable sortedByAmountDesc = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("amount").descending());
        return ResponseEntity.ok(expenseService.getAllExpenses(sortedByAmountDesc));
    }

    @GetMapping("/report")
    public ResponseEntity<Page<ExpenseDTO>> getExpensesBetweenDates(@RequestParam LocalDate start, @RequestParam LocalDate end, Pageable pageable) {
        Pageable sortedByAmountDesc = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("amount").descending());
        return ResponseEntity.ok(expenseService.getExpensesBetweenDates(start, end, sortedByAmountDesc));
    }

    @GetMapping("/total")
    public ResponseEntity<TotalIncomeDetails> getTotalExpensesDetails() {
        Double totalExpenses = expenseRepository.sumAllExpenses();
        List<CategoryTotal> categoryTotals = expenseRepository.sumExpensesByCategory();
        // Sort categoryTotals by totalAmount in descending order
        categoryTotals.sort(Comparator.comparingDouble(CategoryTotal::getTotal).reversed());
        TotalIncomeDetails totalExpensesDetails = new TotalIncomeDetails(totalExpenses, categoryTotals);
        return ResponseEntity.ok(totalExpensesDetails);
    }
}