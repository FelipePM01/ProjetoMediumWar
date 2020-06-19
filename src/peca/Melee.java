package peca;

import Jogador.CardJogador;
import banco.CardBanco;
import card.ICardBanco;
import tabuleiro.Tile;

public class Melee extends Peca {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7809680172894000572L;


	public Melee(double scale) {
		super(scale);
		// TODO Auto-generated constructor stub
	}

	public Melee(IPeca peca,ICardBanco card) {
		super(peca,card);
		
	}
	public Melee(IPecaCardBanco peca,CardJogador card) {
		super(peca,card);
		
	}
	public Melee(IPecaCardJogador peca,Tile tile){
		super(peca, tile);
		
	}

	
	protected void attack() {
		frameCounter+=1;
		if (frameCounter>=(double)baseAttackAnimDuration/attackSpeed/animationFramesAttack.length) {
			
			if(currentFrame==animationFramesAttack.length-1) {
				currentFrame=0;
				if(attackTarget!=null) {
					attackTarget.receberDano(attackDamage);
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

}
