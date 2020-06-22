package peca;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BarraDeVida extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3602473704495200441L;
	
	private Image imgBorda;
	private Image imgConteudo;
	private int sizeConteudo;
	private double scale;
	private boolean setted=false;
	public BarraDeVida(int[] startPosition,double scale,String cor) {
		this.scale=scale;
		String path;
		if(cor=="vermelho")path="assets/vidaVermelho.png";
		else path="assets/vidaAzul.png";
		ImageIcon refImgBorda=new ImageIcon("assets/barraBorda.png");
		imgBorda=refImgBorda.getImage();
		imgBorda=imgBorda.getScaledInstance((int)(scale*imgBorda.getWidth(null)),(int)(scale*imgBorda.getHeight(null)),Image.SCALE_DEFAULT);
		ImageIcon refImgConteudo=new ImageIcon(path);
		imgConteudo=refImgConteudo.getImage();
		imgConteudo=imgConteudo.getScaledInstance((int)(scale*imgConteudo.getWidth(null)),(int)(scale*imgConteudo.getHeight(null)),Image.SCALE_DEFAULT);
	
		
	}
	public void paintComponent(Graphics g,int[] startPosition) {
		super.paintComponent(g);
		g.drawImage(imgBorda, (int)(startPosition[0]),(int) (startPosition[1]-3*scale), null);
		g.drawImage(imgConteudo,(int)( startPosition[0]+scale),(int)( startPosition[1]-2*scale), null);
		
	}
	public void atualizar(double perCent) {
		if(!setted) {
			sizeConteudo=imgConteudo.getWidth(null);
			setted=true;
		}
		if(perCent>0&&sizeConteudo>0&&imgConteudo.getHeight(null)>0)imgConteudo=imgConteudo.getScaledInstance((int)(perCent*sizeConteudo),imgConteudo.getHeight(null),Image.SCALE_DEFAULT);
	}
	
	
}
