package org.example.budgetking.DTO.Mapper;

import org.example.budgetking.DTO.IncomeDTO;
import org.example.budgetking.Model.Income;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IncomeMapper {
    IncomeDTO toDto(Income income);
    Income toEntity(IncomeDTO incomeDTO);
}
