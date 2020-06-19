package peca;

import Jogador.Jogador;
import tabuleiro.ITilePeca;
import tabuleiro.Tile;

public interface IPecaTile extends IPeca{
	public void moveOrAttack();
	public void setTarget(Tile tile);
	public void flip();
	public boolean getInBoard();
	public Jogador getJogador();
	public ITilePeca getTile();
	public void receberDano(double dano);
	public double[] getCenterPosition();

}
