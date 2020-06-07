package tabuleiro;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import game.GUI;

public class Tabuleiro extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 407576592100509664L;
	private Tile[][] matriz=new Tile[10][10];
	private int[] vet;
	private double scale;
	private Image tabuleiro;
	private int[] startPositionScreen= {280,120};
	public Tabuleiro(GUI gui) {
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				vet=new int[2];
				vet[0]=i;
				vet[1]=j;
				matriz[i][j]=new Tile(gui,vet);
			}
		}
		scale=gui.getScale();
		ImageIcon refTabuleiro=new ImageIcon("assets\\tabuleiro.png");
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
	}
}
