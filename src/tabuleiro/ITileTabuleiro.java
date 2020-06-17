package tabuleiro;

import java.awt.Graphics;
import java.awt.Image;

import peca.IPecaTile;
import peca.Peca;

public interface ITileTabuleiro {
	public void paintComponent(Graphics g,Image img);
	public void setPeca(Peca peca);
	public IPecaTile getPeca();
	public void paintPeca(Graphics g);
	public Image getImage();
	public void nullTarget();
	public void actionPeca();
	public boolean existsPeca();
}
