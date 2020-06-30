package tabuleiro;

import java.awt.Graphics;

import Jogador.IJogador;
import peca.Projectile;

public interface ITabuleiro extends ITabuleiroTile,ITabuleiroJogador {

	public void setJogador(IJogador jogador);

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
	public void pressedQ();
	public void pressedM();

	public void removeProjectiles(Projectile projectile);
	public void paintComponent(Graphics g);
	public Tile[][] getTiles();
	public void start();
	public void clear();

	public int[] getIntab();
}
