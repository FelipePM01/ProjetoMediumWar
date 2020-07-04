package peca;

public class Magic extends Projectile{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3312309211347542268L;
	
	public Magic(double scale, double[] position, IPecaTile target, double dano, IPecaCardJogador origem){
		super(scale, position, target, dano,12,origem);
		setCenterCorrection(7,4);
		setGUI(scale,"assets/magic.png");
		int[] edge = {5,3};
		setImgEdge(edge);	
	}
}
