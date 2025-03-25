package condorcet.DataAccessObjects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // JDBC URL for MS SQL Server (make sure to change to your actual database details)
    private static final String URL = "jdbc:sqlserver://127.0.0.6:1433;databaseName=Data2;user=newlogin;password=1111;encrypt=true;trustServerCertificate=true";
//    MS SQL Server URL
    private static final String USER = "newlogin"; // Your MS SQL username
    private static final String PASSWORD = "1111"; // Your MS SQL password
    private static Connection connection;

    // Method to get a connection to the database
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                // Make sure the SQL Server JDBC driver is in your classpath
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new SQLException("Failed to connect to the database", e);
            }
        }
        return connection;
    }
}
