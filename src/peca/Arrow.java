package peca;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import tabuleiro.Tabuleiro;

public class Arrow extends Projectile{
	/**
	 * 
	 */
	private static final long serialVersionUID = -70153469698118573L;
	private Image imgArrow;
	//ponto central da flecha antes da escala 4x2, margem x � 5 e margem y � 1
	public Arrow(double scale, double[] position, IPecaTile target, double dano,Tabuleiro tabuleiro,IPecaCardJogador origem){
		super(scale, position, target, dano, tabuleiro,20,origem);
		setCenterCorrection(4,2);
		setGUI(scale, "assets/arrow.png");
		int[] edge = {5,3};
		setImgEdge(edge);	
	}
}
