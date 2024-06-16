package org.example.budgetking.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

// Represents a budget
@Entity
@Data
@AllArgsConstructor
@Table(name = "budgets")
public class Budget {
    private static Budget instance;
    public Budget() {
    }

    public static Budget getInstance() {
        if (instance == null) {
            instance = new Budget();
        }
        return instance;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "budget", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Income> incomes = new ArrayList<>();

    @OneToMany(mappedBy = "budget", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Expense> expenses = new ArrayList<>();
}
