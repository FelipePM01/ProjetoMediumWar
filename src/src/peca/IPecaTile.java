package peca;

import Jogador.IJogadorCard;
import card.ICardJogadorPeca;
import tabuleiro.ITilePeca;
import tabuleiro.Tile;

public interface IPecaTile extends IPeca{
	public void moveOrAttack() ;
	public void setTarget(Tile tile);
	public void flip();
	public boolean getInBoard();
	public IJogadorCard getJogador();
	public ITilePeca getTile();
	public abstract double[] getCenterPosition();
	public void setTargetNull();
	public boolean getMorto();
	public void receberDano(double attackDamage, Peca peca);
	public void receberDanoRanged(double dano,Projectile projetil);
	public ICardJogadorPeca getCard();
	public IPecaCardJogador getOrigem();
	public void setInBoard(boolean inBoard);
}
