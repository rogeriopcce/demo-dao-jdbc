package camada.dao;

import camada.conexao.DB;
import camada.dao.impl.DepartamentoJDBC;
import camada.dao.impl.FuncionarioJDBC;

public class DaoFactory {

		public static FuncionarioDao createFuncionarioDao() {
			return new FuncionarioJDBC(DB.getConnection());
		}

		public static DepartamentoDao createDepartmentDao() {
			return new DepartamentoJDBC(DB.getConnection());
		}
		
}
