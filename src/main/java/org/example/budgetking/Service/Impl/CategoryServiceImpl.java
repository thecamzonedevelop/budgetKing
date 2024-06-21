package org.example.budgetking.Service.Impl;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.example.budgetking.Model.Category;
import org.example.budgetking.Repository.CategoryRepository;
import org.example.budgetking.Service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    @PostConstruct
    public void init() {
        List<String> names = Arrays.asList("Groceries", "Rent", "Utilities", "Entertainment", "Transportation", "Healthcare", "Clothing", "Education", "Dining Out", "Miscellaneous");
        for (String name : names) {
            Category category = new Category();
            category.setName(name);
            categoryRepository.save(category);
        }
    }
}
