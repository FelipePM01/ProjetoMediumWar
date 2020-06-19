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
	
	public Arrow(double scale, double[] position, Peca alvo){
		super(scale, position, alvo);
		setGUI(scale);
	}
	public void setGUI(double scale) {
		ImageIcon refimg=new ImageIcon("assets/arrow.png");
		imgArrow=refimg.getImage();
		imgArrow=imgArrow.getScaledInstance((int)(imgArrow.getWidth(null)*scale),(int)(imgArrow.getHeight(null)*scale),Image.SCALE_DEFAULT);
	}
}
