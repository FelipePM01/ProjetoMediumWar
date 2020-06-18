package card;

import java.awt.Graphics;

import peca.IPecaCard;

public interface ICardBanco {
	public void setCardAtual(String cor);
	public void paintComponent(Graphics g, int positionX, int positionY);
	public void setPeca(IPecaCard peca);
	public IPecaCard getPeca();
	public int getWidth();
	public int getHeight();
	public int[] getGUIPosition();
}
