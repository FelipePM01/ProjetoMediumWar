package banco;

import Jogador.IJogador;
import Jogador.IJogadorBanco;

public interface IBancoJogador {

	public void comprar(IJogador jogador);

	public int getCursor(int i);

}
