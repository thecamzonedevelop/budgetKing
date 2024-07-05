package org.example.budgetking.DTO;

import lombok.Data;
import java.time.LocalDate;

@Data
public class CombinedBudgetItemDTO {
    private Long id;
    private String description;
    private String remarks;
    private Double amount;
    private LocalDate date;
    private String categoryName;
    private String type; // "income" or "expense"
}
