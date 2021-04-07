# CPSC 210 Term Project

## Expense Tracker


This application will help people **keep track of their expenses**. A user will be able to *record their transactions*
and *categorize* them. They will also be able to view transactions by their category and view their account balance. 
This will let them have a visual representation of how they spend their money.
This expense tracker can be used by anyone who wants to keep track of their finances and will help them see how their money is being spent.
This particular project was of interest to me because of its practicality and usefulness and is something I see myself using as well.



## User Stories

- As a user, I want to be able to add a transaction to my list of transactions
- As a user, I want to be able to remove a transaction from my list of transactions
- As a user, I want to be able to view my transaction history, or just incomes or expenses
- As a user, I want to be able to select a category and view all transactions of that category
- As a user, I want to be able to view the total balance in my account
- As a user, I want to be able to save transactions to file
- As a user, I want to be able to load my transaction list from file

## Phase 4: Task 2
The code has a type hierarchy - where an abstract class Tab has 5 subclasses that extend it - AddTab, HomeTab, SaveTab, 
ViewBalanceTab and ViewTransactionsTab. These subclasses all represent a different kind of tab in the GUI and store
information related to that particular tab. They all implement their own version of Tab's abstract setup() method,
the functionality of which is to set the tab up by displaying the required text and buttons.
