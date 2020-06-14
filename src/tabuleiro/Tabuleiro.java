package tabuleiro;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import game.IGame;

public class Tabuleiro extends JPanel implements ITabuleiro{
	/**
	 * 
	 */
	private static final long serialVersionUID = 407576592100509664L;
	public ITileTabuleiro[][] matriz=new Tile[10][10];
	private int[] vet;
	private double scale=1;
	private Image tabuleiro;
	private int[] startPositionScreen= {280,120};
	
	public Tabuleiro(IGame game) {
		scale=game.getScale();
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				vet=new int[2];
				vet[0]=i;
				vet[1]=j;
				matriz[i][j]=new Tile(this,vet);
			}
		}
		ImageIcon refTabuleiro=new ImageIcon("assets/tabuleiro.png");
		tabuleiro=refTabuleiro.getImage();
		tabuleiro=tabuleiro.getScaledInstance((int)(tabuleiro.getWidth(null)*scale),(int) (tabuleiro.getHeight(null)*scale), Image.SCALE_DEFAULT);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(tabuleiro, (int)(scale*(startPositionScreen[0])), (int)(scale*(startPositionScreen[1])), this);
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				if(matriz[i][j]!=null) {
					Image img=matriz[i][j].getImage();
					matriz[i][j].paintComponent(g,img);
				}
			}
		}
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				if(matriz[i][j]!=null) {
					matriz[i][j].paintPeca(g);
				}
			}
		}
	}
	public void start() {
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				matriz[i][j].actionPeca();
			}
		}
	}
	public void clear() {
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				if(matriz[i][j].existsPeca())matriz[i][j].nullTarget();
				if(matriz[i][j].existsPeca())matriz[i][j].setPeca(null);
			}
		}
	}
	public Tile[][] getTiles() {
		return (Tile[][])matriz;
	}
	public double getScale() {
		return scale;
	}
}
