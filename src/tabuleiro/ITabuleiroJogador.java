package tabuleiro;

import Jogador.Jogador;
import peca.IPecaCard;
import peca.Peca;

public interface ITabuleiroJogador {

	public boolean getCursor(String cor);

	public void hideCursor(String cor);

	public void positionPeca(Jogador jogador, IPecaCard peca);

}