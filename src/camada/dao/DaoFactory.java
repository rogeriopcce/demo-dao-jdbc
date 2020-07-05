package camada.dao;

import camada.dao.impl.FuncionarioJDBC;

public class DaoFactory {

		public static FuncionarioDao createFuncionarioDao() {
			return new FuncionarioJDBC();
		}
		
}
