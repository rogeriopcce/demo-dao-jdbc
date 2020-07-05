package camada.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import camada.conexao.DB;
import camada.conexao.DbException;
import camada.dao.FuncionarioDao;
import camada.model.Departamento;
import camada.model.Funcionario;

public class FuncionarioJDBC implements FuncionarioDao{

	private Connection conn;
	
	public FuncionarioJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Funcionario obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Funcionario obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Funcionario findById(Integer id) {

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department " 
					+ "ON seller.DepartmentId = department.Id " 
					+ "WHERE seller.Id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if ( rs.next() ) {
				Departamento dep = instantiateDepartamento(rs);
				
				Funcionario obj =instantiateFuncionario(rs, dep);
				return obj;
				
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private Funcionario instantiateFuncionario(ResultSet rs, Departamento dep) throws SQLException {
		 	Funcionario obj = new Funcionario();
			obj.setId(rs.getInt("Id"));
			obj.setName(rs.getString("Name"));
			obj.setEmail(rs.getString("Email"));
			obj.setBaseSalary(rs.getDouble("BaseSalary"));
			obj.setBirthDate(rs.getDate("BirthDate"));
			obj.setDepartamento(dep);
			return obj;
	}

	private Departamento instantiateDepartamento(ResultSet rs) throws SQLException {
		Departamento dep = new Departamento();
		dep.setId(rs.getInt("Departmentid"));
		dep.setName(rs.getString("DepName"));
		return dep;
	}

	@Override
	public List<Funcionario> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
