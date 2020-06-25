package peca;

import Jogador.CardJogador;

public interface IPecaCardJogador extends IPeca {

	public ICardJogadorPeca getCard();

	public void recompensar(int giftValue);

	
	
}
