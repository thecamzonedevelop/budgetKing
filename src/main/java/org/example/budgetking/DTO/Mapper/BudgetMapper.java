package org.example.budgetking.DTO.Mapper;

import org.example.budgetking.DTO.BudgetDTO;
import org.example.budgetking.Model.Budget;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BudgetMapper {
    BudgetDTO toDto(Budget budget);
    Budget toEntity(BudgetDTO budgetDTO);
}
