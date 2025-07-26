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
    CURSOR ApplyAnnualFee IS
        SELECT account_id, balance FROM accounts;

    v_acc_id accounts.account_id%TYPE;
    v_balance accounts.balance%TYPE;
    annual_fee CONSTANT NUMBER := 500;
BEGIN
    DBMS_OUTPUT.PUT_LINE('Applying Annual Fee:');
    OPEN ApplyAnnualFee;
    LOOP
        FETCH ApplyAnnualFee INTO v_acc_id, v_balance;
        EXIT WHEN ApplyAnnualFee%NOTFOUND;


        UPDATE accounts SET balance = balance - annual_fee WHERE account_id = v_acc_id;
        DBMS_OUTPUT.PUT_LINE('Account ID: ' || v_acc_id || ' charged fee. New balance amount: ' || (v_balance - annual_fee));
    END LOOP;
    CLOSE ApplyAnnualFee;
END;
/
