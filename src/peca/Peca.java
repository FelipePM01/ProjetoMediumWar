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
	}
	public Peca(Peca peca,Card card) {
		set(peca);
		this.card=card;
		inBoard=false;
	}
	public void paintComponent(Graphics g) {//corrigir com correction , posicao inicial no tile e deslocamento em relacao a ela para movimento
		super.paintComponent(g);
		
		if(tile!=null)basePosition=tile.getGUIPosition();
		else basePosition=card.getGUIPosition();
		g.drawImage(currentAnimation[currentFrame], base_position[0]+(int)(scale*correction[0])+(int)(translation[0]*scale), 0, this);
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
        return img.getScaledInstance((int)(img.getWidth(null)*scale),(int)(img.getHeight(null)*scale),Image.SCALE_DEFAULT);
    }
	
}
