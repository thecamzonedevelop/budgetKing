package org.example.budgetking.Controller;

import lombok.RequiredArgsConstructor;
import org.example.budgetking.DTO.DeleteRequest;
import org.example.budgetking.DTO.IncomeDTO;
import org.example.budgetking.DTO.TotalIncomeDetails;
import org.example.budgetking.Repository.IncomeRepository;
import org.example.budgetking.Service.IncomeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.budgetking.DTO.CategoryTotal;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/incomes")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class IncomeController {
    private final IncomeService incomeService;
    private final IncomeRepository incomeRepository;

    @PostMapping
    public ResponseEntity<IncomeDTO> createIncome(@RequestBody IncomeDTO incomeDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(incomeService.createIncome(incomeDTO));
    }

    @PostMapping("/update")
    public ResponseEntity<IncomeDTO> updateIncome(@RequestBody IncomeDTO incomeDTO) {
        return ResponseEntity.ok(incomeService.updateIncome(incomeDTO));
    }

    @PostMapping("/delete")
    public ResponseEntity<Void> deleteIncome(@RequestBody DeleteRequest deleteRequest) {
        incomeService.deleteIncome(deleteRequest.getId());
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<IncomeDTO>> getAllIncomes(Pageable pageable) {
        Pageable sortedByAmountDesc = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("amount").descending());
        return ResponseEntity.ok(incomeService.getAllIncomes(sortedByAmountDesc));
    }

    @GetMapping("/report")
    public ResponseEntity<Page<IncomeDTO>> getIncomesBetweenDates(@RequestParam LocalDate start, @RequestParam LocalDate end, Pageable pageable) {
        Pageable sortedByAmountDesc = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("amount").descending());
        return ResponseEntity.ok(incomeService.getIncomesBetweenDates(start, end, sortedByAmountDesc));
    }

    @GetMapping("/total")
    public ResponseEntity<TotalIncomeDetails> getTotalIncomeDetails() {
        Double totalIncome = incomeRepository.sumAllIncomes();
        List<CategoryTotal> categoryTotals = incomeRepository.sumIncomesByCategory();
        categoryTotals.sort(Comparator.comparingDouble(CategoryTotal::getTotal).reversed());
        TotalIncomeDetails totalIncomeDetails = new TotalIncomeDetails(totalIncome, categoryTotals);
        return ResponseEntity.ok(totalIncomeDetails);
    }
}