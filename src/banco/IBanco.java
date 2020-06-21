package banco;

import Jogador.IJogador;

public interface IBanco extends IBancoGame,IBancoCard,IBancoJogador {

	void setJogador(IJogador jogador1);
	public int obtainCursor(String cor);
	public void hideCursor(int i);

	public void pressedLEFT() ;
	public void pressedRIGHT() ;
	public void pressedA();
	public void pressedD() ;
	public void pressedSPACE() ;
	public void pressedENTER() ;

}
