package org.example.budgetking.DTO.Mapper;

import org.example.budgetking.DTO.ExpenseDTO;
import org.example.budgetking.Model.Expense;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {

    @Mapping(target = "categoryID" , source = "category.id")
    @Mapping(target = "categoryName", source = "category.name")
    ExpenseDTO toDto(Expense expense);

    @Mapping(target = "category.id", source = "categoryID")
    @Mapping(target = "category.name", source = "categoryName")
    Expense toEntity(ExpenseDTO expenseDTO);
}
