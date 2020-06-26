package banco;

import Jogador.IJogador;

public interface IBanco extends IBancoGame,IBancoCard,IBancoJogador {
	public void setJogador(IJogador jogador);
	public void pressedLEFT();
	public void pressedRIGHT();
	public void pressedA();
	public void pressedD();
	public void pressedSPACE();
	public void pressedENTER();
	public void pressedE();
	public void pressedDoisPontos();
}
