package tabuleiro;

import Jogador.IJogador;

public interface ITabuleiro extends ITabuleiroTile,ITabuleiroGame,ITabuleiroJogador {

	public void setJogador(IJogador jogador1);

	public void pressedSPACE();
	public void pressedENTER() ;
	public void pressedW() ;
	public void pressedS() ;
	public void pressedA() ;
	public void pressedD() ;
	public void pressedUP() ;
	public void pressedDOWN();
	public void pressedLEFT() ;
	public void pressedRIGHT() ;

}
