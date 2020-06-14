package tabuleiro;

import java.awt.Graphics;

public interface ITabuleiroGame {
	public void paintComponent(Graphics g);
	public Tile[][] getTiles();
	public void start();
	public void clear();
}
