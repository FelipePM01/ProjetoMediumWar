package peca;

import Jogador.CardJogador;
import banco.CardBanco;
import card.ICardBanco;
import tabuleiro.Tile;

public class Ranged extends Peca {

	private static final long serialVersionUID = 4784160407718901744L;



	public Ranged(double scale) {
		super(scale);
		// TODO Auto-generated constructor stub
	}

	public Ranged(IPeca peca,CardBanco card) {
		super(peca,card);
		
	}
	public Ranged(IPecaCardBanco peca,CardJogador card) {
		super(peca,card);
		
	}
	public Ranged(IPecaCardJogador peca,Tile tile){
		super(peca, tile);
		
	}

	
	protected void attack() {
		;

	}

}
