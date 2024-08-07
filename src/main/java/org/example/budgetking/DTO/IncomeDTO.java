package org.example.budgetking.DTO;

import lombok.Data;
import org.example.budgetking.Model.Income;

import java.time.LocalDate;

@Data
public class IncomeDTO {
    private Long id;
    private String source;
    private String remarks;
    private Double amount;
    private LocalDate date;
    private Long categoryID;
    private String categoryName;
    private String type; // This will hold either "Income" or "Expense"
    private Boolean enabled;
}

