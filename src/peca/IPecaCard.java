package peca;

import java.awt.Graphics;

public interface IPecaCard extends IPeca{

	void printFeature(Graphics g, String string);

	void paintComponent(Graphics g);

}
