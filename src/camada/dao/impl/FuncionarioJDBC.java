package camada.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO seller "
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
					+ "VALUES (?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getName());
			st.setString(2, obj.getEmail());
			st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
			st.setDouble(4, obj.getBaseSalary());
			st.setInt(5, obj.getDepartamento().getId());
			
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if ( rs.next() ){
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			}
			else {
				throw new DbException("Nenhuma lina inserida. Erro inesperado!!!"); 
			}
		}
		catch (SQLException e){
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(Funcionario obj) {

		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE seller "
					+"SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? "
					+"WHERE Id = ?");
			
			st.setString(1, obj.getName());
			st.setString(2, obj.getEmail());
			st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
			st.setDouble(4, obj.getBaseSalary());
			st.setInt(5, obj.getDepartamento().getId());
			st.setInt(6, obj.getId());
			
			st.executeUpdate();
		}
		catch (SQLException e){
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}		
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
		PreparedStatement st = null;
		ResultSet rs = null;
	
		try {
			st = conn.prepareStatement("SELECT seller.*, department.Name as DepName "
									+ "FROM seller INNER JOIN department "
									+ "ON seller.DepartmentId = department.Id "
									+ "ORDER BY Name");
			
	
			rs = st.executeQuery();
	
			Map<Integer, Departamento> map = new HashMap();
			List<Funcionario> lista = new ArrayList<>();
			
			while ( rs.next() ) {

				Departamento dep = map.get(rs.getInt("DepartmentId"));
				if (dep == null) {
					dep = instantiateDepartamento(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}

				Funcionario obj =instantiateFuncionario(rs, dep);
				
				lista.add(obj);
			}
			return lista;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Funcionario> findbyDepartamento(Departamento departamento) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT seller.*, department.Name as DepName "
									+ "FROM seller INNER JOIN department "
									+ "ON seller.DepartmentId = department.Id "
									+ "WHERE DepartmentId = ? "
									+ "ORDER BY Name");
			
			st.setInt(1, departamento.getId());
			
			rs = st.executeQuery();
	
			Map<Integer, Departamento> map = new HashMap();
			List<Funcionario> lista = new ArrayList<>();
			
			while ( rs.next() ) {

				Departamento dep = map.get(rs.getInt("DepartmentId"));
				if (dep == null) {
					dep = instantiateDepartamento(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}

				Funcionario obj =instantiateFuncionario(rs, dep);
				
				lista.add(obj);
			}
			return lista;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	
	
}
