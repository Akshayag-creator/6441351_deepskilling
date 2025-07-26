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
    CURSOR UpdateLoanInterestRates IS
        SELECT loan_id, interest_rate FROM loans;

    v_loan_id loans.loan_id%TYPE;
    v_rate    loans.interest_rate%TYPE;
BEGIN
    DBMS_OUTPUT.PUT_LINE('Updating Loan Interest Rates:');
    OPEN UpdateLoanInterestRates;
    LOOP
        FETCH UpdateLoanInterestRates INTO v_loan_id, v_rate;
        EXIT WHEN UpdateLoanInterestRates%NOTFOUND;

        IF v_rate < 8 THEN
            UPDATE loans SET interest_rate = v_rate + 1 WHERE loan_id = v_loan_id;
            DBMS_OUTPUT.PUT_LINE('Loan ID: ' || v_loan_id || ' New Interest Rate: ' || (v_rate + 1));
        ELSE
            UPDATE loans SET interest_rate = v_rate - 0.5 WHERE loan_id = v_loan_id;
            DBMS_OUTPUT.PUT_LINE('Loan ID: ' || v_loan_id || ' New Interest Rate: ' || (v_rate - 0.5));
        END IF;
    END LOOP;
    CLOSE UpdateLoanInterestRates;
END;
/
