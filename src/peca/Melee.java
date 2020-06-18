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

	public Melee(IPecaCardBanco peca,ICardBanco card) {
		super(peca,card);
		
	}
	public Melee(IPecaCardJogador peca,CardJogador card) {
		super(peca,card);
		
	}
	public Melee(IPecaCard peca,Tile tile){
		super(peca, tile);
		
	}

	
	protected void attack() {
		frameCounter+=1;
		if (frameCounter>=(double)baseMoveAnimDuration/speed/animationFramesMove.length) {
			
			if(currentFrame==animationFramesMove.length-1)currentFrame=0;
			else currentFrame++;
			
			frameCounter=0;
		}

	}

}
