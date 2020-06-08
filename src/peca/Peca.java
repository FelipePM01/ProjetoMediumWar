package peca;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import card.Card;
import game.GUI;
import tabuleiro.Tile;

public abstract class Peca extends JPanel implements Cloneable{
	protected Image[] animationFramesMove;
	protected Image[] animationFramesAttack;
	protected Tile tile=null;
	protected Card card=null;
	protected boolean inBoard;
	protected int[] correction;
	protected Image[] currentAnimation;
	protected int currentFrame;
	protected GUI gui;
	protected double scale;
	protected double[] translation={0.0,0.0};
	protected int[] basePosition;
	
	
	public Peca(Peca peca,Tile tile) {
		set(peca);
		this.tile=tile;
		inBoard=true;
		basePosition=tile.getGUIPosition();
	}
	public Peca(Peca peca,Card card) {
		set(peca);
		this.card=card;
		this.gui=gui;
		inBoard=false;
		basePosition=card.getGUIPosition();
	}
	public void paintComponent(Graphics g, int positionX, int positionY) {//corrigir com correction , posicao inicial no tile e deslocamento em relacao a ela para movimento
		super.paintComponent(g);
		g.drawImage(currentAnimation[currentFrame], basePosition[0]+(int)(scale*correction[0])+(int)(translation[0]*scale), 0, this);
	}
	public void set(Peca peca) {//cria uma peca que eh uma copia de outra ja existente
		this.animationFramesAttack=peca.animationFramesAttack;
		this.animationFramesMove=peca.animationFramesMove;
		this.currentAnimation=peca.currentAnimation;
		this.currentFrame=peca.currentFrame;
		this.scale=peca.scale;
		
	}
	public Peca(GUI gui) {
		this.gui=gui;
		scale=gui.getScale();
	}
	
	public Image adjustScale(String refImg){
		
        ImageIcon refimg=new ImageIcon(refImg);
        Image img=refimg.getImage();
        //Escala de acordo com o card
        return img.getScaledInstance((int)(img.getWidth(null)*scale*4),(int)(img.getHeight(null)*scale*4),Image.SCALE_DEFAULT);
    }
	public int applyScale(int x) {
		return (int)(x*scale); 
	}
	
	public abstract void paintCard(Graphics g,int positionX,int positionY);
	public abstract void paintTile(Graphics g,int positionX,int positionY);
	
}
