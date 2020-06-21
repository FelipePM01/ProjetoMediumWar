package Jogador;

import peca.IPecaCard;

public interface IJogadorBanco {
	void receber(IPecaCard peca);
	public int getCash();
	public void addCash(int valor);
}
