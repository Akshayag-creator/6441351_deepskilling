import java.util.Scanner;

public class OrderSortingSystem 
{
    static class Order 
    {
        int orderId;
        String customerName;
        double totalPrice;

        public Order(int orderId, String customerName, double totalPrice) 
        {
            this.orderId = orderId;
            this.customerName = customerName;
            this.totalPrice = totalPrice;
        }

        @Override
        public String toString() 
        {
            return "OrderID: " + orderId + ", Customer: " + customerName + ", Total Price: ₹" + totalPrice;
        }
    }

    public static void bubbleSort(Order[] orders) 
    {
        int n = orders.length;
        for (int i = 0; i < n - 1; i++) 
        {
            for (int j = 0; j < n - 1 - i; j++) 
            {
                if (orders[j].totalPrice > orders[j + 1].totalPrice) 
                {
                    Order temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;
                }
            }
        }
    }

    public static void quickSort(Order[] orders, int low, int high) 
    {
        if (low < high) 
        {
            int pivotIndex = partition(orders, low, high);
            quickSort(orders, low, pivotIndex - 1);
            quickSort(orders, pivotIndex + 1, high);
        }
    }

    public static int partition(Order[] orders, int low, int high) 
    {
        double pivot = orders[high].totalPrice;
        int i = low - 1;
        for (int j = low; j < high; j++) 
        {
            if (orders[j].totalPrice < pivot) 
            {
                i++;
                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }
        Order temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;
        return i + 1;
    }

    public static void displayOrders(Order[] orders) 
    {
        for (Order o : orders) 
        {
            System.out.println(o);
        }
    }

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of orders: ");
        int n = sc.nextInt();
        sc.nextLine(); 

        Order[] orders = new Order[n];

        for (int i = 0; i < n; i++) 
        {
            System.out.println("\nEnter details for Order " + (i + 1));
            System.out.print("Order ID: ");
            int id = sc.nextInt();
            sc.nextLine(); 
            System.out.print("Customer Name: ");
            String name = sc.nextLine();
            System.out.print("Total Price: ");
            double price = sc.nextDouble();
            orders[i] = new Order(id, name, price);
        }

        System.out.println("\nChoose Sorting Algorithm:");
        System.out.println("1. Bubble Sort");
        System.out.println("2. Quick Sort");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();

        switch (choice) 
        {
            case 1:
                bubbleSort(orders);
                System.out.println("\nOrders sorted using Bubble Sort:");
                break;
            case 2:
                quickSort(orders, 0, n - 1);
                System.out.println("\nOrders sorted using Quick Sort:");
                break;
            default:
                System.out.println("Invalid choice. Showing unsorted list:");
        }

        displayOrders(orders);
        sc.close();
    }
}

