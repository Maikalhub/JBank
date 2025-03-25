package condorcet.JDBC;

import java.sql.*;

public class JDBC {
    public static Connection connection = null;

    // Метод для подключения к базе данных
    public static void connect() throws SQLException {
        String url = "jdbc:sqlserver://127.0.0.6:1433;databaseName=Data2;user=newlogin;password=1111;encrypt=true;trustServerCertificate=true";
        connection = DriverManager.getConnection(url);

        if (connection == null) {
            throw new SQLException("Connection failed");
        } else {
            System.out.println("Connected to MS SQL Server");
        }
    }

    // Метод для закрытия соединения с БД
    public static void close() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Closing connection");
            }
        } catch (SQLException e) {
            System.out.println("Failed to close connection!");
        }
    }

    // Метод для вставки данных в таблицу User_A
    public static void insert_User_A(String login, String password) throws SQLException
    {
        String query = "INSERT INTO User_A (Логин, Пароль) VALUES (?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, login);
            stmt.setString(2, password);
            stmt.executeUpdate();
            System.out.println("User inserted successfully");
        }
    }

    // Метод для вставки данных в таблицу Admin_A
    public static void insert_Admin_A(String login, String password) throws SQLException {
        String query = "INSERT INTO Admin_A (Логин, Пароль) VALUES (?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, login);
            stmt.setString(2, password);
            stmt.executeUpdate();
            System.out.println("Admin inserted successfully");
        }
    }

    // Метод для вывода всех пользователей из таблицы User_A
    public static void display_User_A() throws SQLException {
        String query = "SELECT * FROM User_A";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String login = rs.getString("Логин");
                String password = rs.getString("Пароль");
                System.out.println("User - Логин: " + login + ", Пароль: " + password);
            }
        }
    }

    // Метод для вывода всех администраторов из таблицы Admin_A
    public static void display_Admin_A() throws SQLException {
        String query = "SELECT * FROM Admin_A";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String login = rs.getString("Логин");
                String password = rs.getString("Пароль");
                System.out.println("Admin - Логин: " + login + ", Пароль: " + password);
            }
        }
    }

    // Метод для удаления пользователя по логину
    public static void delete_User_A(String login) throws SQLException {
        String query = "DELETE FROM User_A WHERE Логин = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, login);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User with login " + login + " deleted successfully");
            } else {
                System.out.println("User with login " + login + " not found.");
            }
        }
    }

    // Метод для удаления администратора по логину
    public static void delete_Admin_A(String login) throws SQLException {
        String query = "DELETE FROM Admin_A WHERE Логин = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, login);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Admin with login " + login + " deleted successfully");
            } else {
                System.out.println("Admin with login " + login + " not found.");
            }
        }
    }

    // Метод для обновления пароля пользователя по логину
    public static void updateUser_Password_A(String login, String newPassword) throws SQLException {
        String query = "UPDATE User_A SET Пароль = ? WHERE Логин = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, newPassword);
            stmt.setString(2, login);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Password for user with login " + login + " updated successfully");
            } else {
                System.out.println("User with login " + login + " not found.");
            }
        }
    }

    // Метод для обновления пароля администратора по логину
    public static void update_Password_Admin_A(String login, String newPassword) throws SQLException {
        String query = "UPDATE Admin_A SET Пароль = ? WHERE Логин = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, newPassword);
            stmt.setString(2, login);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Password for admin with login " + login + " updated successfully");
            } else {
                System.out.println("Admin with login " + login + " not found.");
            }
        }
    }

    // Метод для вывода всех записей из таблицы AdminID
    public static void display_AdminID() throws SQLException {
        String query = "SELECT * FROM AdminID";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String login = rs.getString("Логин");
                String fio = rs.getString("ФИО");
                String должность = rs.getString("Должность");
                String статус = rs.getString("Статус");
                double зарплата = rs.getDouble("Зарплата");
                String номерГруппы = rs.getString("НомерГруппы");
                System.out.println("UserID - Логин: " + login + ", ФИО: " + fio + ", Должность: " + должность +
                        ", Статус: " + статус + ", Зарплата: " + зарплата + ", НомерГруппы: " + номерГруппы);
            }
        }
    }

    // Метод для вывода всех записей из таблицы UserID
    public static void display_UserID() throws SQLException {
        String query = "SELECT * FROM UserID";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String login = rs.getString("Логин");
                String fio = rs.getString("ФИО");
                String должность = rs.getString("Должность");
                String статус = rs.getString("Статус");
                double зарплата = rs.getDouble("Зарплата");
                String номерГруппы = rs.getString("НомерГруппы");
                System.out.println("UserID - Логин: " + login + ", ФИО: " + fio + ", Должность: " + должность +
                        ", Статус: " + статус + ", Зарплата: " + зарплата + ", НомерГруппы: " + номерГруппы);
            }
        }
    }



    // Метод для получения пользователя по логину и паролю
    public static void get_ByLoginAndPassword_User_A(String login, String password) throws SQLException {
        String query = "SELECT * FROM User_A WHERE Логин = ? AND Пароль = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, login);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String foundLogin = rs.getString("Логин");
                    String foundPassword = rs.getString("Пароль");
                    System.out.println("Found User - Логин: " + foundLogin + ", Пароль: " + foundPassword);
                } else {
                    System.out.println("User with login " + login + " and provided password not found.");
                }
            }
        }
    }

    // Метод для получения администратора по логину и паролю
    public static void getLoginAndPassword_Admin_A(String login, String password) throws SQLException {
        String query = "SELECT * FROM Admin_A WHERE Логин = ? AND Пароль = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, login);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String foundLogin = rs.getString("Логин");
                    String foundPassword = rs.getString("Пароль");
                    System.out.println("Found Admin - Логин: " + foundLogin + ", Пароль: " + foundPassword);
                } else {
                    System.out.println("Admin with login " + login + " and provided password not found.");
                }
            }
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void setConnection(Connection connection) {
        JDBC.connection = connection;
    }
}
