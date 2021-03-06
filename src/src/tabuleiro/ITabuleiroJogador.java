package tabuleiro;

import Jogador.Jogador;
import peca.IPecaCardJogador;

public interface ITabuleiroJogador {
	public boolean getCursor(String cor);
	public void hideCursor(String cor);
	public void positionPeca(Jogador jogador, IPecaCardJogador peca);
}
