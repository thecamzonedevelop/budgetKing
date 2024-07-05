package org.example.budgetking.DTO.Mapper;

import org.example.budgetking.DTO.ExpenseDTO;
import org.example.budgetking.Model.Expense;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {

    @Mapping(target = "categoryID" , source = "category.id")
    @Mapping(target = "categoryName", source = "category.name")
    @Mapping(target = "type", constant = "Expense")
    @Mapping(target = "description", source = "expense.description")
    ExpenseDTO toDto(Expense expense);

    @Mapping(target = "category.id", source = "categoryID")
    @Mapping(target = "category.name", source = "categoryName")
    @Mapping(target = "category.type", source = "type")
    @Mapping(target = "description", source = "description")
    Expense toEntity(ExpenseDTO expenseDTO);
}
