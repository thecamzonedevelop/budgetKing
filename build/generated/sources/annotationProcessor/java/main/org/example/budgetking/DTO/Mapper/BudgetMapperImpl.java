package org.example.budgetking.DTO.Mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.example.budgetking.DTO.BudgetDTO;
import org.example.budgetking.DTO.ExpenseDTO;
import org.example.budgetking.DTO.IncomeDTO;
import org.example.budgetking.Model.Budget;
import org.example.budgetking.Model.Expense;
import org.example.budgetking.Model.Income;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-26T15:21:55+0700",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 21.0.3 (Oracle Corporation)"
)
@Component
public class BudgetMapperImpl implements BudgetMapper {

    @Autowired
    private IncomeMapper incomeMapper;
    @Autowired
    private ExpenseMapper expenseMapper;

    @Override
    public BudgetDTO toDto(Budget budget) {
        if ( budget == null ) {
            return null;
        }

        BudgetDTO budgetDTO = new BudgetDTO();

        budgetDTO.setIncomes( incomeListToIncomeDTOList( budget.getIncomes() ) );
        budgetDTO.setExpenses( expenseListToExpenseDTOList( budget.getExpenses() ) );
        budgetDTO.setId( budget.getId() );

        return budgetDTO;
    }

    @Override
    public Budget toEntity(BudgetDTO budgetDTO) {
        if ( budgetDTO == null ) {
            return null;
        }

        Budget budget = new Budget();

        budget.setIncomes( incomeDTOListToIncomeList( budgetDTO.getIncomes() ) );
        budget.setExpenses( expenseDTOListToExpenseList( budgetDTO.getExpenses() ) );
        budget.setId( budgetDTO.getId() );

        return budget;
    }

    protected List<IncomeDTO> incomeListToIncomeDTOList(List<Income> list) {
        if ( list == null ) {
            return null;
        }

        List<IncomeDTO> list1 = new ArrayList<IncomeDTO>( list.size() );
        for ( Income income : list ) {
            list1.add( incomeMapper.toDto( income ) );
        }

        return list1;
    }

    protected List<ExpenseDTO> expenseListToExpenseDTOList(List<Expense> list) {
        if ( list == null ) {
            return null;
        }

        List<ExpenseDTO> list1 = new ArrayList<ExpenseDTO>( list.size() );
        for ( Expense expense : list ) {
            list1.add( expenseMapper.toDto( expense ) );
        }

        return list1;
    }

    protected List<Income> incomeDTOListToIncomeList(List<IncomeDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Income> list1 = new ArrayList<Income>( list.size() );
        for ( IncomeDTO incomeDTO : list ) {
            list1.add( incomeMapper.toEntity( incomeDTO ) );
        }

        return list1;
    }

    protected List<Expense> expenseDTOListToExpenseList(List<ExpenseDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Expense> list1 = new ArrayList<Expense>( list.size() );
        for ( ExpenseDTO expenseDTO : list ) {
            list1.add( expenseMapper.toEntity( expenseDTO ) );
        }

        return list1;
    }
}
