package tabuleiro;

import java.awt.Graphics;
import java.awt.Image;

import peca.Peca;

public interface ITileTabuleiro {
	public void paintComponent(Graphics g,Image img);
	public void setPeca(Peca peca);
	public Peca getPeca();
	public void paintPeca(Graphics g);
	public void actionPeca();
	public boolean existsPeca();
	
}
