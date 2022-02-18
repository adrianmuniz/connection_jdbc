package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Connection conn = null;
		
		PreparedStatement st = null;
		
		//inserindo dados
		try {
			conn = DB.getConnection();
			
			//espera como argumento, String como comando SQL
			st = conn.prepareStatement(
					"INSERT INTO seller "
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId)"
					+ "VALUES "
					//placeholder, colocamos depois os valores
					+ "(?, ?, ?, ?, ?)");
			
			//vamos trocar as interrogações
			st.setString(1, "Cainãdrian");
			st.setString(2, "caina@email.com");
			//preenchendo o campo Date
			st.setDate(3, new java.sql.Date(sdf.parse("19/11/2000").getTime()));
			st.setDouble(4, 3000.0);
			st.setInt(5, 4);
			
			int rowsAffect =  st.executeUpdate();
			
			System.out.println("Done! Rows affected "+ rowsAffect);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ParseException p) {
			p.printStackTrace();
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}