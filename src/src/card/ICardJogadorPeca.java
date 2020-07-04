package card;

import Jogador.IJogadorCard;

public interface ICardJogadorPeca extends ICardPeca{
	public IJogadorCard getJogador();
	public void recompensar(int giftValue);
	public void setNaoColocado(boolean value);
}
