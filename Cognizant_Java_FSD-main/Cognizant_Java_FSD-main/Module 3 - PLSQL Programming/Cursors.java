public class Cursors {
    private static final String GENERATE_MONTHLY_STATEMENTS = """
            DECLARE
                CURSOR GenerateMonthlyStatements IS
                    SELECT c.CustomerID,
                           c.Name,
                           t.TransactionID,
                           t.TransactionDate,
                           t.Amount,
                           t.TransactionType
                    FROM Customers c
                    JOIN Accounts a ON a.CustomerID = c.CustomerID
                    JOIN Transactions t ON t.AccountID = a.AccountID
                    WHERE TRUNC(t.TransactionDate, 'MM') = TRUNC(SYSDATE, 'MM')
                    ORDER BY c.CustomerID, t.TransactionDate;
            BEGIN
                FOR statement_record IN GenerateMonthlyStatements LOOP
                    DBMS_OUTPUT.PUT_LINE(
                        'Customer: ' || statement_record.Name ||
                        ', Transaction: ' || statement_record.TransactionID ||
                        ', Date: ' || TO_CHAR(statement_record.TransactionDate, 'YYYY-MM-DD') ||
                        ', Type: ' || statement_record.TransactionType ||
                        ', Amount: ' || statement_record.Amount
                    );
                END LOOP;
            END;
            /
            """;

    private static final String APPLY_ANNUAL_FEE = """
            DECLARE
                CURSOR ApplyAnnualFee IS
                    SELECT AccountID
                    FROM Accounts
                    FOR UPDATE;

                v_annual_fee CONSTANT NUMBER := 100;
            BEGIN
                FOR account_record IN ApplyAnnualFee LOOP
                    UPDATE Accounts
                    SET Balance = Balance - v_annual_fee,
                        LastModified = SYSDATE
                    WHERE CURRENT OF ApplyAnnualFee;
                END LOOP;

                COMMIT;
            END;
            /
            """;

    private static final String UPDATE_LOAN_INTEREST_RATES = """
            DECLARE
                CURSOR UpdateLoanInterestRates IS
                    SELECT LoanID, InterestRate
                    FROM Loans
                    FOR UPDATE;
            BEGIN
                FOR loan_record IN UpdateLoanInterestRates LOOP
                    UPDATE Loans
                    SET InterestRate =
                        CASE
                            WHEN loan_record.InterestRate < 6 THEN loan_record.InterestRate + 0.5
                            ELSE loan_record.InterestRate + 0.25
                        END
                    WHERE CURRENT OF UpdateLoanInterestRates;
                END LOOP;

                COMMIT;
            END;
            /
            """;

    public static void main(String[] args) {
        System.out.println(GENERATE_MONTHLY_STATEMENTS);
        System.out.println(APPLY_ANNUAL_FEE);
        System.out.println(UPDATE_LOAN_INTEREST_RATES);
    }
}
