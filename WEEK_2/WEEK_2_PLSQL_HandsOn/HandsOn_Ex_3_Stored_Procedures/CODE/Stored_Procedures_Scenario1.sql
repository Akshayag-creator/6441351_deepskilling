SET SERVEROUTPUT ON;

CREATE TABLE Accounts (
    account_id INT PRIMARY KEY,
    account_type VARCHAR2(20),
    balance NUMBER(15, 2),
    customer_id INT
);

CREATE TABLE Employees (
    employee_id INT PRIMARY KEY,
    name VARCHAR2(100),
    department VARCHAR2(50),
    salary NUMBER(15, 2)
);

CREATE TABLE Transactions (
    transaction_id INT PRIMARY KEY,
    from_account INT,
    to_account INT,
    amount NUMBER(15, 2),
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE SEQUENCE transaction_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER trg_transaction_id
BEFORE INSERT ON Transactions
FOR EACH ROW
BEGIN
    :NEW.transaction_id := transaction_seq.NEXTVAL;
END;
/

INSERT INTO Accounts VALUES (101, 'Savings', 5000.00, 1);
INSERT INTO Accounts VALUES (102, 'Checking', 2500.00, 1);
INSERT INTO Accounts VALUES (103, 'Savings', 10000.00, 2);
INSERT INTO Accounts VALUES (104, 'Checking', 7500.00, 3);
INSERT INTO Accounts VALUES (105, 'Savings', 3000.00, 4);

INSERT INTO Employees VALUES (1, 'John Smith', 'Loans', 50000.00);
INSERT INTO Employees VALUES (2, 'Jane Doe', 'Customer Service', 45000.00);
INSERT INTO Employees VALUES (3, 'Mike Johnson', 'Investments', 60000.00);
INSERT INTO Employees VALUES (4, 'Sarah Williams', 'Customer Service', 48000.00);

CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest AS
BEGIN
    UPDATE Accounts 
    SET balance = balance * 1.01
    WHERE account_type = 'Savings';

    FOR rec IN (
        SELECT account_id, balance AS new_balance 
        FROM Accounts 
        WHERE account_type = 'Savings'
    ) LOOP
        DBMS_OUTPUT.PUT_LINE('Account ID: ' || rec.account_id || ' | New Balance: ' || rec.new_balance);
    END LOOP;
END;
/

BEGIN
    ProcessMonthlyInterest;
END;
/
