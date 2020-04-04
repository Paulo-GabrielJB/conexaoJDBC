package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {

	public static void main(String[] args) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;
		PreparedStatement pst = null;
		try {
			
			conn = DB.getConnection();
			
			pst = conn.prepareStatement(
					"INSERT INTO seller "
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
					+ "VALUES"
					+ "(?, ?, ?, ?, ?)"); //as interrogações são os placeholdes, como são 5 colunas, temos 5 interrogações
			
			pst.setString(1, "Paulo Gabriel"); //primeira coluna do tipo string
			pst.setString(2, "paulo.gabriel@gmail.com"); //segunda coluna do tipo string
			pst.setDate(3, new java.sql.Date(sdf.parse("10/08/1999").getTime())); //terceira coluna do tipo Date, tem q ser o Date do SQL
			pst.setDouble(4, 2750.0); //quarta coluna do tipo double
			pst.setInt(5, 1); //quinta coluna do tipo int
			
			int rowsAffected = pst.executeUpdate(); //executa a query e retorna as linhas afetadas
			
			System.out.println("Done " + rowsAffected);
					
			
		}catch(SQLException | ParseException e) {
			e.printStackTrace();
		} finally {
			DB.closePreparedStatement(pst);
			DB.closeConnection();
		}
		
	}

}
