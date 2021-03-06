package peca;

import java.awt.Image;

import card.ICardBanco;
import card.ICardJogadorPeca;
import tabuleiro.Tile;

public class Knight extends Melee{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5203249539608432216L;
	
	static private String[] refImagens= {"assets/knight0.png","assets/knight1.png","assets/knight2.png","assets/knight3.png","assets/knight4.png","assets/knight5.png"};
			
	public Knight(double scale) {
		super(scale);
		setup(4);
	}
	public Knight(IPeca peca,ICardBanco card) {
		super(peca,card);
		correction[0]=18;
		correction[1]=14;
	}
	public Knight(IPecaCardBanco peca,ICardJogadorPeca card) {
		super(peca,card);
		correction[0]=18;
		correction[1]=14;
	}
	public Knight(IPecaCardJogador peca,Tile tile){
		super(peca, tile);
		setup(2);	
		correction[0]=8;
		correction[1]=4;
		flipCorrection=10;
	}
	
	public String toString(){
		return "Knight ";
	}
	private void setup(int x) {
		animationFramesMove = new Image[3];
		animationFramesAttack = new Image[3];
		for(int i=0;i<6;i++){
			if(i<3)
				animationFramesMove[i]=adjustScale(refImagens[i],x);
			else
				animationFramesAttack[i-3]=adjustScale(refImagens[i],x);
		}
		currentFrame = 0;
		currentAnimation = animationFramesMove;
		speed=1;
		baseMoveAnimDuration=500;
		setLife(250);
		setEndurance(20);
		alcance=1;
		attackSpeed=1;
		attackDamage=12;
		baseAttackAnimDuration=1200;
		purchaseValue=2;
		saleValue=1;
		giftValue=1;
		maxLife=life;
	}
	public double[] getCenterPosition() {
		return getCenterPosition(11,19);
	}	
}
