
CREATE TABLE customers (
    customer_id NUMBER PRIMARY KEY,
    name VARCHAR2(100),
    email VARCHAR2(100),
    balance NUMBER
);

CREATE TABLE employees (
    emp_id NUMBER PRIMARY KEY,
    name VARCHAR2(100),
    department VARCHAR2(100),
    salary NUMBER
);

CREATE TABLE accounts (
    account_id NUMBER PRIMARY KEY,
    customer_id NUMBER,
    account_type VARCHAR2(50),
    balance NUMBER,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

-- Scenario 1: CustomerManagement

CREATE OR REPLACE PACKAGE CustomerManagement AS
    PROCEDURE add_customer(p_id NUMBER, p_name VARCHAR2, p_email VARCHAR2, p_balance NUMBER);
    PROCEDURE update_customer(p_id NUMBER, p_name VARCHAR2, p_email VARCHAR2);
    FUNCTION get_customer_balance(p_id NUMBER) RETURN NUMBER;
END CustomerManagement;
/

CREATE OR REPLACE PACKAGE BODY CustomerManagement AS
    PROCEDURE add_customer(p_id NUMBER, p_name VARCHAR2, p_email VARCHAR2, p_balance NUMBER) IS
    BEGIN
        INSERT INTO customers VALUES(p_id, p_name, p_email, p_balance);
    END;

    PROCEDURE update_customer(p_id NUMBER, p_name VARCHAR2, p_email VARCHAR2) IS
    BEGIN
        UPDATE customers SET name = p_name, email = p_email WHERE customer_id = p_id;
    END;

    FUNCTION get_customer_balance(p_id NUMBER) RETURN NUMBER IS
        v_balance NUMBER;
    BEGIN
        SELECT balance INTO v_balance FROM customers WHERE customer_id = p_id;
        RETURN v_balance;
    END;
END CustomerManagement;
/

-- Scenario 2: EmployeeManagement

CREATE OR REPLACE PACKAGE EmployeeManagement AS
    PROCEDURE hire_employee(p_id NUMBER, p_name VARCHAR2, p_dept VARCHAR2, p_salary NUMBER);
    PROCEDURE update_employee(p_id NUMBER, p_name VARCHAR2, p_dept VARCHAR2);
    FUNCTION calculate_annual_salary(p_id NUMBER) RETURN NUMBER;
END EmployeeManagement;
/

CREATE OR REPLACE PACKAGE BODY EmployeeManagement AS
    PROCEDURE hire_employee(p_id NUMBER, p_name VARCHAR2, p_dept VARCHAR2, p_salary NUMBER) IS
    BEGIN
        INSERT INTO employees VALUES(p_id, p_name, p_dept, p_salary);
    END;

    PROCEDURE update_employee(p_id NUMBER, p_name VARCHAR2, p_dept VARCHAR2) IS
    BEGIN
        UPDATE employees SET name = p_name, department = p_dept WHERE emp_id = p_id;
    END;

    FUNCTION calculate_annual_salary(p_id NUMBER) RETURN NUMBER IS
        v_salary NUMBER;
    BEGIN
        SELECT salary INTO v_salary FROM employees WHERE emp_id = p_id;
        RETURN v_salary * 12;
    END;
END EmployeeManagement;
/

-- Scenario 3: AccountOperations

CREATE OR REPLACE PACKAGE AccountOperations AS
    PROCEDURE open_account(p_id NUMBER, p_cust_id NUMBER, p_type VARCHAR2, p_balance NUMBER);
    PROCEDURE close_account(p_id NUMBER);
    FUNCTION get_total_balance(p_cust_id NUMBER) RETURN NUMBER;
END AccountOperations;
/

CREATE OR REPLACE PACKAGE BODY AccountOperations AS
    PROCEDURE open_account(p_id NUMBER, p_cust_id NUMBER, p_type VARCHAR2, p_balance NUMBER) IS
    BEGIN
        INSERT INTO accounts VALUES(p_id, p_cust_id, p_type, p_balance);
    END;

    PROCEDURE close_account(p_id NUMBER) IS
    BEGIN
        DELETE FROM accounts WHERE account_id = p_id;
    END;

    FUNCTION get_total_balance(p_cust_id NUMBER) RETURN NUMBER IS
        v_total NUMBER;
    BEGIN
        SELECT SUM(balance) INTO v_total FROM accounts WHERE customer_id = p_cust_id;
        RETURN NVL(v_total, 0);
    END;
END AccountOperations;
/


-- TESTING 

BEGIN
    -- Test Customer
    CustomerManagement.add_customer(1, 'Siri', 'siri@mail.com', 5000);
    DBMS_OUTPUT.PUT_LINE('Customer Balance: ' || CustomerManagement.get_customer_balance(1));
    CustomerManagement.update_customer(1, 'Siri K', 'siri.k@mail.com');

    -- Test Employee
    EmployeeManagement.hire_employee(101, 'Raj', 'IT', 40000);
    DBMS_OUTPUT.PUT_LINE('Annual Salary: ' || EmployeeManagement.calculate_annual_salary(101));
    EmployeeManagement.update_employee(101, 'Raj Kumar', 'Engineering');

    -- Test Account
    AccountOperations.open_account(201, 1, 'Savings', 3000);
    AccountOperations.open_account(202, 1, 'Current', 2000);
    DBMS_OUTPUT.PUT_LINE('Total Balance: ' || AccountOperations.get_total_balance(1));
    AccountOperations.close_account(202);
END;
/
