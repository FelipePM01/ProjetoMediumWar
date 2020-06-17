package peca;

import tabuleiro.Tile;

public interface IPecaTile extends IPeca{
	public void moveOrAttack();
	public void setTarget(Tile tile);
}
