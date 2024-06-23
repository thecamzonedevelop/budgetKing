package org.example.budgetking.DTO.Mapper;

import javax.annotation.processing.Generated;
import org.example.budgetking.DTO.IncomeDTO;
import org.example.budgetking.Model.Category;
import org.example.budgetking.Model.Income;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-23T21:05:19+0700",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 21.0.3 (Oracle Corporation)"
)
@Component
public class IncomeMapperImpl implements IncomeMapper {

    @Override
    public IncomeDTO toDto(Income income) {
        if ( income == null ) {
            return null;
        }

        IncomeDTO incomeDTO = new IncomeDTO();

        incomeDTO.setCategoryID( incomeCategoryId( income ) );
        incomeDTO.setCategoryName( incomeCategoryName( income ) );
        incomeDTO.setId( income.getId() );
        incomeDTO.setSource( income.getSource() );
        incomeDTO.setAmount( income.getAmount() );
        incomeDTO.setDate( income.getDate() );
        incomeDTO.setEnabled( income.getEnabled() );

        return incomeDTO;
    }

    @Override
    public Income toEntity(IncomeDTO incomeDTO) {
        if ( incomeDTO == null ) {
            return null;
        }

        Income income = new Income();

        income.setCategory( incomeDTOToCategory( incomeDTO ) );
        income.setId( incomeDTO.getId() );
        income.setSource( incomeDTO.getSource() );
        income.setAmount( incomeDTO.getAmount() );
        income.setDate( incomeDTO.getDate() );
        income.setEnabled( incomeDTO.getEnabled() );

        return income;
    }

    private Long incomeCategoryId(Income income) {
        if ( income == null ) {
            return null;
        }
        Category category = income.getCategory();
        if ( category == null ) {
            return null;
        }
        Long id = category.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String incomeCategoryName(Income income) {
        if ( income == null ) {
            return null;
        }
        Category category = income.getCategory();
        if ( category == null ) {
            return null;
        }
        String name = category.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    protected Category incomeDTOToCategory(IncomeDTO incomeDTO) {
        if ( incomeDTO == null ) {
            return null;
        }

        Category category = new Category();

        category.setId( incomeDTO.getCategoryID() );
        category.setName( incomeDTO.getCategoryName() );

        return category;
    }
}
