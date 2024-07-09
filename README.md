# BudgetKing

BudgetKing is a Spring Boot backend application for a budget tracker. This application provides various endpoints to manage incomes, expenses, budgets, and categories.

## Table of Contents
- [Technologies Used](#technologies-used)
- [Endpoints](#endpoints)
  - [Incomes](#incomes)
  - [Expenses](#expenses)
  - [Budgets](#budgets)
  - [Categories](#categories)
- [Getting Started](#getting-started)
- [Environment Setup](#environment-setup)
- [Running the Application](#running-the-application)
- [Postman Collection](#postman-collection)

## Technologies Used
- Spring Boot
  - Spring Boot Starter Data JPA
  - Spring Boot Starter Web
  - Spring Boot DevTools
  - Spring Boot Starter Test
- Lombok
- PostgreSQL
- JUnit
- MapStruct
- Java Dotenv

## Endpoints

### Incomes
- Create Income
- Update Income by ID
- Soft Delete Income
- Get All Incomes (Paginated)
- Get Incomes Between Date Start and End
- Get Total Income

### Expenses
- Create Expense
- Update Expense by ID
- Soft Delete Expense
- Get All Expenses (Paginated)
- Get Expenses Between Date Start and End
- Get Total Expense

### Budgets
- Get Budget (All Incomes and Expenses)
- Reset Budget
- Get Total Money
- Get Incomes Between Date Start and End

### Categories
- Get Categories

## Getting Started

To get started with BudgetKing, clone the repository and install the dependencies:

```bash
git clone https://github.com/daaanhe/budgetKing.git
cd budgetKing
```

## Environment Setup

Create a PostgreSQL database named `budgetKing` or any name you prefer. Set up an `.env` file in the root directory of the project with the following attributes:

```
APPLICATION_NAME=budgetKing
DATASOURCE_URL=jdbc:postgresql://<IP_ADDRESS>/budgetKing
DATASOURCE_USERNAME=postgres
DATASOURCE_PASSWORD=<YOUR_PASSWORD>
HIBERNATE_DDL_AUTO=create # Change to update after the first session
HIBERNATE_DIALECT=org.hibernate.dialect.PostgreSQLDialect
SHOW_SQL=true
SERVER_PORT=8080
```

Alternatively, you can set these properties in the `application.properties` file.

## Running the Application

You can run the application directly in IntelliJ IDEA by opening the project and running the main method of the `BudgetKingApplication` class. Or you can run the application following your own development environment.

## Postman Collection

A Postman collection with example requests for all endpoints is included in the application resources. You can import this collection into Postman to easily test the API endpoints.
