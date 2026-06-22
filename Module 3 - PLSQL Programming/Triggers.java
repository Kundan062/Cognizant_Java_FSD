public class Triggers {
    private static final String UPDATE_CUSTOMER_LAST_MODIFIED = """
            CREATE OR REPLACE TRIGGER UpdateCustomerLastModified
            BEFORE UPDATE ON Customers
            FOR EACH ROW
            BEGIN
                :NEW.LastModified := SYSDATE;
            END;
            /
            """;

    private static final String AUDIT_LOG_TABLE = """
            CREATE TABLE AuditLog (
                AuditID NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                TransactionID NUMBER,
                AccountID NUMBER,
                TransactionDate DATE,
                Amount NUMBER,
                TransactionType VARCHAR2(10),
                LoggedAt DATE DEFAULT SYSDATE
            );
            /
            """;

    private static final String LOG_TRANSACTION = """
            CREATE OR REPLACE TRIGGER LogTransaction
            AFTER INSERT ON Transactions
            FOR EACH ROW
            BEGIN
                INSERT INTO AuditLog (
                    TransactionID,
                    AccountID,
                    TransactionDate,
                    Amount,
                    TransactionType,
                    LoggedAt
                ) VALUES (
                    :NEW.TransactionID,
                    :NEW.AccountID,
                    :NEW.TransactionDate,
                    :NEW.Amount,
                    :NEW.TransactionType,
                    SYSDATE
                );
            END;
            /
            """;

    private static final String CHECK_TRANSACTION_RULES = """
            CREATE OR REPLACE TRIGGER CheckTransactionRules
            BEFORE INSERT ON Transactions
            FOR EACH ROW
            DECLARE
                v_balance Accounts.Balance%TYPE;
            BEGIN
                SELECT Balance
                INTO v_balance
                FROM Accounts
                WHERE AccountID = :NEW.AccountID;

                IF :NEW.TransactionType = 'Withdrawal' AND :NEW.Amount > v_balance THEN
                    RAISE_APPLICATION_ERROR(-20004, 'Withdrawal exceeds account balance');
                END IF;

                IF :NEW.TransactionType = 'Deposit' AND :NEW.Amount <= 0 THEN
                    RAISE_APPLICATION_ERROR(-20005, 'Deposit amount must be positive');
                END IF;
            END;
            /
            """;

    public static void main(String[] args) {
        System.out.println(UPDATE_CUSTOMER_LAST_MODIFIED);
        System.out.println(AUDIT_LOG_TABLE);
        System.out.println(LOG_TRANSACTION);
        System.out.println(CHECK_TRANSACTION_RULES);
    }
}
