package dataSort;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {

	public static PreparedStatement pstmt = null;
	public static Connection c = null;
	public static Statement stmt = null;

	// sets up database and database connections
	// creates the database headers and the prepared statement outline
	public static void dbSetup() throws Exception {
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:ms3Interview.db");
		c.setAutoCommit(false);
		stmt = c.createStatement();
		String sql = "CREATE TABLE MS3INTERVIEW " + "(A TEXT NOT NULL," + " B TEXT NOT NULL, " + " C TEXT NOT NULL, "
				+ " D TEXT NOT NULL, " + "E TEXT NOT NULL," + "F TEXT NOT NULL," + "G DECIMAL NOT NULL,"
				+ "H BOOL NOT NULL," + "I BOOL NOT NULL," + "J TEXTNOT NULL)";
		stmt.executeUpdate(sql);
		System.out.println("Table created successfully");
		String sql2 = "INSERT INTO MS3INTERVIEW (A,B,C,D,E,F,G,H,I,J) " + "VALUES (?,?,?,?,?,?,?,?,?,?)";
		pstmt = c.prepareStatement(sql2);
	}

	// adds the data from the csv to a prepared statement and then a batch
	// batch commit is used to push all data at once for efficiency
	public static void dbAddBatch(String line) throws SQLException {

		String splitBy = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";
		String[] employee = line.split(splitBy);
		pstmt.setString(1, employee[0]);
		pstmt.setString(2, employee[1]);
		pstmt.setString(3, employee[2]);
		pstmt.setString(4, employee[3]);
		pstmt.setString(5, employee[4]);
		pstmt.setString(6, employee[5]);
		pstmt.setString(7, employee[6]);
		pstmt.setString(8, employee[7]);
		pstmt.setString(9, employee[8]);
		pstmt.setString(10, employee[9]);
		pstmt.addBatch();
	}

	// execute and commit everything to database, close open connections
	public static void dbClean() throws SQLException {
		pstmt.executeBatch();
		c.commit();
		stmt.close();
		pstmt.close();
		c.close();
	}

	// getters and setters
	public static PreparedStatement getPstmt() {
		return pstmt;
	}

	public static void setPstmt(PreparedStatement pstmt) {
		DataBase.pstmt = pstmt;
	}

	public static Connection getC() {
		return c;
	}

	public static void setC(Connection c) {
		DataBase.c = c;
	}

	public static Statement getStmt() {
		return stmt;
	}

	public static void setStmt(Statement stmt) {
		DataBase.stmt = stmt;
	}
}