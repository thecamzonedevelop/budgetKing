package org.example.budgetking.Controller;

import lombok.RequiredArgsConstructor;
import org.example.budgetking.DTO.IncomeDTO;
import org.example.budgetking.Repository.IncomeRepository;
import org.example.budgetking.Service.IncomeService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import java.time.LocalDate;

/**
 * This class is a REST controller for managing incomes.
 * It provides endpoints for creating, updating, deleting, and retrieving incomes.
 */
@RestController
@RequestMapping("/api/incomes")
@RequiredArgsConstructor
public class IncomeController {
    private final IncomeService incomeService;
    private final IncomeRepository incomeRepository;

    /**
     * Creates a new income.
     * @param incomeDTO The income to create.
     * @return The created income.
     */
    @PostMapping
    public ResponseEntity<IncomeDTO> createIncome(@RequestBody IncomeDTO incomeDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(incomeService.createIncome(incomeDTO));
    }


    @PostMapping("/update")
    public ResponseEntity<IncomeDTO> updateIncome(@RequestBody IncomeDTO incomeDTO) {
        return ResponseEntity.ok(incomeService.updateIncome(incomeDTO));
    }

    /**
     * Deletes an income.
     * @param id The ID of the income to delete.
     * @return A ResponseEntity with no content.
     */
    @PostMapping("/delete")
    public ResponseEntity<Void> deleteIncome(@RequestParam Long id) {
        incomeService.deleteIncome(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Retrieves all incomes.
     * @param pageable The pagination information.
     * @return A page of incomes.
     */
    @GetMapping
    public ResponseEntity<Page<IncomeDTO>> getAllIncomes(Pageable pageable) {
        return ResponseEntity.ok(incomeService.getAllIncomes(pageable));
    }

    /**
     * Retrieves incomes between two dates.
     * @param start The start date.
     * @param end The end date.
     * @param pageable The pagination information.
     * @return A page of incomes between the two dates.
     */
    @GetMapping("/report")
    public ResponseEntity<Page<IncomeDTO>> getIncomesBetweenDates(@RequestParam LocalDate start, @RequestParam LocalDate end, Pageable pageable) {
        return ResponseEntity.ok(incomeService.getIncomesBetweenDates(start, end, pageable));
    }

    /**
     * Retrieves the total of all incomes.
     * @return The total of all incomes.
     */
    @GetMapping("/total")
    public ResponseEntity<Double> getTotalIncomes() {
        return ResponseEntity.ok(incomeRepository.sumAllIncomes());
    }
}