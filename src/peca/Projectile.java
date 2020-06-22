package peca;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.geom.AffineTransform;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import tabuleiro.Tabuleiro;

public abstract class Projectile extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8344947528531167804L;
	protected Image img;
	private double[] translation={0.0,0.0};
	private double[] basePosition = {0,0};
	private double[] currentPosition;
	private double[] alvoPosition;
	private double scale;
	private double speed;
	private double dano;
	private int[] centerCorrection = new int[2];
	private Timer timer;
	private IPecaTile alvo;
	private int[] imgEdge = new int[2];
	private Tabuleiro tabuleiro;
	private double angulo=0;
	private IPecaCardJogador origem;
	
	public Projectile(double scale, double[] position, IPecaTile target, double dano,Tabuleiro tabuleiro, double speed,IPecaCardJogador origem) {
		this.scale=scale;
		this.origem=origem;
		this.basePosition=position;
		this.currentPosition=position;
		this.alvo=target;
		this.dano=dano;
		this.tabuleiro=tabuleiro;
		this.alvoPosition=alvo.getCenterPosition();
	    this.speed=speed;
		setVisible(true);
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				track();
		    }
		};
		timer=new Timer(100, taskPerformer);
		timer.start();	
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2d =(Graphics2D) g.create();
		g2d.translate((int)(currentPosition[0]),(int)(currentPosition[1]));
		g2d.rotate(angulo);  
		g2d.drawImage(img,0,0,null);
	}
	
	public void track() {
		alvoPosition=alvo.getCenterPosition();
		
		if(alvoPosition[0]-currentPosition[0]<0&&alvoPosition[1]-currentPosition[1]<0) {
			
			translation[0]=-Math.abs(((alvoPosition[0]-currentPosition[0])/(alvoPosition[1]-currentPosition[1])));
			translation[1]=-Math.abs(((alvoPosition[1]-currentPosition[1])/(alvoPosition[0]-currentPosition[0])));
			angulo=Math.atan2(translation[1], translation[0]);
		}
		
		else if(alvoPosition[0]-currentPosition[0]<0&&alvoPosition[1]-currentPosition[1]>0){
			
			translation[0]=-Math.abs(((alvoPosition[0]-currentPosition[0])/(alvoPosition[1]-currentPosition[1])));
			translation[1]=Math.abs(((alvoPosition[1]-currentPosition[1])/(alvoPosition[0]-currentPosition[0])));
			angulo=Math.atan2(translation[1], translation[0]);
		}
		
		else if(alvoPosition[0]-currentPosition[0]>0&&alvoPosition[1]-currentPosition[1]<0) {
			
			translation[0]=Math.abs(((alvoPosition[0]-currentPosition[0])/(alvoPosition[1]-currentPosition[1])));
			translation[1]=-Math.abs(((alvoPosition[1]-currentPosition[1])/(alvoPosition[0]-currentPosition[0])));
			angulo=Math.atan2(translation[1], translation[0]);
		}
		else if(alvoPosition[0]-currentPosition[0]>0&&alvoPosition[0]-currentPosition[0]>0) {
			
			translation[0]=Math.abs(((alvoPosition[0]-currentPosition[0])/(alvoPosition[1]-currentPosition[1])));
			translation[1]=Math.abs(((alvoPosition[1]-currentPosition[1])/(alvoPosition[0]-currentPosition[0])));
			angulo=Math.atan2(translation[1], translation[0]);
		}
		else if(alvoPosition[0]-currentPosition[0]==0) {
			if(alvoPosition[1]-currentPosition[1]>0) {
				translation[1]=1;
			}
			else translation[1]=-1;
			translation[0]=0;
			angulo=Math.atan2(translation[1], translation[0]);
		}
		else if(alvoPosition[1]-currentPosition[1]==0) {
			if(alvoPosition[0]-currentPosition[0]>0) {
				translation[0]=speed;
			}
			else translation[0]=-speed;
			translation[1]=0;
			angulo=Math.atan2(translation[1], translation[0]);
		}
		double dist=Math.sqrt(Math.pow(translation[0],2)+Math.pow(translation[1], 2));
		translation[0]*=speed/dist;
		translation[1]*=speed/dist;
		//Atualiza a currentPosition para a nova posicao
		currentPosition[0]+=translation[0];
		currentPosition[1]+=translation[1];

		if(dano!=0&&Math.abs(currentPosition[0]-alvoPosition[0])<(scale*imgEdge[0])&&Math.abs(currentPosition[1]-alvoPosition[1])<(scale*imgEdge[1])) {
			alvo.receberDanoRanged(dano,this);
			dano=0;
			tabuleiro.removeProjectiles(this);
		}
	}
	
	public double getDistancia() {
		double cOposto = alvoPosition[0]-currentPosition[0];
		double cAdjascente = alvoPosition[1]-currentPosition[1];
		return Math.sqrt(Math.pow(cOposto, 2)+Math.pow(cAdjascente,2));
	}
	public void setGUI(double scale, String imgReferencia) {
		ImageIcon refimg=new ImageIcon(imgReferencia);
		Image auxImg=refimg.getImage();
		img=auxImg.getScaledInstance((int)(2*scale*auxImg.getWidth(null)),(int)(2*scale*auxImg.getHeight(null)), Image.SCALE_DEFAULT);//.getScaledInstance((int)(2*scale*auxImg.getWidth(null)),(int)(2*scale*auxImg.getHeight(null)),Image.SCALE_DEFAULT);
	
	}
	public void setCenterCorrection(int x, int y) {
		centerCorrection[0]=x;
		centerCorrection[1]=y;
	}
	public void setDano(double dano) {
		this.dano=dano;
	}
	public void setImgEdge(int[] imgEdge) {
		this.imgEdge=imgEdge;	
	}
	public void recompensar(int value) {
		origem.recompensar(value);
	}
}
