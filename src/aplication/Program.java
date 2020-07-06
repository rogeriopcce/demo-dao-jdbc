package aplication;

import java.util.Date;
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
		
		System.out.println("\n=== Teste 02: Funcionarios findByDepartamento ====");
		Departamento dep = new Departamento(2, null);
		List<Funcionario> lista = func.findbyDepartamento(dep);
		for (Funcionario obj : lista ) {
			System.out.println(obj);
		}

		System.out.println("\n=== Teste 03: Funcionarios findAll ====");

		lista = func.findAll();
		for (Funcionario obj : lista ) {
			System.out.println(obj);
		}
		
		System.out.println("\n=== Teste 04: Funcionarios insert ====");

		Funcionario newSeller = new Funcionario(null, "Greg", "greg@gmail.com", new Date(), 4000.00, dep);
		func.insert(newSeller);
	}

}
