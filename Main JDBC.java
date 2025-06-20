import java.sql.*;
import java.util.Scanner;
public class ElectricityBillManagement {
 private static final String JDBC_URL = "jdbc:mysql://localhost:3306/electricity_db";
 private static final String DB_USER = "root"; // Change as per your MySQL
 private static final String DB_PASS = "sivasks@1804"; // Change as per your MySQL
 public static void main(String[] args) {
 try (
 Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER,
DB_PASS);
 Scanner scanner = new Scanner(System.in)
 ) {
 while (true) {
 System.out.println("\n--- Electricity Bill Management ---");
 System.out.println("1. Add New Bill");
 System.out.println("2. View All Bills");
 System.out.println("3. Exit");
 System.out.print("Choose option: ");
 int choice = scanner.nextInt();
 scanner.nextLine(); // consume newline
 switch (choice) {
 case 1:
 addBill(conn, scanner);
 break;
 case 2:
 viewBills(conn);
 break;
 case 3:
 System.out.println("Exiting...");
 return;
 default:
 System.out.println("Invalid choice!");
 }
 }
 } catch (SQLException e) {
 e.printStackTrace();
 }
 }
 private static void addBill(Connection conn, Scanner scanner) throws SQLException {
 System.out.print("Enter customer name: ");
 String name = scanner.nextLine();
 System.out.print("Enter units consumed: ");
 int units = scanner.nextInt();
 double billAmount = calculateBill(units);
 String sql = "INSERT INTO bills (customer_name, units_consumed, bill_amount)
VALUES (?, ?, ?)";
 try (PreparedStatement stmt = conn.prepareStatement(sql)) {
 stmt.setString(1, name);
 stmt.setInt(2, units);
 stmt.setDouble(3, billAmount);
 stmt.executeUpdate();
 System.out.println("Bill added successfully. Amount: ₹" + billAmount);
 }
 }
 private static void viewBills(Connection conn) throws SQLException {
 String sql = "SELECT * FROM bills";
 try (Statement stmt = conn.createStatement();
 ResultSet rs = stmt.executeQuery(sql)) {
 System.out.println("\n--- All Bills ---");
 while (rs.next()) {
 System.out.printf("ID: %d | Name: %s | Units: %d | Bill: ₹%.2f\n",
 rs.getInt("id"),
 rs.getString("customer_name"),
 rs.getInt("units_consumed"),
 rs.getDouble("bill_amount"));
 }
 }
 }
 private static double calculateBill(int units) {
 double rate;
 if (units <= 100) rate = 3.0;
 else if (units <= 300) rate = 4.5;
 else rate = 6.0;
 return units * rate;
 }
}
