package peca;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class Arrow extends Projectile{
	/**
	 * 
	 */
	private static final long serialVersionUID = -70153469698118573L;
	//private BufferedImage imgArrow;
	private Image imgArrow;
	//ponto central da flecha antes da escala 4x2, margem x � 5 e margem y � 1
	//ponto central magia nates da escala 7x4,margem x � 5 e margem y � 3
	public Arrow(double scale, double[] position, Peca alvo){
		super(scale, position, alvo);
		setCenterCorrection(4,2);
		setGUI(scale);
		int[] edge = {5,1};
		setImgEdge(edge);
	}
	public void setGUI(double scale) {
		ImageIcon refimg=new ImageIcon("assets/arrow.png");
		imgArrow=refimg.getImage();
		imgArrow=imgArrow.getScaledInstance((int)(imgArrow.getWidth(null)*scale),(int)(imgArrow.getHeight(null)*scale),Image.SCALE_DEFAULT);
	}
}
