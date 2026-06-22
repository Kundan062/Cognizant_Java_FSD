public class Packages {
    private static final String CUSTOMER_MANAGEMENT = """
            CREATE OR REPLACE PACKAGE CustomerManagement AS
                PROCEDURE AddCustomer (
                    p_customer_id IN Customers.CustomerID%TYPE,
                    p_name IN Customers.Name%TYPE,
                    p_dob IN Customers.DOB%TYPE,
                    p_balance IN Customers.Balance%TYPE
                );

                PROCEDURE UpdateCustomer (
                    p_customer_id IN Customers.CustomerID%TYPE,
                    p_name IN Customers.Name%TYPE,
                    p_dob IN Customers.DOB%TYPE,
                    p_balance IN Customers.Balance%TYPE
                );

                FUNCTION GetCustomerBalance (
                    p_customer_id IN Customers.CustomerID%TYPE
                ) RETURN NUMBER;
            END CustomerManagement;
            /

            CREATE OR REPLACE PACKAGE BODY CustomerManagement AS
                PROCEDURE AddCustomer (
                    p_customer_id IN Customers.CustomerID%TYPE,
                    p_name IN Customers.Name%TYPE,
                    p_dob IN Customers.DOB%TYPE,
                    p_balance IN Customers.Balance%TYPE
                ) AS
                BEGIN
                    INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
                    VALUES (p_customer_id, p_name, p_dob, p_balance, SYSDATE);
                    COMMIT;
                END AddCustomer;

                PROCEDURE UpdateCustomer (
                    p_customer_id IN Customers.CustomerID%TYPE,
                    p_name IN Customers.Name%TYPE,
                    p_dob IN Customers.DOB%TYPE,
                    p_balance IN Customers.Balance%TYPE
                ) AS
                BEGIN
                    UPDATE Customers
                    SET Name = p_name,
                        DOB = p_dob,
                        Balance = p_balance,
                        LastModified = SYSDATE
                    WHERE CustomerID = p_customer_id;
                    COMMIT;
                END UpdateCustomer;

                FUNCTION GetCustomerBalance (
                    p_customer_id IN Customers.CustomerID%TYPE
                ) RETURN NUMBER AS
                    v_balance Customers.Balance%TYPE;
                BEGIN
                    SELECT Balance
                    INTO v_balance
                    FROM Customers
                    WHERE CustomerID = p_customer_id;

                    RETURN v_balance;
                END GetCustomerBalance;
            END CustomerManagement;
            /
            """;

    private static final String EMPLOYEE_MANAGEMENT = """
            CREATE OR REPLACE PACKAGE EmployeeManagement AS
                PROCEDURE HireEmployee (
                    p_employee_id IN Employees.EmployeeID%TYPE,
                    p_name IN Employees.Name%TYPE,
                    p_position IN Employees.Position%TYPE,
                    p_salary IN Employees.Salary%TYPE,
                    p_department IN Employees.Department%TYPE,
                    p_hire_date IN Employees.HireDate%TYPE
                );

                PROCEDURE UpdateEmployee (
                    p_employee_id IN Employees.EmployeeID%TYPE,
                    p_name IN Employees.Name%TYPE,
                    p_position IN Employees.Position%TYPE,
                    p_salary IN Employees.Salary%TYPE,
                    p_department IN Employees.Department%TYPE
                );

                FUNCTION CalculateAnnualSalary (
                    p_employee_id IN Employees.EmployeeID%TYPE
                ) RETURN NUMBER;
            END EmployeeManagement;
            /

            CREATE OR REPLACE PACKAGE BODY EmployeeManagement AS
                PROCEDURE HireEmployee (
                    p_employee_id IN Employees.EmployeeID%TYPE,
                    p_name IN Employees.Name%TYPE,
                    p_position IN Employees.Position%TYPE,
                    p_salary IN Employees.Salary%TYPE,
                    p_department IN Employees.Department%TYPE,
                    p_hire_date IN Employees.HireDate%TYPE
                ) AS
                BEGIN
                    INSERT INTO Employees (
                        EmployeeID,
                        Name,
                        Position,
                        Salary,
                        Department,
                        HireDate
                    ) VALUES (
                        p_employee_id,
                        p_name,
                        p_position,
                        p_salary,
                        p_department,
                        p_hire_date
                    );
                    COMMIT;
                END HireEmployee;

                PROCEDURE UpdateEmployee (
                    p_employee_id IN Employees.EmployeeID%TYPE,
                    p_name IN Employees.Name%TYPE,
                    p_position IN Employees.Position%TYPE,
                    p_salary IN Employees.Salary%TYPE,
                    p_department IN Employees.Department%TYPE
                ) AS
                BEGIN
                    UPDATE Employees
                    SET Name = p_name,
                        Position = p_position,
                        Salary = p_salary,
                        Department = p_department
                    WHERE EmployeeID = p_employee_id;
                    COMMIT;
                END UpdateEmployee;

                FUNCTION CalculateAnnualSalary (
                    p_employee_id IN Employees.EmployeeID%TYPE
                ) RETURN NUMBER AS
                    v_salary Employees.Salary%TYPE;
                BEGIN
                    SELECT Salary
                    INTO v_salary
                    FROM Employees
                    WHERE EmployeeID = p_employee_id;

                    RETURN v_salary * 12;
                END CalculateAnnualSalary;
            END EmployeeManagement;
            /
            """;

    private static final String ACCOUNT_OPERATIONS = """
            CREATE OR REPLACE PACKAGE AccountOperations AS
                PROCEDURE OpenAccount (
                    p_account_id IN Accounts.AccountID%TYPE,
                    p_customer_id IN Accounts.CustomerID%TYPE,
                    p_account_type IN Accounts.AccountType%TYPE,
                    p_balance IN Accounts.Balance%TYPE
                );

                PROCEDURE CloseAccount (
                    p_account_id IN Accounts.AccountID%TYPE
                );

                FUNCTION GetTotalBalance (
                    p_customer_id IN Customers.CustomerID%TYPE
                ) RETURN NUMBER;
            END AccountOperations;
            /

            CREATE OR REPLACE PACKAGE BODY AccountOperations AS
                PROCEDURE OpenAccount (
                    p_account_id IN Accounts.AccountID%TYPE,
                    p_customer_id IN Accounts.CustomerID%TYPE,
                    p_account_type IN Accounts.AccountType%TYPE,
                    p_balance IN Accounts.Balance%TYPE
                ) AS
                BEGIN
                    INSERT INTO Accounts (
                        AccountID,
                        CustomerID,
                        AccountType,
                        Balance,
                        LastModified
                    ) VALUES (
                        p_account_id,
                        p_customer_id,
                        p_account_type,
                        p_balance,
                        SYSDATE
                    );
                    COMMIT;
                END OpenAccount;

                PROCEDURE CloseAccount (
                    p_account_id IN Accounts.AccountID%TYPE
                ) AS
                BEGIN
                    DELETE FROM Accounts
                    WHERE AccountID = p_account_id;
                    COMMIT;
                END CloseAccount;

                FUNCTION GetTotalBalance (
                    p_customer_id IN Customers.CustomerID%TYPE
                ) RETURN NUMBER AS
                    v_total_balance NUMBER;
                BEGIN
                    SELECT NVL(SUM(Balance), 0)
                    INTO v_total_balance
                    FROM Accounts
                    WHERE CustomerID = p_customer_id;

                    RETURN v_total_balance;
                END GetTotalBalance;
            END AccountOperations;
            /
            """;

    public static void main(String[] args) {
        System.out.println(CUSTOMER_MANAGEMENT);
        System.out.println(EMPLOYEE_MANAGEMENT);
        System.out.println(ACCOUNT_OPERATIONS);
    }
}
