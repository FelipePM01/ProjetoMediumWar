package banco;

import card.Card;
import game.GUI;
import peca.Peca;

public class CardBanco extends Card{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3151322956375756610L;
	static private String[] refImagens = {"assets/cardPadrao.png","assets/cardAmbos.png","assets/cardAzul.png","assets/cardVermelho.png"};
    
	public CardBanco(GUI gui, int i,Peca peca){
		super(gui, refImagens[i], peca);
    }
    
}
