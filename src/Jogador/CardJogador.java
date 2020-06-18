package Jogador;

import card.Card;
import card.ICardJogador;
import peca.Archer;
import peca.IPecaCard;
import peca.IPecaCardJogador;
import peca.Knight;
import peca.Orc;
import peca.Peca;

public class CardJogador extends Card implements ICardJogador{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1679685899782569774L;
	
	static private String[] refImagens = {"assets/cardPadrao.png","assets/cardAzul.png","assets/cardVermelho.png"};
	
	public CardJogador(IJogadorCard jogador, int i){
		super(jogador, refImagens[0]);
	}

	@Override
	public void setPeca(IPecaCard peca) {
		if (peca instanceof Archer)this.peca=new Archer(peca,this);
		else if (peca instanceof Knight)this.peca=new Knight(peca,this);
		else if (peca instanceof Orc)this.peca=new Orc(peca,this);
		
	}

	

	
}
