package Jogador;

import peca.IPecaCard;
import peca.IPecaCardBanco;

public interface IJogadorBanco {
	void receber(IPecaCardBanco peca);
	public int getCash();
	public void addCash(int valor);
}
