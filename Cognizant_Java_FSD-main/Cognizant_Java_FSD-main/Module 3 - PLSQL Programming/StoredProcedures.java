public class StoredProcedures {
    private static final String PROCESS_MONTHLY_INTEREST = """
            CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest AS
            BEGIN
                UPDATE Accounts
                SET Balance = Balance + (Balance * 0.01),
                    LastModified = SYSDATE
                WHERE AccountType = 'Savings';

                COMMIT;
            END ProcessMonthlyInterest;
            /
            """;

    private static final String UPDATE_EMPLOYEE_BONUS = """
            CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus (
                p_department IN Employees.Department%TYPE,
                p_bonus_percentage IN NUMBER
            ) AS
            BEGIN
                UPDATE Employees
                SET Salary = Salary + (Salary * p_bonus_percentage / 100)
                WHERE Department = p_department;

                COMMIT;
            END UpdateEmployeeBonus;
            /
            """;

    private static final String TRANSFER_FUNDS = """
            CREATE OR REPLACE PROCEDURE TransferFunds (
                p_source_account_id IN Accounts.AccountID%TYPE,
                p_target_account_id IN Accounts.AccountID%TYPE,
                p_amount IN NUMBER
            ) AS
                v_source_balance Accounts.Balance%TYPE;
            BEGIN
                SELECT Balance
                INTO v_source_balance
                FROM Accounts
                WHERE AccountID = p_source_account_id
                FOR UPDATE;

                IF v_source_balance < p_amount THEN
                    RAISE_APPLICATION_ERROR(-20003, 'Insufficient balance');
                END IF;

                UPDATE Accounts
                SET Balance = Balance - p_amount,
                    LastModified = SYSDATE
                WHERE AccountID = p_source_account_id;

                UPDATE Accounts
                SET Balance = Balance + p_amount,
                    LastModified = SYSDATE
                WHERE AccountID = p_target_account_id;

                COMMIT;
            END TransferFunds;
            /
            """;

    public static void main(String[] args) {
        System.out.println(PROCESS_MONTHLY_INTEREST);
        System.out.println(UPDATE_EMPLOYEE_BONUS);
        System.out.println(TRANSFER_FUNDS);
    }
}
