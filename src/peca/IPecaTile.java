package peca;

import java.awt.Graphics;

import tabuleiro.Tile;

public interface IPecaTile {
	public void moveOrAttack();
	public void paintComponent(Graphics g, int positionX, int positionY);
	public void setTarget(Tile tile);
}
