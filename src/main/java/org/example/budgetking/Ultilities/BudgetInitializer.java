package org.example.budgetking.Ultilities;

import org.example.budgetking.Model.Budget;
import org.example.budgetking.Repository.BudgetRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class BudgetInitializer implements CommandLineRunner {

    private final BudgetRepository budgetRepository;

    public BudgetInitializer(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Check if a budget already exists
        if (budgetRepository.count() == 0) {
            Budget initialBudget = new Budget();
            // Set initial values as needed
            initialBudget.setIncomes(new ArrayList<>()); // Initialize with empty list or add initial incomes
            initialBudget.setExpenses(new ArrayList<>()); // Initialize with empty list or add initial expenses

            // Save initial budget to database
            budgetRepository.save(initialBudget);

            System.out.println("Initial budget created successfully.");
        }
    }
}
