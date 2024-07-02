package org.example.budgetking.Controller;


import lombok.RequiredArgsConstructor;
import org.example.budgetking.Model.Category;
import org.example.budgetking.Service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "*")
public class CategoryController {
    private final CategoryService categoryService;


@GetMapping
public ResponseEntity<List<Category>> getAllCategories(@RequestParam(required = false, defaultValue = "") String type) {
    List<Category> categories;
    try {
        Category.Type categoryType = Category.Type.valueOf(type.toUpperCase());
        categories = categoryService.getCategoriesByType(categoryType);
    } catch (IllegalArgumentException e) {
        categories = categoryService.getAllCategories();
    }
    return ResponseEntity.ok(categories);
}
}
