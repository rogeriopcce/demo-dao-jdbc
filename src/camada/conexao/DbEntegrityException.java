package camada.conexao;

public class DbEntegrityException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public DbEntegrityException(String msg) {
		super(msg);
	}
	
}
