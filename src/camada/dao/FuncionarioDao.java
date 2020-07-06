package camada.dao;

import java.util.List;
import camada.model.Departamento;
import camada.model.Funcionario;

public interface FuncionarioDao {

	void insert(Funcionario obj);
	void update(Funcionario obj);
	void deleteById(Integer id);
	Funcionario findById(Integer id);
	List<Funcionario> findAll();
	List<Funcionario> findbyDepartamento(Departamento departamento);
}
