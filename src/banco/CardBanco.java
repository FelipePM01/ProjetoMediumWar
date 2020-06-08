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
    static private String[] refPeca = {"assets/archer0.png", "assets/knight0.png","assets/orc0.png"};
    
	public CardBanco(GUI gui, int i,Peca peca){
		super(gui, refImagens[i], peca);
    }
    
}
