package excecoes;

public class PosicaoOcupada extends MovimentoInvalido {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2496905074610406665L;
	   public PosicaoOcupada() {
		   super();
	   }
	   public PosicaoOcupada(String message) {
		   super(message);
	   }	
}
