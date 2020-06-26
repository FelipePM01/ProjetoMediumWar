package banco;

import Jogador.IJogador;
import Jogador.IJogadorBanco;

public interface IBancoJogador {
	public void comprar(IJogador jogador);
	public void hideCursor(int i);
	public int obtainCursor(String cor);
}
