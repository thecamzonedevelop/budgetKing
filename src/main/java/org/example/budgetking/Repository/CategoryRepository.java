package org.example.budgetking.Repository;

import org.example.budgetking.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByType(Category.Type type);

}
