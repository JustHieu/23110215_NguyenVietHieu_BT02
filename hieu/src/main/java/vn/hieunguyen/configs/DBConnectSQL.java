package vn.hieunguyen.configs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectSQL {
    private final String serverName = "localhost";
    private final String dbName     = "LTWebTest";
    private final String portNumber = "1433";        // dùng khi KHÔNG xài instance hoặc instance dùng cổng cố định
    private final String instance   = "SQLEXPRESS";  // để "" hoặc null nếu không dùng named instance
    private final String userID     = "sa";
    private final String password   = "Hieu228!";

    static {
        try {
        	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("[DB] SQLServerDriver loaded");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("SQLServer JDBC driver not found", e);
        }
    }

    public Connection getConnection() throws SQLException {
        final String hostPart;
        if (instance != null && !instance.isBlank()) {
            hostPart = serverName + "\\" + instance;
        } else {
            hostPart = serverName + ":" + portNumber;
        }

        String url = "jdbc:sqlserver://localhost:1433;databaseName=LTWebTest;encrypt=false";

        System.out.println("[DB] Connecting to: " + url);
        return DriverManager.getConnection(url, userID, password);
    }
    public static void main(String[] args) {
		
	}
}
