package org.example.budgetking.DTO.Mapper;

import org.example.budgetking.DTO.BudgetDTO;
import org.example.budgetking.Model.Budget;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {IncomeMapper.class, ExpenseMapper.class})
public interface BudgetMapper {
    @Mapping(target = "incomes", source = "incomes")
    @Mapping(target = "expenses", source = "expenses")
    BudgetDTO toDto(Budget budget);
    @Mapping(target = "incomes", source = "incomes")
    @Mapping(target = "expenses", source = "expenses")
    Budget toEntity(BudgetDTO budgetDTO);
}
