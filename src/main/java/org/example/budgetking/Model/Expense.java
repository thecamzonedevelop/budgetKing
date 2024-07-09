package org.example.budgetking.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

// Represents an expense
@Entity
@Data
@Table(name = "expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private String remarks;

    private Double amount;

    private LocalDate date;

    private Boolean enabled = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "budget_id")
    private Budget budget;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    public boolean isEnabled() {
        return Boolean.TRUE.equals(this.enabled);
    }
}
