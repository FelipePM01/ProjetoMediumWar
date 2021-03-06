package tabuleiro;

import java.awt.Graphics;
import java.awt.Image;

import peca.IPecaCardJogador;
import peca.IPecaTile;

public interface ITileTabuleiro {
	public void paintComponent(Graphics g,Image img);
	public IPecaTile getPeca();
	public void paintPeca(Graphics g);
	public Image getImage();
	public void nullTarget();
	public void actionPeca();
	public boolean existsPeca();
	public void setTileAtual(String cor);
	public void setPeca(IPecaCardJogador peca);
	public void setNull();
}
