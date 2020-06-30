package peca;

import Jogador.CardJogador;
import card.ICardJogadorPeca;

public interface IPecaCardJogador extends IPecaCard {

	public ICardJogadorPeca getCard();

	public void recompensar(int giftValue);

	
	
}
