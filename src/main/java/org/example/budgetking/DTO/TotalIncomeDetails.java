package org.example.budgetking.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor

public class TotalIncomeDetails {
    private Double total;
    private List<CategoryTotal> categoryTotals;
}
