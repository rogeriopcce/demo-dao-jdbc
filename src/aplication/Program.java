package aplication;

import java.util.List;
import camada.dao.DaoFactory;
import camada.dao.FuncionarioDao;
import camada.model.Departamento;
import camada.model.Funcionario;



public class Program {

	public static void main(String[] args){

		FuncionarioDao func = DaoFactory.createFuncionarioDao();
		
		System.out.println("=== Teste 01: Funcionarios findById ====");
		Funcionario seller = func.findById(3);
		System.out.println(seller);
		
		System.out.println("\n === Teste 02: Funcionarios findByDepartamento ====");
		Departamento dep = new Departamento(2, null);
		List<Funcionario> lista = func.findbyDepartamento(dep);
		for (Funcionario obj : lista ) {
			System.out.println(obj);
		}
	}

}
