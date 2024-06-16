package org.example.budgetking.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class IncomeDTO {
    private Long id;
    private String source;
    private Double amount;
    private LocalDate date;
    private Boolean enabled;
}
