package peca;

import Jogador.CardJogador;

public interface IPecaCardJogador extends IPeca {

	public CardJogador getCard();

	public void recompensar(int giftValue);

	
	
}
