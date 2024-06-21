package org.example.budgetking.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TransactionDTO {
    private Long id;
    private String description;
    private Double amount;
    private LocalDate date;
    private Long categoryID;
    private String categoryName;
    private Boolean enabled;
    private String type; // This will hold either "Income" or "Expense"
}
