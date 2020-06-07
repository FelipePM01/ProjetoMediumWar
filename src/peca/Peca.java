package peca;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import card.Card;
import tabuleiro.Tile;

public abstract class Peca extends JPanel{
	private Image[] animationFramesMove;
	private Image[] animationFramesAttack;
	private Tile tile=null;
	private Card card=null;
	private boolean inBoard;
	private int[] correction;
	private Image[] currentAnimation;
	private int currentFrame;
	public Peca(Tile tile) {
		this.tile=tile;
		inBoard=true;
	}
	public Peca(Card card) {
		this.card=card;
		inBoard=false;
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(currentAnimation[currentFrame], 0, 0, this);
	}
	
	
}
