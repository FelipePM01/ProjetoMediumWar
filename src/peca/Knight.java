package peca;

import java.awt.Image;

import javax.swing.ImageIcon;

import game.GUI;
import tabuleiro.Tile;

public class Knight extends Peca{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5203249539608432216L;
	static private String[] imagens= {"assets/knight0.png","assets/knight1.png","assets/knight2.png","assets/knight3.png","assets/knight4.png","assets/knight5.png"};
			
	public Knight(GUI gui) {
		super(gui);
		setup();
	}
	public void setup() {
		animationFramesMove = new Image[3];
		animationFramesAttack = new Image[3];
		for(int i=0;i<6;i++){
			if(i<3)
				animationFramesMove[i]=adjustScale(imagens[i]);
			else
				animationFramesAttack[i-3]=adjustScale(imagens[i]);
		}
		currentFrame = 0;
		currentAnimation = animationFramesMove;
	}
}
