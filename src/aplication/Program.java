package aplication;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import camada.dao.DaoFactory;
import camada.dao.FuncionarioDao;
import camada.model.Departamento;
import camada.model.Funcionario;



public class Program {

	public static void main(String[] args){
		
		Scanner sc = new Scanner(System.in);

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
		
		System.out.println("\n=== Teste 05: Funcionarios Update ====");
		seller = func.findById(1);
		seller.setName("Martha Weine");
		func.update(seller);
		
		System.out.println("\n=== Teste 06: Funcionarios Delete ====");
		System.out.println("Digite um código para excluir: ");
		int newId = sc.nextInt();
		func.deleteById(newId);
		System.out.println("Exclusão com sucesso!!!");
		sc.close();
		
	}

}
