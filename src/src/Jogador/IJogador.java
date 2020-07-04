package Jogador;

import java.awt.Graphics;

public interface IJogador extends IJogadorCard, IJogadorBanco{
	public void addPoint();
	public int getPoints();
	public int obtainCursor();
	public void hideCursor();
	public void pressedA() ;
	public void pressedC() ;
	public void pressedJ();
	public void pressedD() ;
	public void pressedLEFT();
	public void pressedRIGHT();
	public void pressedZ() ;
	public void pressedE();
	public void pressedX();
	public void pressedN();
	public void pressedK() ;
	public void pressedL() ;
	public void pressedSPACE() ;
	public void pressedENTER() ;
	public void paintComponent(Graphics g);	
}
