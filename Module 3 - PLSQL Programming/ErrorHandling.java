public class ErrorHandling {
    private static final String ERROR_LOG_TABLE = """
            CREATE TABLE ErrorLog (
                LogID NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                ProcedureName VARCHAR2(100),
                ErrorMessage VARCHAR2(4000),
                LoggedAt DATE DEFAULT SYSDATE
            );
            /
            """;

    private static final String SAFE_TRANSFER_FUNDS = """
            CREATE OR REPLACE PROCEDURE SafeTransferFunds (
                p_source_account_id IN Accounts.AccountID%TYPE,
                p_target_account_id IN Accounts.AccountID%TYPE,
                p_amount IN NUMBER
            ) AS
                v_source_balance Accounts.Balance%TYPE;
                v_error_message VARCHAR2(4000);
            BEGIN
                SELECT Balance
                INTO v_source_balance
                FROM Accounts
                WHERE AccountID = p_source_account_id
                FOR UPDATE;

                IF v_source_balance < p_amount THEN
                    RAISE_APPLICATION_ERROR(-20001, 'Insufficient funds');
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
            EXCEPTION
                WHEN OTHERS THEN
                    v_error_message := SQLERRM;
                    ROLLBACK;
                    INSERT INTO ErrorLog (ProcedureName, ErrorMessage)
                    VALUES ('SafeTransferFunds', v_error_message);
                    COMMIT;
            END SafeTransferFunds;
            /
            """;

    private static final String UPDATE_SALARY = """
            CREATE OR REPLACE PROCEDURE UpdateSalary (
                p_employee_id IN Employees.EmployeeID%TYPE,
                p_percentage IN NUMBER
            ) AS
                v_count NUMBER;
                v_error_message VARCHAR2(4000);
            BEGIN
                UPDATE Employees
                SET Salary = Salary + (Salary * p_percentage / 100)
                WHERE EmployeeID = p_employee_id;

                v_count := SQL%ROWCOUNT;

                IF v_count = 0 THEN
                    RAISE_APPLICATION_ERROR(-20002, 'Employee ID does not exist');
                END IF;

                COMMIT;
            EXCEPTION
                WHEN OTHERS THEN
                    v_error_message := SQLERRM;
                    ROLLBACK;
                    INSERT INTO ErrorLog (ProcedureName, ErrorMessage)
                    VALUES ('UpdateSalary', v_error_message);
                    COMMIT;
            END UpdateSalary;
            /
            """;

    private static final String ADD_NEW_CUSTOMER = """
            CREATE OR REPLACE PROCEDURE AddNewCustomer (
                p_customer_id IN Customers.CustomerID%TYPE,
                p_name IN Customers.Name%TYPE,
                p_dob IN Customers.DOB%TYPE,
                p_balance IN Customers.Balance%TYPE
            ) AS
                v_error_message VARCHAR2(4000);
            BEGIN
                INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
                VALUES (p_customer_id, p_name, p_dob, p_balance, SYSDATE);

                COMMIT;
            EXCEPTION
                WHEN DUP_VAL_ON_INDEX THEN
                    ROLLBACK;
                    INSERT INTO ErrorLog (ProcedureName, ErrorMessage)
                    VALUES ('AddNewCustomer', 'Customer ID already exists: ' || p_customer_id);
                    COMMIT;
                WHEN OTHERS THEN
                    v_error_message := SQLERRM;
                    ROLLBACK;
                    INSERT INTO ErrorLog (ProcedureName, ErrorMessage)
                    VALUES ('AddNewCustomer', v_error_message);
                    COMMIT;
            END AddNewCustomer;
            /
            """;

    public static void main(String[] args) {
        System.out.println(ERROR_LOG_TABLE);
        System.out.println(SAFE_TRANSFER_FUNDS);
        System.out.println(UPDATE_SALARY);
        System.out.println(ADD_NEW_CUSTOMER);
    }
}
