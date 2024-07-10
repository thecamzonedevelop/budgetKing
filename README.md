# BudgetKing

BudgetKing is a Spring Boot backend application for a budget tracker. This application provides various endpoints to manage incomes, expenses, budgets

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
- [Frontend](#frontend)

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
git clone https://github.com/thecamzonedevelop/budgetKing.git
cd budgetKing
```

## Environment Setup

Create a PostgreSQL database named `budgetKing` or any name you prefer. Ensure you have the PostgreSQL driver installed to initialize the connection to the database. Set up an `.env` file in the root directory of the project with the following attributes:

```
APPLICATION_NAME=budgetKing
DATASOURCE_URL=jdbc:postgresql://<IP_ADDRESS>/budgetKing
DATASOURCE_USERNAME=postgres
DATASOURCE_PASSWORD=<YOUR_PASSWORD>
HIBERNATE_DDL_AUTO=create-drop # Change to "update" after the first session to not drop the table after the application restarts.
HIBERNATE_DIALECT=org.hibernate.dialect.PostgreSQLDialect
SHOW_SQL=true
SERVER_PORT=8080
```

Alternatively, you can set these properties in the `application.properties` file.

## Running the Application

You can run the application directly in IntelliJ IDEA by opening the project and running the `main` method of the `BudgetKingApplication` class. Or you can run the application following your own development environment.

## Postman Collection

A Postman collection with example requests for all endpoints is included in the application resources. You can import this collection into Postman to easily test the API endpoints.

## Frontend

If you need a frontend for this application, visit the repository [here](https://github.com/thecamzonedevelop/budget-tracker-project-client.git), or you can build your own frontend by utilizing the available endpoints.
