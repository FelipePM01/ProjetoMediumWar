package peca;

import Jogador.IJogadorCard;

public interface ICardJogadorPeca {

	public int[] getGUIPosition();

	public IJogadorCard getJogador();

	public void recompensar(int giftValue);

	public void setNaoColocado(boolean value);
	
}
