# Bank Management System - Architecture

## Overview
The Bank Management System is designed using core OOP concepts. The main components of the system are Accounts, Customers, and Transactions. Each component has specific responsibilities and interacts with others to provide the overall functionality of the system.

## Components
### Account
The `Account` class represents a bank account. It includes details such as account number, balance, and methods for deposit and withdrawal.

### Customer
The `Customer` class represents a bank customer. It includes details such as customer ID, name, and a list of accounts owned by the customer.

### Transaction
The `Transaction` class represents a bank transaction. It includes details such as transaction ID, amount, and type (deposit or withdrawal).

### Main
The `Main` class contains the entry point of the application. It provides a user interface for interacting with the system.

## Design Patterns
- **Singleton**: Ensures a single instance of the main banking service class.
- **Observer**: Used for transaction logging and notifications.

## Data Flow
1. User inputs are handled by the `Main` class.
2. The `Main` class interacts with `Customer`, `Account`, and `Transaction` classes to perform operations.
3. The system updates and stores data, ensuring consistency and integrity.

## Future Enhancements
- Implement a database for persistent storage.
- Add a graphical user interface.
- Enhance security features.
