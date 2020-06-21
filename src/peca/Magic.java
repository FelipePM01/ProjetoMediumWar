package peca;

import java.awt.Image;

import javax.swing.ImageIcon;

import tabuleiro.Tabuleiro;

public class Magic extends Projectile{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3312309211347542268L;
	//private Image imgMagic;
	public Magic(double scale, double[] position, IPecaTile target, double dano, Tabuleiro tabuleiro){
		super(scale, position, target, dano, tabuleiro,12);
		setCenterCorrection(7,4);
		setGUI(scale,"assets/magic.png");
		int[] edge = {5,3};
		setImgEdge(edge);	
	}
}
