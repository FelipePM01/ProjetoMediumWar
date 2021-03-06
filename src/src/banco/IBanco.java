package banco;

import java.awt.Graphics;

import Jogador.IJogador;

public interface IBanco extends IBancoCard,IBancoJogador {
	public void setJogador(IJogador jogador);
	public void pressedLEFT();
	public void pressedRIGHT();
	public void pressedA();
	public void pressedD();
	public void pressedSPACE();
	public void pressedENTER();
	public void pressedE();
	public void pressedN();
	public void refresh();
	public void paintComponent(Graphics g);
}
