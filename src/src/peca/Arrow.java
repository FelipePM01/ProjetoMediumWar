package peca;

public class Arrow extends Projectile{
	/**
	 * 
	 */
	private static final long serialVersionUID = -70153469698118573L;
	
	public Arrow(double scale, double[] position, IPecaTile target, double dano,IPecaCardJogador origem){
		super(scale, position, target, dano,20,origem);
		setCenterCorrection(4,2);
		setGUI(scale, "assets/arrow.png");
		int[] edge = {5,3};
		setImgEdge(edge);	
	}
}
