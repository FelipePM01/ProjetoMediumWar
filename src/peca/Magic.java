package peca;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Magic extends Projectile{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3312309211347542268L;
	private Image imgMagic;
	public Magic(double scale, double[] position, IPecaTile target, double dano){
		super(scale, position, target, dano);
		setCenterCorrection(7,4);
		setGUI(scale);
		int[] edge = {5,3};
		setImgEdge(edge);	
	}
	
	//ponto central magia nates da escala 7x4,margem x é 5 e margem y é 3
	public void setGUI(double scale){
		ImageIcon refimg=new ImageIcon("assets/magic.png");
		imgMagic=refimg.getImage();
		imgMagic=imgMagic.getScaledInstance((int)(imgMagic.getWidth(null)*scale),(int)(imgMagic.getHeight(null)*scale),Image.SCALE_DEFAULT);
	}
}
