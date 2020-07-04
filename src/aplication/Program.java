package aplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import camada.conexao.DB;
import camada.conexao.DbEntegrityException;



public class Program {

	public static void main(String[] args){

		Connection conn = null;
		PreparedStatement st = null;
		

		try {
			conn = DB.getConnection();
			st = conn.prepareStatement("DELETE FROM DEPARTMENT WHERE ID = ?");
			
			st.setInt(1, 2);

			int totallinhas = st.executeUpdate();
			
			System.out.println("Total de linhas afetadas " + totallinhas);
		} catch (SQLException e) {

			//throw new DbEntegrityException(e.getMessage());
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			
		} finally {
			
			DB.closeStatement(st);
			DB.closeConnetion();
			
		}
		
	}

}
