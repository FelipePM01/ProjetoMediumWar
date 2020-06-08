package peca;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import game.GUI;
import tabuleiro.Tile;

public class Archer extends Peca{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6369254339259024631L;
	static private String[] imagens= {"assets/archer0.png","assets/archer1.png","assets/archer2.png","assets/archer3.png","assets/archer4.png","assets/archer5.png","assets/archer6.png","assets/archer7.png","assets/archer8.png"};
	
	public Archer(GUI gui){
		super(gui);
		setup();
	}
	public void setup() {
		animationFramesMove = new Image[3];
		animationFramesAttack = new Image[6];
		for(int i=0;i<9;i++){
			if(i<3)
				animationFramesMove[i]=adjustScale(imagens[i]);
			else
				animationFramesAttack[i-3]=adjustScale(imagens[i]);
		}
		currentFrame = 0;
		currentAnimation = animationFramesMove;
	}
	
	public void paintCard(Graphics g,int positionX,int positionY) {
		paintComponent(g,positionX+applyScale(12),positionY+applyScale(14));
	}
	public void paintTile(Graphics g,int positionX,int positionY) {
		paintComponent(g,positionX+applyScale(6),positionY+applyScale(4));
	}
	
}
