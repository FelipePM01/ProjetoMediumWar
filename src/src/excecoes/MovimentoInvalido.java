package excecoes;

public class MovimentoInvalido extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2528134903397689302L;
	public MovimentoInvalido() {
		super();
	}
    public MovimentoInvalido(String message) {
	    super(message);
    }
}
