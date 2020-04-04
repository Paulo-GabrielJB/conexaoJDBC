package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;

public class Program {

	public static void main(String[] args) {
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			conn = DB.getConnection(); //pega a conexão do banco de dados
			
			st = conn.createStatement(); //instancia o statement
			
			rs = st.executeQuery("select * from department"); //executa a query
			
			while(rs.next()) 
				System.out.println(rs.getInt("Id") + ", " + rs.getString("name")); //pega os valores do campo pelo nome da coluna
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}

}
