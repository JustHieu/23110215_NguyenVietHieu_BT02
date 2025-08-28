package vn.hieunguyen.configs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectMySql {
	private static Connection con = null;
	   private static String USERNAME = "root";
	   private static String PASSWORD = "123456";
	   private static String DRIVER = "com.mysql.cj.jdbc.Driver";
	   private static String URL = "jdbc:mysql://localhost:3306/ltweb";

	   public static Connection getDatabaseConnection() throws SQLException, Exception{
	       try {
			Class.forName(DRIVER);
		    return con = DriverManager.getConnection(URL,USERNAME,PASSWORD);

		   } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		   }
	       return null;
	   }
	   public static void main(String[] args) {
		try {
			new DBConnectMySql();
			System.out.println(DBConnectMySql.getDatabaseConnection());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
