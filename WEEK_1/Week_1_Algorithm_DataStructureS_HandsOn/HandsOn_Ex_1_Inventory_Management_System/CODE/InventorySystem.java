import java.util.HashMap;
import java.util.Scanner;

public class InventorySystem 
{
    static class Product 
    {
        int productId;
        String productName;
        int quantity;
        double price;

        public Product(int productId, String productName, int quantity, double price) 
        {
            this.productId = productId;
            this.productName = productName;
            this.quantity = quantity;
            this.price = price;
        }

        @Override
        public String toString() 
        {
            return "ProductID: " + productId +
                   ", Name: " + productName +
                   ", Quantity: " + quantity +
                   ", Price: ₹" + price;
        }
    }

    private static HashMap<Integer, Product> inventory = new HashMap<>();

    public static void addProduct(Product product) 
    {
        inventory.put(product.productId, product);
        System.out.println("Product added successfully.");
    }

    public static void updateProduct(int productId, int quantity, double price) 
    {
        Product product = inventory.get(productId);
        if (product != null) 
        {
            product.quantity = quantity;
            product.price = price;
            System.out.println("Product updated successfully.");
        } 
        else 
        {
            System.out.println("Product not found.");
        }
    }

    public static void deleteProduct(int productId) 
    {
        if (inventory.remove(productId) != null) 
        {
            System.out.println("Product deleted successfully.");
        } 
        else 
        {
            System.out.println("Product not found.");
        }
    }

    public static void displayInventory() 
    {
        if (inventory.isEmpty()) 
        {
            System.out.println("Inventory is empty.");
        } 
        else 
        {
            System.out.println("Current Inventory:");
            for (Product p : inventory.values()) 
            {
                System.out.println(p);
            }
        }
    }

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== Inventory Management System ===");

        while (running) 
        {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add Product");
            System.out.println("2. Update Product");
            System.out.println("3. Delete Product");
            System.out.println("4. Display Inventory");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            switch (choice) 
            {
                case 1:
                    System.out.print("Enter Product ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();  // consume newline
                    System.out.print("Enter Product Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Quantity: ");
                    int qty = sc.nextInt();
                    System.out.print("Enter Price: ");
                    double price = sc.nextDouble();
                    addProduct(new Product(id, name, qty, price));
                    break;

                case 2:
                    System.out.print("Enter Product ID to update: ");
                    int upId = sc.nextInt();
                    System.out.print("Enter New Quantity: ");
                    int newQty = sc.nextInt();
                    System.out.print("Enter New Price: ");
                    double newPrice = sc.nextDouble();
                    updateProduct(upId, newQty, newPrice);
                    break;

                case 3:
                    System.out.print("Enter Product ID to delete: ");
                    int delId = sc.nextInt();
                    deleteProduct(delId);
                    break;

                case 4:
                    displayInventory();
                    break;

                case 5:
                    running = false;
                    System.out.println("Exiting... Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }

        sc.close();
    }
}