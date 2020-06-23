package peca;

import java.awt.Graphics;

public interface IPecaCard extends IPecaCardBanco, IPecaCardJogador{

	void printFeature(Graphics g, String string);

}
