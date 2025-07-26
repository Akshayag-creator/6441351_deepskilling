CREATE TABLE transactions (
    txn_id NUMBER PRIMARY KEY,
    customer_id NUMBER,
    txn_date DATE,
    amount NUMBER,
    txn_type VARCHAR2(50)
);

CREATE TABLE accounts (
    account_id NUMBER PRIMARY KEY,
    customer_id NUMBER,
    balance NUMBER
);

CREATE TABLE loans (
    loan_id NUMBER PRIMARY KEY,
    customer_id NUMBER,
    amount NUMBER,
    interest_rate NUMBER
);

INSERT INTO transactions VALUES (1, 1001, SYSDATE, 8000, 'Deposit');
INSERT INTO transactions VALUES (2, 1002, SYSDATE, -2000, 'Withdrawal');
INSERT INTO transactions VALUES (3, 1001, ADD_MONTHS(SYSDATE, -1), -500, 'Withdrawal');
INSERT INTO transactions VALUES (4, 1003, SYSDATE, 2000, 'Deposit');

INSERT INTO accounts VALUES (1, 1001, 100000);
INSERT INTO accounts VALUES (2, 1002, 8000);
INSERT INTO accounts VALUES (3, 1003, 6500);

INSERT INTO loans VALUES (1, 1001, 55000, 7.5);
INSERT INTO loans VALUES (2, 1002, 35000, 8.0);
INSERT INTO loans VALUES (3, 1003, 26000, 9.0);


DECLARE
    CURSOR GenerateMonthlyStatements IS
        SELECT customer_id, txn_date, amount, txn_type
        FROM transactions
        WHERE TO_CHAR(txn_date, 'MMYYYY') = TO_CHAR(SYSDATE, 'MMYYYY');

    v_cust_id transactions.customer_id%TYPE;
    v_date    transactions.txn_date%TYPE;
    v_amount  transactions.amount%TYPE;
    v_type    transactions.txn_type%TYPE;
BEGIN
    DBMS_OUTPUT.PUT_LINE('--- Monthly Statements ---');
    OPEN GenerateMonthlyStatements;
    LOOP
        FETCH GenerateMonthlyStatements INTO v_cust_id, v_date, v_amount, v_type;
        EXIT WHEN GenerateMonthlyStatements%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE('Customer ID: ' || v_cust_id ||
                             ', Date: ' || TO_CHAR(v_date, 'DD-MON-YYYY') ||
                             ', Type: ' || v_type ||
                             ', Amount: ' || v_amount);
    END LOOP;
    CLOSE GenerateMonthlyStatements;
END;
/
