package tabuleiro;

import java.awt.Image;

import peca.Peca;

public interface ITilePeca {
	public Image getImage();
	public void setPeca(Peca peca);
	public int[] getGUIPosition();
	public int[] getPosition();
}
