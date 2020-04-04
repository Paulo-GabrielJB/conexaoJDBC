package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;

public class Program {

	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn =DB.getConnection();
			st = conn.prepareStatement(
					"UPDATE seller "
					+ "SET BaseSalary = BaseSalary + ? "
					+ "WHERE"
					+ "(DepartmentId = ?)");
			
			st.setDouble(1, 150.0); //primeiro parametro
			st.setInt(2, 1);
			
			int rowsAffected = st.executeUpdate();
			
			System.out.println("Done! Rows Affected " + rowsAffected);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DB.closePreparedStatement(st);
			DB.closeConnection();
		}
		
	}

}
