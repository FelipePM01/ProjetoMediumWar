package Jogador;

import card.Card;
import card.ICardJogador;

public class CardJogador extends Card implements ICardJogador{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1679685899782569774L;
	
	static private String[] refImagens = {"assets/cardPadrao.png","assets/cardAzul.png","assets/cardVermelho.png"};
	
	public CardJogador(IJogadorCard jogador, int i){
		super(jogador, refImagens[0]);
	}	
}
