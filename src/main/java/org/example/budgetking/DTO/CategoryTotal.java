package org.example.budgetking.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.budgetking.Model.Category;

@Data
@AllArgsConstructor
public class CategoryTotal {
    private Category category;
    private Double total;
}
