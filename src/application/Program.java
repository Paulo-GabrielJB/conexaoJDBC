package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;
import db.DbIntegrityException;

public class Program {

	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn =DB.getConnection();
			st = conn.prepareStatement(
					"DELETE FROM seller "
					+ "WHERE "
					+ "Id = ?"
					);
			
			st.setInt(1, 8);
			
			int rowsAffected = st.executeUpdate();
			
			System.out.println("Done! Rows Affected " + rowsAffected);
			
		}catch(SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		}finally {
			DB.closePreparedStatement(st);
			DB.closeConnection();
		}
		
	}

}
