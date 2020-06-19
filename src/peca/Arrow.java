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
	//hitBox flecha, ponto central da flecha antes da escala 4x2, margem x é 5 e margem y é 1
	//posição central magia 7x4, margem y é 3 e 5 no x;
	public Arrow(double scale, double[] position, Peca alvo){
		super(scale, position, alvo);
		setHitBox(4,2);
		setGUI(scale);
	}
	public void setGUI(double scale) {
		ImageIcon refimg=new ImageIcon("assets/arrow.png");
		imgArrow=refimg.getImage();
		imgArrow=imgArrow.getScaledInstance((int)(imgArrow.getWidth(null)*scale),(int)(imgArrow.getHeight(null)*scale),Image.SCALE_DEFAULT);
	}
}
