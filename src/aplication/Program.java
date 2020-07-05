package aplication;

import java.util.Date;

import camada.dao.DaoFactory;
import camada.dao.FuncionarioDao;
import camada.model.Departamento;
import camada.model.Funcionario;



public class Program {

	public static void main(String[] args){

		FuncionarioDao func = DaoFactory.createFuncionarioDao();
		
		Funcionario seller = func.findById(3);
		
		System.out.println(seller);
	}

}
