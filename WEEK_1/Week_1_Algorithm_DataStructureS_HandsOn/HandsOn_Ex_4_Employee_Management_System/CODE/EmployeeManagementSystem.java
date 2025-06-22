import java.util.Scanner;
public class EmployeeManagementSystem 
{
    static class Employee 
    {
        int employeeId;
        String name;
        String position;
        double salary;

        public Employee(int employeeId, String name, String position, double salary) 
        {
            this.employeeId = employeeId;
            this.name = name;
            this.position = position;
            this.salary = salary;
        }

        @Override
        public String toString() 
        {
            return "EmployeeID: " + employeeId + ", Name: " + name +
                   ", Position: " + position + ", Salary: ₹" + salary;
        }
    }

    static final int MAX_EMPLOYEES = 100;
    static Employee[] employees = new Employee[MAX_EMPLOYEES];
    static int count = 0; 

    public static void addEmployee(Employee emp) 
    {
        if (count < MAX_EMPLOYEES) 
        {
            employees[count++] = emp;
            System.out.println("Employee added successfully.");
        } 
        else 
        {
            System.out.println("Employee array is full.");
        }
    }

    public static void searchEmployee(int id) 
    {
        for (int i = 0; i < count; i++) 
        {
            if (employees[i].employeeId == id) 
            {
                System.out.println("Employee Found: " + employees[i]);
                return;
            }
        }
        System.out.println("Employee not found.");
    }

    public static void displayAllEmployees() 
    {
        if (count == 0) 
        {
            System.out.println("No employee records.");
        } 
        else 
        {
            System.out.println("Employee List:");
            for (int i = 0; i < count; i++) 
            {
                System.out.println(employees[i]);
            }
        }
    }

    public static void deleteEmployee(int id) 
    {
        for (int i = 0; i < count; i++) 
        {
            if (employees[i].employeeId == id) 
            {
                for (int j = i; j < count - 1; j++) 
                {
                    employees[j] = employees[j + 1];
                }
                employees[--count] = null;
                System.out.println("Employee deleted.");
                return;
            }
        }
        System.out.println("Employee not found.");
    }

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== Employee Management System ===");

        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Employee");
            System.out.println("2. Search Employee");
            System.out.println("3. Display All Employees");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter Employee ID: ");
                    int id = sc.nextInt();
                    sc.nextLine(); 
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Position: ");
                    String position = sc.nextLine();
                    System.out.print("Enter Salary: ");
                    double salary = sc.nextDouble();
                    addEmployee(new Employee(id, name, position, salary));
                    break;

                case 2:
                    System.out.print("Enter Employee ID to search: ");
                    int searchId = sc.nextInt();
                    searchEmployee(searchId);
                    break;

                case 3:
                    displayAllEmployees();
                    break;

                case 4:
                    System.out.print("Enter Employee ID to delete: ");
                    int deleteId = sc.nextInt();
                    deleteEmployee(deleteId);
                    break;

                case 5:
                    running = false;
                    System.out.println("Existing system...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }

        sc.close();
    }
}

