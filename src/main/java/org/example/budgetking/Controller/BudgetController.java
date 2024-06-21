package org.example.budgetking.Controller;

import lombok.RequiredArgsConstructor;
import org.example.budgetking.DTO.BudgetDTO;
import org.example.budgetking.DTO.CombinedBudgetItemDTO;
import org.example.budgetking.DTO.TotalMoneyResponse;
import org.example.budgetking.Service.BudgetService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Page<CombinedBudgetItemDTO>> getCombinedBudgetItems(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size) {
        return ResponseEntity.ok(budgetService.getCombinedBudgetItems(PageRequest.of(page, size)));
    }
    @GetMapping("/total-money")
    public ResponseEntity<TotalMoneyResponse> getTotalMoney() {
        Double totalMoney = budgetService.getTotalMoney();
        TotalMoneyResponse response = new TotalMoneyResponse();
        response.setTotalMoney(totalMoney);
        response.setMessage("Total money calculated successfully");
        return ResponseEntity.ok(response);
    }
}
