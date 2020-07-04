package peca;

import card.ICardJogadorPeca;

public interface IPecaCardJogador extends IPecaCard {
	public ICardJogadorPeca getCard();
	public void recompensar(int giftValue);	
}
