package peca;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import card.Card;
import game.GUI;
import game.Game;
import game.IGame;
import tabuleiro.Tile;

public class Knight extends Peca{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5203249539608432216L;
	static private String[] imagens= {"assets/knight0.png","assets/knight1.png","assets/knight2.png","assets/knight3.png","assets/knight4.png","assets/knight5.png"};
			
	public Knight(IGame game) {
		super(game);
		setup(4);
	}
	public Knight(Peca peca,Card card) {
		super(peca,card);
		correction[0]=18;
		correction[1]=14;
	}
	public void setup(int x) {
		animationFramesMove = new Image[3];
		animationFramesAttack = new Image[3];
		for(int i=0;i<6;i++){
			if(i<3)
				animationFramesMove[i]=adjustScale(imagens[i],x);
			else
				animationFramesAttack[i-3]=adjustScale(imagens[i],x);
		}
		currentFrame = 0;
		currentAnimation = animationFramesMove;
		speed=1;
		baseMoveAnimDuration=500;
	}
	public Knight(Peca peca,Tile tile){
		super(peca, tile);
		setup(2);	
		correction[0]=8;
		correction[1]=4;
		flipCorrection=10;
	}
}
