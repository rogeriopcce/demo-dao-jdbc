package aplication;

import java.util.Date;

import camada.model.Departamento;
import camada.model.Funcionario;



public class Program {

	public static void main(String[] args){

		Departamento obj = new Departamento(1,"Financeiro"); 
		System.out.println(obj);
		
		Funcionario seller = new Funcionario(21, "Bob", "bob@gmail.com", new Date(), 3000.0, obj );
		System.out.println(seller);
	}

}
