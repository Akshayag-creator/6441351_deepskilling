CREATE TABLE accounts (
    account_id NUMBER PRIMARY KEY,
    customer_id NUMBER,
    balance NUMBER
);

INSERT INTO accounts VALUES (1, 101, 5500);
INSERT INTO accounts VALUES (2, 102, 3750);

CREATE OR REPLACE FUNCTION HasSufficientBalance(p_account_id NUMBER, p_amount NUMBER)
RETURN BOOLEAN IS
    v_balance NUMBER;
BEGIN
    SELECT balance INTO v_balance FROM accounts WHERE account_id = p_account_id;
    RETURN v_balance >= p_amount;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN FALSE;
END;
/


DECLARE
    v_result BOOLEAN;
BEGIN
    v_result := HasSufficientBalance(1, 4000);
    IF v_result THEN
        DBMS_OUTPUT.PUT_LINE('Account 1 has sufficient balance.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Account 1 does NOT have sufficient balance.');
    END IF;

    v_result := HasSufficientBalance(2, 5000);
    IF v_result THEN
        DBMS_OUTPUT.PUT_LINE('Account 2 has sufficient balance.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Account 2 does NOT have sufficient balance.');
    END IF;
END;
/
