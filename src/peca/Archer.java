package peca;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import card.Card;
import game.GUI;
import tabuleiro.Tile;

public class Archer extends Peca{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6369254339259024631L;
	private static String[] refImagens= {"assets/archer0.png","assets/archer1.png","assets/archer2.png","assets/archer3.png","assets/archer4.png","assets/archer5.png","assets/archer6.png","assets/archer7.png","assets/archer8.png"};
	
	public Archer(GUI gui){
		super(gui);
		setup(4);
	}
	public Archer(Peca peca,Card card) {
		super(peca,card);
	}
	public void setup(int x) {
		animationFramesMove = new Image[3];
		animationFramesAttack = new Image[6];
		for(int i=0;i<9;i++){
			if(i<3)
				animationFramesMove[i]=adjustScale(refImagens[i],x);
			else
				animationFramesAttack[i-3]=adjustScale(refImagens[i],x);
		}
		currentFrame = 0;
		currentAnimation = animationFramesMove;
	}
	
	public Archer(Peca peca,Tile tile){
		super(peca, tile);
		setup(2);	
	}
	
	/*public void paintCard(Graphics g,int positionX,int positionY) {
		paintComponent(g,positionX+applyScale(12),positionY+applyScale(14));
	}
	public void paintTile(Graphics g,int positionX,int positionY) {
		paintComponent(g,positionX+applyScale(6),positionY+applyScale(4));
	}*/
	
}
