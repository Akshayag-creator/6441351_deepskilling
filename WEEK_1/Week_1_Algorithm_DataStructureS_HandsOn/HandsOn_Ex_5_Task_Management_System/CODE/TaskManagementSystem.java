import java.util.Scanner;
public class TaskManagementSystem 
{
    static class Task 
    {
        int taskId;
        String taskName;
        String taskStatus;
        Task next;

        public Task(int taskId, String taskName, String taskStatus) 
        {
            this.taskId = taskId;
            this.taskName = taskName;
            this.taskStatus = taskStatus;
            this.next = null;
        }

        @Override
        public String toString() 
        {
            return "TaskID: " + taskId + ", Name: " + taskName + ", Status: " + taskStatus;
        }
    }

    static Task head = null; 

    public static void addTask(int id, String name, String taskStatus) 
    {
        Task newTask = new Task(id, name, taskStatus);
        if (head == null) 
        {
            head = newTask;
        } 
        else 
        {
            Task temp = head;
            while (temp.next != null) 
            {
                temp = temp.next;
            }
            temp.next = newTask;
        }
        System.out.println("Task added.");
    }

    public static void searchTask(int id) 
    {
        Task temp = head;
        while (temp != null) 
        {
            if (temp.taskId == id) 
            {
                System.out.println("Task Found: " + temp);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Task not found.");
    }

    public static void displayTasks() 
    {
        if (head == null) 
        {
            System.out.println("No tasks available.");
            return;
        }
        Task temp = head;
        System.out.println("Task List:");
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    public static void deleteTask(int id) 
    {
        if (head == null) 
        {
            System.out.println("Task list is empty.");
            return;
        }

        if (head.taskId == id) 
        {
            head = head.next;
            System.out.println("Task deleted.");
            return;
        }

        Task temp = head;
        while (temp.next != null && temp.next.taskId != id) 
        {
            temp = temp.next;
        }

        if (temp.next == null) 
        {
            System.out.println("Task not found.");
        } 
        else 
        {
            temp.next = temp.next.next;
            System.out.println("Task deleted.");
        }
    }

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== Task Management System ===");

        while (running) 
        {
            System.out.println("\nMenu:");
            System.out.println("1. Add Task");
            System.out.println("2. Search Task");
            System.out.println("3. Display Tasks");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter Task ID: ");
                    int id = sc.nextInt();
                    sc.nextLine(); 
                    System.out.print("Enter Task Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Status: ");
                    String taskStatus = sc.nextLine();
                    addTask(id, name, taskStatus);
                    break;

                case 2:
                    System.out.print("Enter Task ID to search: ");
                    int searchId = sc.nextInt();
                    searchTask(searchId);
                    break;

                case 3:
                    displayTasks();
                    break;

                case 4:
                    System.out.print("Enter Task ID to delete: ");
                    int deleteId = sc.nextInt();
                    deleteTask(deleteId);
                    break;

                case 5:
                    running = false;
                    System.out.println("Existing Task Manager...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }

        sc.close();
    }
}


