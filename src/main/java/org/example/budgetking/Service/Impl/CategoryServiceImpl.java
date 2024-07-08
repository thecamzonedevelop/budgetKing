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
        if(categoryRepository.count() == 0) {
            List<String> names = Arrays.asList("\uD83D\uDED2Groceries,", "\uD83C\uDFE0Rent", "\uD83D\uDCA1Utilities", "\uD83C\uDF89Entertainment", "\uD83D\uDE97Transportation", "🏩Healthcare", "\uD83D\uDC55Clothing", "\uD83C\uDF93Education", "\uD83C\uDF7D\uFE0FDining Out", "\uD83D\uDD2EMiscellaneous");
            int halfSize = names.size() / 2;
            for (int i = 0; i < names.size(); i++) {
                Category category = new Category();
                category.setName(names.get(i));
                if (i < halfSize) {
                    category.setType(Category.Type.INCOME);
                } else {
                    category.setType(Category.Type.EXPENSE);
                }
                categoryRepository.save(category);
            }
        }
    }
    @Override
    public List<Category> getCategoriesByType(Category.Type type) {
        return categoryRepository.findByType(type);
    }

}
