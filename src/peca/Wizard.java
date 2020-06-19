package peca;

import java.awt.Image;

import Jogador.CardJogador;
import card.ICardBanco;
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
		correction[0]=12;
		correction[1]=14;
	}
	public Wizard(IPecaCardBanco peca,CardJogador card) {
		super(peca,card);
		correction[0]=24;
		correction[1]=13;
	}
	public Wizard(IPecaCardJogador peca,Tile tile){
		super(peca, tile);
		setup(2);	
		correction[0]=11;
		correction[1]=4;
		flipCorrection=9;
		projectileCorrection[0]=18;
		projectileCorrection[1]=14;
	}
	
	
	public void setup(int x) {
		animationFramesMove = new Image[3];
		animationFramesAttack = new Image[6];
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
	}	
	public Projectile create(double scale,double[] posicao,Peca target,double dano) {
		return new Magic(scale,posicao,target,dano);
	}
	public double[] getCenterPosition() {
		return getCenterPosition(7,20);
	}
}
