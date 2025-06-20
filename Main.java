import java.util.ArrayList;
import java.util.Scanner;

// Customer class to store individual customer details
class Customer {
    private int customerId;
    private String name;
    private int unitsConsumed;

    public Customer(int customerId, String name, int unitsConsumed) {
        this.customerId = customerId;
        this.name = name;
        this.unitsConsumed = unitsConsumed;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public int getUnitsConsumed() {
        return unitsConsumed;
    }

    public double calculateBill() {
        int units = unitsConsumed;
        double bill = 0;
        if (units <= 100) {
            bill = units * 1.5;
        } else if (units <= 300) {
            bill = 100 * 1.5 + (units - 100) * 2.5;
        } else {
            bill = 100 * 1.5 + 200 * 2.5 + (units - 300) * 4.0;
        }
        return bill;
    }

    public void displayDetails() {
        System.out.println("Customer ID: " + customerId);
        System.out.println("Name : " + name);
        System.out.println("Units Used : " + unitsConsumed);
        System.out.println("Bill Amount: ₹" + String.format("%.2f", calculateBill()));
        System.out.println("--------------------------------------");
    }
}

// Main class to handle logic
public class Main {
    private static final ArrayList<Customer> customers = new ArrayList<>();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n=== Electricity Bill Management System ===");
            System.out.println("1. Add Customer");
            System.out.println("2. View All Customers");
            System.out.println("3. Search Customer by ID");
            System.out.println("4. Generate Bill by ID");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            while (!sc.hasNextInt()) {
                System.out.print("Enter a valid number: ");
                sc.next();
            }
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    addCustomer();
                    break;
                case 2:
                    viewAllCustomers();
                    break;
                case 3:
                    searchCustomer();
                    break;
                case 4:
                    generateBill();
                    break;
                case 5:
                    System.out.println("Exiting... Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 5);
    }

    private static void addCustomer() {
        System.out.print("Enter Customer ID: ");
        int id = sc.nextInt();
        sc.nextLine(); // consume newline
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Units Consumed: ");
        int units = sc.nextInt();
        customers.add(new Customer(id, name, units));
        System.out.println("Customer added successfully!");
    }

    private static void viewAllCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customer records found.");
            return;
        }
        System.out.println("\nCustomer List:");
        for (Customer c : customers) {
            c.displayDetails();
        }
    }

    private static void searchCustomer() {
        System.out.print("Enter Customer ID to search: ");
        int id = sc.nextInt();
        boolean found = false;
        for (Customer c : customers) {
            if (c.getCustomerId() == id) {
                c.displayDetails();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Customer not found.");
        }
    }

    private static void generateBill() {
        System.out.print("Enter Customer ID for bill: ");
        int id = sc.nextInt();
        for (Customer c : customers) {
            if (c.getCustomerId() == id) {
                System.out.println("\n----- Electricity Bill -----");
                c.displayDetails();
                return;
            }
        }
        System.out.println("Customer not found.");
    }
}
