package peca;

import Jogador.CardJogador;
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
		projectileCorrection=new int[2];
	}
	

	
	protected void attack() {
		frameCounter+=1;
		if (frameCounter>=(double)baseAttackAnimDuration/attackSpeed/animationFramesAttack.length) {
			if(attackTarget==null||attackTarget.getTile()==null||attackTarget.getTile().getPeca()!=attackTarget)moveOrAttack();
			if(currentFrame==animationFramesAttack.length-1) {
				currentFrame=0;
				if(attackTarget!=null) {
					fire();
					if(Tile.dist(getTile(),attackTarget.getTile())>alcance||attackTarget.getMorto()) {
						currentAction=null;
						moveOrAttack();
					}
				}
				else {
					moveOrAttack();
				}
				
			}
			else currentFrame++;
			
			frameCounter=0;
		}

	}
	protected void fire() {
		if(!flipped) {
			double[] position= {basePosition[0]+(scale*correction[0])+(translation[0])+scale*projectileCorrection[0], basePosition[1]+(scale*correction[1])+(translation[1])+scale*projectileCorrection[1]};
			if(!morto)tabuleiro.addProjectiles(create(scale,position,attackTarget,attackDamage,origem));
		}
		else {
			double[] position= {basePosition[0]+(scale*correction[0])+(translation[0])+scale*projectileCorrection[0], basePosition[1]+(scale*correction[1])+(translation[1])+scale*projectileCorrection[1]};
			if(!morto)tabuleiro.addProjectiles(create(scale,position,attackTarget,attackDamage,origem));
		}		
	}
	protected abstract Projectile create(double scale,double[] posicao,IPecaTile attackTarget,double dano,IPecaCardJogador origem);

}
