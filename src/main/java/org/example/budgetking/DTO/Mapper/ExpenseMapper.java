package org.example.budgetking.DTO.Mapper;

import org.example.budgetking.DTO.ExpenseDTO;
import org.example.budgetking.Model.Expense;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {
    ExpenseDTO toDto(Expense expense);
    Expense toEntity(ExpenseDTO expenseDTO);
}
