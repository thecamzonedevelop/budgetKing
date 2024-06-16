package org.example.budgetking.Controller;

import lombok.RequiredArgsConstructor;
import org.example.budgetking.DTO.BudgetDTO;
import org.example.budgetking.Service.BudgetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/budget")
@RequiredArgsConstructor
public class BudgetController {
    private final BudgetService budgetService;

    @GetMapping
    public ResponseEntity<BudgetDTO> getBudget() {
        return ResponseEntity.ok(budgetService.getBudget());
    }

    @PostMapping("/reset")
    public ResponseEntity<BudgetDTO> resetBudget() {
        return ResponseEntity.ok(budgetService.resetBudget());
    }

    @GetMapping("/report")
    public ResponseEntity<BudgetDTO> getReport() {
        return ResponseEntity.ok(budgetService.getBudget());
    }
    @GetMapping("/total-money")
    public ResponseEntity<Double> getTotalMoney() {
        Double totalMoney = budgetService.getTotalMoney();
        return ResponseEntity.ok(totalMoney);
    }
}
