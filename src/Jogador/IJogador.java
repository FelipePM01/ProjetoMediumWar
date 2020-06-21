package Jogador;

public interface IJogador extends IJogadorCard, IJogadorGame,IJogadorBanco{
	public void addPoint();
	public int getPoints();
	
	public void pressedA() ;
	public void pressedC() ;
	public void pressedVIRGULA();
	public void pressedD() ;
	public void pressedLEFT();
	public void pressedRIGHT();
	public void pressedZ() ;
	public void pressedX() ;
	public void pressedPONTO() ;
	public void pressedBARRA() ;
	public void pressedSPACE() ;
	public void pressedENTER() ;
	

}
