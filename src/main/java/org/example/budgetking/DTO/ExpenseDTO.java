package org.example.budgetking.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ExpenseDTO {
    private Long id;
    private String description;
    private Double amount;
    private LocalDate date;
    private Boolean enabled;
}
