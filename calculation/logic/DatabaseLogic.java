package logic;

import java.sql.*;

public class DatabaseLogic {

	private static Connection con = null;
	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/";
	private static String dbName = null;
	private static int count = 0;

	public DatabaseLogic(String dbName) {
		this.dbName = dbName;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url + dbName, "root", "1111");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void createTable(String tableName) {
		try {
			Statement st = con.createStatement();
			st.executeUpdate("CREATE TABLE "
					+ tableName
					+ "(id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, expression VARCHAR(100), result VARCHAR(100))");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addToDB(String tableName, String expression) {

		try {
			Statement st = con.createStatement();
			st.execute("INSERT INTO " + tableName
					+ "(expression, result) VALUES ('" + expression + "', '')");
			count++;
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public String getExpression(String tableName) {
		String expression = "";

		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT expression FROM "
					+ tableName);

			while (rs.next()) {
				expression = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return expression;
	}

	public void addResultDB(String tableName, String expression, String result) {

		try {
			Statement st = con.createStatement();
			st.execute("INSERT INTO " + tableName
					+ "(expression, result) VALUES ('" + expression + "', '"
					+ result + "')");
			count++;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addResultDB(String tableName, String result) {
		try {
			Statement st = con.createStatement();
			st.execute("UPDATE " + tableName + " SET result = '" + result
					+ "' WHERE id = '" + count + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
