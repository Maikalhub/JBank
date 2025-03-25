package main.JDBC;

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


}
