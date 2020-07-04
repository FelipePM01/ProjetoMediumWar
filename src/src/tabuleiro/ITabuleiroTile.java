package tabuleiro;

import peca.Projectile;

public interface ITabuleiroTile  {
	public double getScale();
	public Tile[][] getTiles();
	public void eliminateInTab(int i);
	public void addProjectiles(Projectile projetil);
}