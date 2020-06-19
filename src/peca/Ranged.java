package peca;

import Jogador.CardJogador;
import banco.CardBanco;
import card.ICardBanco;
import tabuleiro.Tile;

public abstract class Ranged extends Peca {

	private static final long serialVersionUID = 4784160407718901744L;

	protected int[] projectileCorrection;

	public Ranged(double scale) {
		super(scale);
		// TODO Auto-generated constructor stub
	}

	public Ranged(IPeca peca,ICardBanco card) {
		super(peca,card);
		
	}
	public Ranged(IPecaCardBanco peca,CardJogador card) {
		super(peca,card);
		
	}
	public Ranged(IPecaCardJogador peca,Tile tile){
		super(peca, tile);
		
	}
	

	
	protected void attack() {
		frameCounter+=1;
		if (frameCounter>=(double)baseAttackAnimDuration/attackSpeed/animationFramesAttack.length) {
			
			if(currentFrame==animationFramesAttack.length-1) {
				currentFrame=0;
				if(attackTarget!=null) {
					fire();
					if(Tile.dist(getTile(),attackTarget.getTile())>alcance) {
						currentAction=null;
						moveOrAttack();
					}
				}
				
			}
			else currentFrame++;
			
			frameCounter=0;
		}

	}
	protected void fire() {
		
		if(!flipped) {
			double[] position= {basePosition[0]+(scale*correction[0])+(translation[0])+scale*projectileCorrection[0], basePosition[1]+(scale*correction[1])+(translation[1])+scale*projectileCorrection[1]};
			tabuleiro.add(create(scale,position,attackTarget,attackDamage));
		}
		else {
			double[] position= {basePosition[0]+(scale*correction[0])+(translation[0])+scale*projectileCorrection[0], basePosition[1]+(scale*correction[1])+(translation[1])+scale*projectileCorrection[1]};
			tabuleiro.add(create(scale,position,attackTarget,attackDamage));
		}
		
	}
	protected abstract Projectile create(double scale,double[] posicao,Peca target,double dano);

}
