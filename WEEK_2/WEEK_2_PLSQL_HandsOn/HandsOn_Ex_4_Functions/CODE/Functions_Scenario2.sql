CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment(
  p_loan_amount NUMBER,
  p_annual_rate NUMBER,
  p_years NUMBER
)
RETURN NUMBER
IS
  v_monthly_rate NUMBER := p_annual_rate / 12 / 100;
  v_months NUMBER := p_years * 12;
  v_emi NUMBER;
BEGIN
  v_emi := (p_loan_amount * v_monthly_rate * POWER(1 + v_monthly_rate, v_months)) /
           (POWER(1 + v_monthly_rate, v_months) - 1);
  RETURN ROUND(v_emi, 2);
END;
/

BEGIN
  DBMS_OUTPUT.PUT_LINE('Monthly EMI: ' || CalculateMonthlyInstallment(100000, 10, 5));
END;
/
