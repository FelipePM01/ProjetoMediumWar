package peca;

import Jogador.CardJogador;

public interface IPecaCardJogador extends IPecaCard {

	public ICardJogadorPeca getCard();

	public void recompensar(int giftValue);

	
	
}
