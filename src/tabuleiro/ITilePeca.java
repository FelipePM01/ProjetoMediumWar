package tabuleiro;

import java.awt.Image;

import peca.IPecaTile;
import peca.Peca;

public interface ITilePeca {
	public Image getImage();
//	public void setPeca(IPecaTile peca);
	public int[] getGUIPosition();
	public int[] getPosition();
	public Tile[][] getOtherTiles();
}
