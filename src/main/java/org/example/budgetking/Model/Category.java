package org.example.budgetking.Model;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    public enum Type {
        INCOME,
        EXPENSE
    }
    private Type type;

}
