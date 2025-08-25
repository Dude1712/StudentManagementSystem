import java.sql.*;
import java.util.Scanner;

public class StudentApp {
    static final String URL = "jdbc:mysql://localhost:3306/studentdb";
    static final String USER = "root";
    static final String PASS = "Root123";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            while (true) {
                System.out.println("\n===== Student Management System =====");
                System.out.println("1. Add Student");
                System.out.println("2. View Students");
                System.out.println("3. Update Student");
                System.out.println("4. Delete Student");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");
                int ch = Integer.parseInt(sc.nextLine());

                switch (ch) {
                    case 1 -> addStudent(con, sc);
                    case 2 -> viewStudents(con);
                    case 3 -> updateStudent(con, sc);
                    case 4 -> deleteStudent(con, sc);
                    case 5 -> {
                        System.out.println("Exiting...");
                        return;
                    }
                    default -> System.out.println("Invalid choice!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void addStudent(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter course: ");
        String course = sc.nextLine();

        System.out.print("Enter fee: ");
        double fee = Double.parseDouble(sc.nextLine());

        System.out.print("Enter email: ");
        String email = sc.nextLine();

        System.out.print("Enter phone: ");
        String phone = sc.nextLine();

        String sql = "INSERT INTO student (name, course, fee, email, phone) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, course);
        ps.setDouble(3, fee);
        ps.setString(4, email);
        ps.setString(5, phone);
        ps.executeUpdate();

        System.out.println("Student added successfully!");
    }

    static void viewStudents(Connection con) throws SQLException {
        String sql = "SELECT * FROM student";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        System.out.println("\nID | Name | Course | Fee | Email | Phone");
        System.out.println("--------------------------------------------");
        while (rs.next()) {
            System.out.println(rs.getInt("id") + " | " +
                    rs.getString("name") + " | " +
                    rs.getString("course") + " | " +
                    rs.getDouble("fee") + " | " +
                    rs.getString("email") + " | " +
                    rs.getString("phone"));
        }
    }

    static void updateStudent(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter student ID to update: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("Enter new name: ");
        String name = sc.nextLine();

        System.out.print("Enter new course: ");
        String course = sc.nextLine();

        System.out.print("Enter new fee: ");
        double fee = Double.parseDouble(sc.nextLine());

        System.out.print("Enter new email: ");
        String email = sc.nextLine();

        System.out.print("Enter new phone: ");
        String phone = sc.nextLine();

        String sql = "UPDATE student SET name=?, course=?, fee=?, email=?, phone=? WHERE id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, course);
        ps.setDouble(3, fee);
        ps.setString(4, email);
        ps.setString(5, phone);
        ps.setInt(6, id);

        int rows = ps.executeUpdate();
        if (rows > 0)
            System.out.println("Student updated successfully!");
        else
            System.out.println("Student not found!");
    }

    static void deleteStudent(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter student ID to delete: ");
        int id = Integer.parseInt(sc.nextLine());

        String sql = "DELETE FROM student WHERE id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);

        int rows = ps.executeUpdate();
        if (rows > 0)
            System.out.println("Student deleted successfully!");
        else
            System.out.println("Student not found!");
    }
}
