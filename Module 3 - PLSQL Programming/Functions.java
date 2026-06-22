public class Functions {
    private static final String CALCULATE_AGE = """
            CREATE OR REPLACE FUNCTION CalculateAge (
                p_dob IN DATE
            ) RETURN NUMBER AS
            BEGIN
                RETURN TRUNC(MONTHS_BETWEEN(SYSDATE, p_dob) / 12);
            END CalculateAge;
            /
            """;

    private static final String CALCULATE_MONTHLY_INSTALLMENT = """
            CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment (
                p_loan_amount IN NUMBER,
                p_interest_rate IN NUMBER,
                p_duration_years IN NUMBER
            ) RETURN NUMBER AS
                v_monthly_rate NUMBER;
                v_months NUMBER;
            BEGIN
                v_monthly_rate := p_interest_rate / 100 / 12;
                v_months := p_duration_years * 12;

                IF v_monthly_rate = 0 THEN
                    RETURN p_loan_amount / v_months;
                END IF;

                RETURN p_loan_amount * v_monthly_rate * POWER(1 + v_monthly_rate, v_months)
                    / (POWER(1 + v_monthly_rate, v_months) - 1);
            END CalculateMonthlyInstallment;
            /
            """;

    private static final String HAS_SUFFICIENT_BALANCE = """
            CREATE OR REPLACE FUNCTION HasSufficientBalance (
                p_account_id IN Accounts.AccountID%TYPE,
                p_amount IN NUMBER
            ) RETURN BOOLEAN AS
                v_balance Accounts.Balance%TYPE;
            BEGIN
                SELECT Balance
                INTO v_balance
                FROM Accounts
                WHERE AccountID = p_account_id;

                RETURN v_balance >= p_amount;
            EXCEPTION
                WHEN NO_DATA_FOUND THEN
                    RETURN FALSE;
            END HasSufficientBalance;
            /
            """;

    public static void main(String[] args) {
        System.out.println(CALCULATE_AGE);
        System.out.println(CALCULATE_MONTHLY_INSTALLMENT);
        System.out.println(HAS_SUFFICIENT_BALANCE);
    }
}
