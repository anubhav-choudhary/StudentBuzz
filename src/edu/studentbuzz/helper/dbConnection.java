package edu.studentbuzz.helper;
import java.sql.*;
public class dbConnection {
	private Connection con;
	private String dbName;
	private String dbUser;
	private String dbPass;
	private String dbDriver;
	private String dbHost;
	private String dbPort;
	public dbConnection() {
		dbName = "studentbuzz";
		dbUser = "root";
		dbPass = "tiger";
		dbDriver = "com.mysql.jdbc.Driver";
		dbHost = "localhost";
		dbPort = "3306";
	}
	public void open() {
		try {
			Class.forName(dbDriver);
			con = DriverManager.getConnection("jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName, dbUser, dbPass);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	public void close() {
		try {
			con.close();
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
	}
	public int executeUpdate(String SQL) {
		try {
			Statement stmt = con.createStatement();
			return stmt.executeUpdate(SQL);
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return 0;
	}
	public ResultSet executeQuery(String SQL) {
		try {
			Statement stmt = con.createStatement();
			return stmt.executeQuery(SQL);
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return null;
	}
}