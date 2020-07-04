package peca;

import java.awt.Image;

import Jogador.CardJogador;
import card.ICardBanco;
import card.ICardJogadorPeca;
import tabuleiro.Tile;

public class Wizard extends Ranged {
/**
	 * 
	 */
	private static final long serialVersionUID = -8920165694292630391L;
	private static String[] refImagens= {"assets/wizard0.png","assets/wizard1.png","assets/wizard2.png","assets/wizard3.png","assets/wizard4.png","assets/wizard5.png","assets/wizard6.png"};
	
	public Wizard(double scale){
		super(scale);
		setup(4);	
	}
	public Wizard(IPeca peca,ICardBanco card) {
		super(peca,card);
		correction[0]=24;
		correction[1]=14;
	}
	public Wizard(IPecaCardBanco peca,ICardJogadorPeca card) {
		super(peca,card);
		correction[0]=24;
		correction[1]=13;
	}
	public Wizard(IPecaCardJogador peca,Tile tile){
		super(peca, tile);
		setup(2);	
		correction[0]=11;
		correction[1]=4;
		flipCorrection=15;
		projectileCorrection[0]=18;
		projectileCorrection[1]=14;
	}
	public String toString(){
		return "Wizard ";
	}
	
	private void setup(int x) {
		animationFramesMove = new Image[3];
		animationFramesAttack = new Image[4];
		for(int i=0;i<7;i++){
			if(i<3)
				animationFramesMove[i]=adjustScale(refImagens[i],x);
			else
				animationFramesAttack[i-3]=adjustScale(refImagens[i],x);
		}
		currentFrame = 0;
		currentAnimation = animationFramesMove;
		speed=1.5;
		baseMoveAnimDuration=1000;
		setLife(170);
		setEndurance(5);
		alcance=8;
		attackSpeed=0.5;
		baseAttackAnimDuration=1000;
		attackDamage=50;
		purchaseValue=5;
		saleValue=2;
		giftValue=3;
		maxLife=life;
	}	
	public double[] getCenterPosition() {
		return getCenterPosition(7,20);
	}
	public Projectile create(double scale,double[] posicao,IPecaTile target,double dano,IPecaCardJogador origem) {
		return new Magic(scale,posicao,target,dano,origem);
	}
	
}
