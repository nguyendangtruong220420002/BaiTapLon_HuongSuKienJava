package connectBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Connect_DB {
	public static Connection con  = null;
	private static Connect_DB instance = new Connect_DB();
	public static Connect_DB getInstance() {
		return instance;
	}
	public static void setInstance(Connect_DB instance) {
		Connect_DB.instance = instance;
	}
	public void connet() throws SQLException{
		String url = "jdbc:sqlserver://localhost:1433;databaseName=QuanCaPhe";
		String user = "sa";
		String password = "123456789";
		con = DriverManager.getConnection(url, user, password);
	}
	public void disconnect() {
		if(con != null)
			try {
				con.close();
			} catch (SQLException e ) {
				e.printStackTrace();
			}
	}
	public static Connection getConnection() {
		return con;
		
	}
	public ResultSet executeQuery(String sql) {
		ResultSet rs = null;
	    try {
	        rs = con.createStatement().executeQuery(sql);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return rs;
	}
}
