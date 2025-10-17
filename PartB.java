import java.sql.*;
import java.util.Scanner;

public class PartB {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/storedb";
        String user = "root";
        String password = "yourpassword";

        Scanner sc = new Scanner(System.in);

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con.setAutoCommit(false);

            while (true) {
                System.out.println("\n1. Insert\n2. View\n3. Update\n4. Delete\n5. Exit");
                System.out.print("Enter choice: ");
                int ch = sc.nextInt();

                switch (ch) {
                    case 1:
                        System.out.print("Enter ProductID, Name, Price, Quantity: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        String name = sc.nextLine();
                        double price = sc.nextDouble();
                        int qty = sc.nextInt();

                        PreparedStatement ps1 = con.prepareStatement(
                            "INSERT INTO Product VALUES (?, ?, ?, ?)");
                        ps1.setInt(1, id);
                        ps1.setString(2, name);
                        ps1.setDouble(3, price);
                        ps1.setInt(4, qty);
                        ps1.executeUpdate();
                        con.commit();
                        System.out.println("Product added successfully!");
                        break;

                    case 2:
                        Statement st = con.createStatement();
                        ResultSet rs = st.executeQuery("SELECT * FROM Product");
                        System.out.println("ID\tName\tPrice\tQty");
                        while (rs.next()) {
                            System.out.println(rs.getInt(1) + "\t" + rs.getString(2)
                                    + "\t" + rs.getDouble(3) + "\t" + rs.getInt(4));
                        }
                        break;

                    case 3:
                        System.out.print("Enter ProductID to update price: ");
                        int pid = sc.nextInt();
                        System.out.print("Enter new price: ");
                        double newPrice = sc.nextDouble();

                        PreparedStatement ps2 = con.prepareStatement(
                            "UPDATE Product SET Price=? WHERE ProductID=?");
                        ps2.setDouble(1, newPrice);
                        ps2.setInt(2, pid);
                        ps2.executeUpdate();
                        con.commit();
                        System.out.println("Price updated successfully!");
                        break;

                    case 4:
                        System.out.print("Enter ProductID to delete: ");
                        int delId = sc.nextInt();
                        PreparedStatement ps3 = con.prepareStatement(
                            "DELETE FROM Product WHERE ProductID=?");
                        ps3.setInt(1, delId);
                        ps3.executeUpdate();
                        con.commit();
                        System.out.println("Product deleted successfully!");
                        break;

                    case 5:
                        System.out.println("Exiting...");
                        con.close();
                        sc.close();
                        System.exit(0);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
