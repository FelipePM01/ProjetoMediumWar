package card;

import java.awt.Graphics;

import peca.IPecaCard;
import peca.IPecaCardBanco;
import peca.IPecaCardJogador;
import peca.Peca;

public interface ICardJogador {
	public int getWidth();
	public void paintComponent(Graphics g, int positionX, int positionY);
	public int getHeight();
	public boolean ehNulo();
	public void setCardAtual(String cor);
	public void setPeca(IPecaCardBanco recebido);
	public IPecaCardJogador getPeca();
	public boolean getNaoColocado();
	
}
