package Project;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class DBManager {
	static ResultSet data;

	/* getting the DB Connection */

	public static Connection getDBConnection() throws SQLException {
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String dbURL = "jdbc:sqlserver://is-swang01.ischool.uw.edu:1433; DatabaseName=VechileManagementSystem";

		String userid = "INFO6210";
		String passwd = "NEUHusky!";

		Connection conn = null;

		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(dbURL, userid, passwd);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}

	/* fetching all the data without filters */

	public static ResultSet getAllData() throws SQLException {
		String query = "select * from CarInventory";

		data = queryExecution(query);
		return data;
	}

	/* fetching all the data with filters */

	public static ResultSet getAllDataWithFilters(String parameters) throws SQLException {
		String queryWithFilters = "select * from CarInventory where " + parameters;
		data = queryExecution(queryWithFilters);
		return data;
	}

	/*
	 * executing the query, where query is passed as a parameter to the below
	 * function
	 */

	public static ResultSet queryExecution(String query) throws SQLException {
		Connection con = null;
		con = getDBConnection();

		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			return rs;
		}
	

		catch (SQLException ex) {
			ex.printStackTrace();
		} 
		return null;
	}

	public static void main(String[] args) throws SQLException {

		 ResultSet arr = getAllData();
		//ResultSet arr = getAllDataWithFilters("brand = 'TESLA'");
		ResultSetMetaData metadata = arr.getMetaData();
		int columnCount = metadata.getColumnCount();
		ArrayList<String> columnValues = new ArrayList<String>();

		while (arr.next()) {

			for (int i = 1; i <= columnCount; i++) {
				columnValues.add(arr.getString(i));

			}

		}
		System.out.println(columnValues);
	}

}
