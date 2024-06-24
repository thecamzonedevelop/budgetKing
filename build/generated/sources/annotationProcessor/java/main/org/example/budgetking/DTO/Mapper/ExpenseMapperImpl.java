package org.example.budgetking.DTO.Mapper;

import javax.annotation.processing.Generated;
import org.example.budgetking.DTO.ExpenseDTO;
import org.example.budgetking.Model.Category;
import org.example.budgetking.Model.Expense;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-24T19:54:16+0700",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 21.0.3 (Oracle Corporation)"
)
@Component
public class ExpenseMapperImpl implements ExpenseMapper {

    @Override
    public ExpenseDTO toDto(Expense expense) {
        if ( expense == null ) {
            return null;
        }

        ExpenseDTO expenseDTO = new ExpenseDTO();

        expenseDTO.setCategoryID( expenseCategoryId( expense ) );
        expenseDTO.setCategoryName( expenseCategoryName( expense ) );
        expenseDTO.setId( expense.getId() );
        expenseDTO.setDescription( expense.getDescription() );
        expenseDTO.setAmount( expense.getAmount() );
        expenseDTO.setDate( expense.getDate() );
        expenseDTO.setEnabled( expense.getEnabled() );

        expenseDTO.setType( "Expense" );

        return expenseDTO;
    }

    @Override
    public Expense toEntity(ExpenseDTO expenseDTO) {
        if ( expenseDTO == null ) {
            return null;
        }

        Expense expense = new Expense();

        expense.setCategory( expenseDTOToCategory( expenseDTO ) );
        expense.setId( expenseDTO.getId() );
        expense.setDescription( expenseDTO.getDescription() );
        expense.setAmount( expenseDTO.getAmount() );
        expense.setDate( expenseDTO.getDate() );
        expense.setEnabled( expenseDTO.getEnabled() );

        return expense;
    }

    private Long expenseCategoryId(Expense expense) {
        if ( expense == null ) {
            return null;
        }
        Category category = expense.getCategory();
        if ( category == null ) {
            return null;
        }
        Long id = category.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String expenseCategoryName(Expense expense) {
        if ( expense == null ) {
            return null;
        }
        Category category = expense.getCategory();
        if ( category == null ) {
            return null;
        }
        String name = category.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    protected Category expenseDTOToCategory(ExpenseDTO expenseDTO) {
        if ( expenseDTO == null ) {
            return null;
        }

        Category category = new Category();

        category.setId( expenseDTO.getCategoryID() );
        category.setName( expenseDTO.getCategoryName() );
        if ( expenseDTO.getType() != null ) {
            category.setType( Enum.valueOf( Category.Type.class, expenseDTO.getType() ) );
        }

        return category;
    }
}
