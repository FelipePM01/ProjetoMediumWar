package peca;

import java.awt.Graphics;

public interface IPecaCard extends IPeca{

	public void printFeature(Graphics g, String string);

	public void paintComponent(Graphics g);

}
