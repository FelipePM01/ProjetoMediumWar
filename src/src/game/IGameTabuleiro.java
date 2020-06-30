package game;

public interface IGameTabuleiro extends IGame{
	public void newRound(String cor);
	public void endGame(String cor);
}
