package Jogador;

import peca.IPecaCardBanco;

public interface IJogadorBanco {
	public void receber(IPecaCardBanco peca);
	public int getCash();
	public void addCash(int valor);
}
