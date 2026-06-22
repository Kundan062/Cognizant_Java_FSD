class EmployeeRecord {
    int employeeId;
    String name;
    String position;
    double salary;

    EmployeeRecord(int employeeId, String name, String position, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }
}

public class EmployeeManagementSystem {

    EmployeeRecord[] employees = new EmployeeRecord[100];
    int count = 0;

    void add(EmployeeRecord employee) {
        employees[count++] = employee;
    }

    EmployeeRecord search(int id) {
        for (int i = 0; i < count; i++) {
            if (employees[i].employeeId == id)
                return employees[i];
        }
        return null;
    }

    void delete(int id) {
        for (int i = 0; i < count; i++) {
            if (employees[i].employeeId == id) {
                for (int j = i; j < count - 1; j++)
                    employees[j] = employees[j + 1];

                count--;
                break;
            }
        }
    }

    public static void main(String[] args) {
        EmployeeManagementSystem ems = new EmployeeManagementSystem();

        ems.add(new EmployeeRecord(1, "Vinayak", "Manager", 50000));

        System.out.println(ems.search(1).name);
    }
}