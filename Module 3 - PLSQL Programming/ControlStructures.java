public class ControlStructures {
    private static final String SENIOR_LOAN_DISCOUNT = """
            BEGIN
                FOR customer_record IN (
                    SELECT c.CustomerID
                    FROM Customers c
                    WHERE TRUNC(MONTHS_BETWEEN(SYSDATE, c.DOB) / 12) > 60
                ) LOOP
                    UPDATE Loans
                    SET InterestRate = InterestRate - 1
                    WHERE CustomerID = customer_record.CustomerID;
                END LOOP;

                COMMIT;
            END;
            /
            """;

    private static final String PROMOTE_VIP_CUSTOMERS = """
            BEGIN
                EXECUTE IMMEDIATE 'ALTER TABLE Customers ADD IsVIP CHAR(1) DEFAULT ''N''';
            EXCEPTION
                WHEN OTHERS THEN
                    IF SQLCODE != -1430 THEN
                        RAISE;
                    END IF;
            END;
            /

            BEGIN
                FOR customer_record IN (
                    SELECT CustomerID
                    FROM Customers
                    WHERE Balance > 10000
                ) LOOP
                    UPDATE Customers
                    SET IsVIP = 'Y'
                    WHERE CustomerID = customer_record.CustomerID;
                END LOOP;

                COMMIT;
            END;
            /
            """;

    private static final String LOAN_DUE_REMINDERS = """
            BEGIN
                FOR loan_record IN (
                    SELECT c.Name, l.LoanID, l.EndDate
                    FROM Loans l
                    JOIN Customers c ON c.CustomerID = l.CustomerID
                    WHERE l.EndDate BETWEEN SYSDATE AND SYSDATE + 30
                    ORDER BY l.EndDate
                ) LOOP
                    DBMS_OUTPUT.PUT_LINE(
                        'Reminder: Loan ' || loan_record.LoanID ||
                        ' for ' || loan_record.Name ||
                        ' is due on ' || TO_CHAR(loan_record.EndDate, 'YYYY-MM-DD')
                    );
                END LOOP;
            END;
            /
            """;

    public static void main(String[] args) {
        System.out.println(SENIOR_LOAN_DISCOUNT);
        System.out.println(PROMOTE_VIP_CUSTOMERS);
        System.out.println(LOAN_DUE_REMINDERS);
    }
}
