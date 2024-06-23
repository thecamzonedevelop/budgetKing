package org.example.budgetking.DTO.Mapper;

import org.example.budgetking.DTO.IncomeDTO;
import org.example.budgetking.Model.Income;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IncomeMapper {
    @Mapping(target = "categoryID" , source = "category.id")
    @Mapping(target = "categoryName", source = "category.name")
    IncomeDTO toDto(Income income);
    @Mapping(target = "category.id", source = "categoryID")
    @Mapping(target = "category.name", source = "categoryName")
    Income toEntity(IncomeDTO incomeDTO);
}
