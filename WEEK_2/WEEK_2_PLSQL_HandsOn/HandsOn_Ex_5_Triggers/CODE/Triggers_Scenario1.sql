CREATE TABLE customers (
    customer_id NUMBER PRIMARY KEY,
    name VARCHAR2(100),
    email VARCHAR2(100),
    balance NUMBER,
    LastModified DATE
);

CREATE TABLE transactions (
    txn_id NUMBER PRIMARY KEY,
    customer_id NUMBER,
    txn_date DATE,
    amount NUMBER,
    txn_type VARCHAR2(50)
);

CREATE TABLE auditlog (
    log_id NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY KEY,
    txn_id NUMBER,
    customer_id NUMBER,
    log_time DATE,
    action VARCHAR2(50)
);

INSERT INTO customers VALUES (101, 'Siri', 'siri56@mail.com', 5600, SYSDATE);
INSERT INTO customers VALUES (102, 'Raji', 'raji12@mail.com', 3500, SYSDATE);


CREATE OR REPLACE TRIGGER UpdateCustomerLastModified
BEFORE UPDATE ON customers
FOR EACH ROW
BEGIN
    :NEW.LastModified := SYSDATE;
END;
/


BEGIN
    UPDATE customers SET name = 'Siri Updated' WHERE customer_id = 101;
    DBMS_OUTPUT.PUT_LINE('LastModified (after update):');
    FOR rec IN (SELECT customer_id, LastModified FROM customers WHERE customer_id = 101) LOOP
        DBMS_OUTPUT.PUT_LINE('Customer ID: ' || rec.customer_id || ' | LastModified: ' || TO_CHAR(rec.LastModified, 'DD-MON-YYYY HH:MI:SS'));
    END LOOP;
END;
/
