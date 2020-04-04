package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {

	private static Connection conn = null;

	public static Connection getConnection() {
		
		if(conn == null) { //verifica se a conexão é null
			try {
				Properties props = loadProperties(); //carrega o arquivo db.properties que esta na raiz do projeto
				String url = props.getProperty("dburl"); //caputra a url do banco de dados do arquivo carregado
				conn = DriverManager.getConnection(url, props); //pega a conexao com o banco de dados
			}catch(SQLException e) {
				throw new DbException(e.getMessage());
			}
			
		}
		return conn;
		
	}
	
	public static void closeConnection() {
		
		if(conn != null) 
			try {
				conn.close(); //fecha a conexao com o banco de dados
			}catch(Exception e) {
				throw new DbException(e.getMessage());
			}
		
		
	}

	private static Properties loadProperties() {

		try (FileInputStream fs = new FileInputStream("db.properties")) { //carrega o arquivo db.properties que esta na raiz do projeto
			Properties props = new Properties(); //instancia um novo objeto do tipo Properties que é o que guarda as propriedades
			props.load(fs); //carrega as propriedades do arquivo
			return props; //retornar essas propriedades
		} catch (IOException e) {
			throw new DbException(e.getMessage());
		}

	}
	
	public static void closeStatement(Statement st) {
		try {
			if(st != null)
				st.close();
		} catch(SQLException e){
			throw new DbException(e.getMessage());
		}
	}
	
	public static void closeResultSet(ResultSet rs) {
		try {
			if(rs != null)
				rs.close();
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	public static void closePreparedStatement(PreparedStatement pst) {
		try {
			if(pst != null)
				pst.close();
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

}
