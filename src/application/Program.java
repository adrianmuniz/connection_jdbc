package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;

public class Program {

	public static void main(String[] args) {
		
		//conexão com obanco de dados
		Connection conn = null;
		//consulta sql
		Statement st = null;
		//resultado da consulta
		ResultSet rs= null;
		
		try {
			conn = DB.getConnection();
			
			//instanciando
			st = conn.createStatement();
			
			//comando sql e atribuindo ao resultado
			rs = st.executeQuery("select * from department");
			
			//percorrendo e imprimindo
			while(rs.next()) {
				System.out.println(rs.getInt("id") + ", " + rs.getString("name"));
			}	
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
			DB.closeConnection();
		}	
	}
}