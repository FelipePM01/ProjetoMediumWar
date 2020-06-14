package card;

import java.awt.Graphics;

public interface ICardJogador {
	public int getWidth();
	public void paintComponent(Graphics g, int positionX, int positionY);
	public int getHeight();
}
