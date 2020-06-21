package peca;

import java.awt.Image;

import Jogador.CardJogador;
import banco.CardBanco;
import card.Card;
import card.ICardBanco;
import tabuleiro.Tile;

public class Orc extends Melee{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5644930813218984359L;
	static private String[] imagens= {"assets/orc0.png","assets/orc1.png","assets/orc2.png","assets/orc3.png","assets/orc4.png","assets/orc5.png"};
	
	public Orc(double scale) {
		super(scale);
		setup(4);
	}
	public Orc(IPeca peca,ICardBanco card) {
		super(peca,card);
		correction[0]=14;
		correction[1]=13;
	}	
	public Orc(IPecaCardBanco peca,CardJogador card) {
		super(peca,card);
		correction[0]=14;
		correction[1]=13;
	}	
	public Orc(IPecaCardJogador peca,Tile tile){
		super(peca, tile);
		setup(2);
		correction[0]=7;
		correction[1]=3;
		flipCorrection=7;
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
		speed=3;
		baseMoveAnimDuration=1000;
		setLife(200);
		setEndurance(7.5);
		alcance=1;
		attackDamage=15;
		attackSpeed=1.8;
		baseAttackAnimDuration=900;
		purchaseValue=4;
		saleValue=2;
		giftValue=3;
	}
	public double[] getCenterPosition() {
		return getCenterPosition(13,18);
	}
}
