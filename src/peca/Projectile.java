package peca;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public abstract class Projectile extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8344947528531167804L;
	private double[] translation={0.0,0.0};
	private double[] basePosition = {0,0};
	private double[] currentPosition;
	private double[] alvoPosition;
	private double scale;
	private double speed;
	private double dano;
	private int[] centerCorrection = new int[2];
	private Timer timer;
	private Peca alvo;
	
	AffineTransform identity = new AffineTransform();
	
	public Projectile(double scale, double[] position, Peca alvo) {
		this.scale=scale;
		this.basePosition=position;
		this.currentPosition=position;
		this.alvo=alvo;
		dano = 30;
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				track();
		    }
		};
		timer=new Timer(1, taskPerformer);
		timer.start();	
	}

	public void paintComponent(Graphics g, Image img) {
		//super.paintComponent(g);
		//g.drawImage(img, (int)(scale*positionX), (int)(scale*positionY), this);
		Graphics2D g2d = (Graphics2D)g;
		AffineTransform trans = new AffineTransform();
		trans.setTransform(identity);
		trans.rotate(getAngulo());
		//trans.translate((int)(scale*translation[0]), (int)(scale*translation[1]));
		trans.translate((int)(scale*currentPosition[0]), (int)(scale*currentPosition[1]));
		g2d.drawImage(img, trans, this);	
		//g2d.drawImage(img,(int)(scale*currentPosition[0]),(int)(scale*currentPosition[1]), this);
	}

	
	public void track() {
		alvoPosition=alvo.getCenterPosition();
		//Calcula translação 
		translation[0]=speed*(alvoPosition[0]-currentPosition[0])*(alvo.getTile().getImage().getWidth(null)/1000);
		translation[1]=speed*(alvoPosition[1]-currentPosition[1])*(alvo.getTile().getImage().getHeight(null)/1000);
		//Atualiza a currentPosition para a nova posicao
		currentPosition[0]+=translation[0];
		currentPosition[1]+=translation[1];		
		
		if(getDistancia()<(Math.sqrt(Math.pow(centerCorrection[0], 2)+Math.pow(centerCorrection[1],2)))) {
			alvo.receberDano(dano);
		}
	}
	public double getAngulo() {
		double cOposto = alvoPosition[0]-currentPosition[0];
		double cAdjascente = alvoPosition[1]-currentPosition[1];
		//double deg = Math.toDegrees(Math.atan(cOposto/cAdjascente)); 
		double deg = Math.atan(cOposto/cAdjascente); 
		return deg;
	}
	public double getDistancia() {
		double cOposto = alvoPosition[0]-currentPosition[0];
		double cAdjascente = alvoPosition[1]-currentPosition[1];
		return Math.sqrt(Math.pow(cOposto, 2)+Math.pow(cAdjascente,2));
	}
	public void setCenterCorrection(int x, int y) {
		centerCorrection[0]=x;
		centerCorrection[1]=y;
	}
	public void setDano(double dano) {
		this.dano=dano;
	}
}
