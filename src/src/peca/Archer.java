package peca;

import java.awt.Image;

import card.ICardBanco;
import card.ICardJogadorPeca;
import tabuleiro.Tile;

public class Archer extends Ranged{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6369254339259024631L;
	private static String[] refImagens= {"assets/archer0.png","assets/archer1.png","assets/archer2.png","assets/archer3.png","assets/archer4.png","assets/archer5.png","assets/archer6.png","assets/archer7.png","assets/archer8.png"};
	
	public Archer(double scale){
		super(scale);
		setup(4);	
	}
	public Archer(IPeca peca,ICardBanco card) {
		super(peca,card);
		correction[0]=12;
		correction[1]=14;
	}
	public Archer(IPecaCardBanco peca,ICardJogadorPeca card) {
		super(peca,card);
		correction[0]=12;
		correction[1]=14;
	}
	public Archer(IPecaCardJogador peca,Tile tile){
		super(peca, tile);
		setup(2);	
		correction[0]=6;
		correction[1]=4;
		flipCorrection=6;
		projectileCorrection[0]=22;
		projectileCorrection[1]=16;
	}
	
	public String toString(){
		return "Archer ";
	}
	private void setup(int x) {
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
		speed=2.0;
		baseMoveAnimDuration=1000;
		setLife(150);
		setEndurance(5);
		alcance=6;
		attackSpeed=2;
		baseAttackAnimDuration=1000;
		attackDamage=10;
		purchaseValue=3;
		saleValue=1;
		giftValue=2;
		maxLife=life;
	}	
	public double[] getCenterPosition() {
		return getCenterPosition(12,19);
	}
	public Projectile create(double scale,double[] posicao,IPecaTile target,double dano,IPecaCardJogador origem) {
		return new Arrow(scale,posicao,target,dano,origem);
	}
}
