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
	private int[] cursorAzul;
	private int[] cursorVermelho;
	
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
		//setCursor("azul");
		//setCursor("vermelho");
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
	public void setCursor(String cor) {
		if (cor=="azul") {
			cursorAzul=new int[2];
			cursorAzul[0]=0;
			cursorAzul[1]=0;
			selectTile(0,0,0,0,"azul");
		}
		else if(cor=="vermelho") {
			cursorVermelho=new int[2];
			cursorVermelho[0]=9;
			cursorVermelho[1]=9;
			selectTile(9,9,9,9,"vermelho");
		}
	}
	public void hideCursor(String cor) {
		if (cor=="azul") {
			selectTile(cursorAzul[0],cursorAzul[1],0,0,"padrao");
			cursorAzul[0]=0;
			cursorAzul[1]=0;
			
		}
		else if(cor=="vermelho") {
			selectTile(cursorVermelho[0],cursorVermelho[1],9,9,"padrao");
			cursorVermelho[0]=9;
			cursorVermelho[1]=9;
		}
	}
	public void selectTile(int xAnt, int yAnt, int x, int y, String cor) {
		if(matriz[xAnt][yAnt]!=null)matriz[xAnt][yAnt].setTileAtual("padrao");
		if(matriz[x][y]!=null)matriz[x][y].setTileAtual(cor);
	}
	public void pressedW() {
		if(cursorAzul[1]!=0) {
			selectTile(cursorAzul[0], cursorAzul[1], cursorAzul[0], cursorAzul[1]-1, "azul");
			cursorAzul[1]--;
		}
	}
	public void pressedS() {
		if(cursorAzul[1]!=2) {
			selectTile(cursorAzul[0], cursorAzul[1], cursorAzul[0], cursorAzul[1]+1, "azul");
			cursorAzul[1]++;
		}
	}
	public void pressedA() {
		if(cursorAzul[0]!=0) {
			selectTile(cursorAzul[0], cursorAzul[1], cursorAzul[0]-1, cursorAzul[1],"azul");
			cursorAzul[0]--;
		}
	}
	public void pressedD() {
		if(cursorAzul[0]!=9) {
			selectTile(cursorAzul[0], cursorAzul[1], cursorAzul[0]+1, cursorAzul[1], "azul");
			cursorAzul[0]++;
		 }
	}
	public void pressedUP() {
		if(cursorVermelho[1]!=7) {
			selectTile(cursorVermelho[0], cursorVermelho[1], cursorVermelho[0], cursorVermelho[1]-1, "vermelho");
			cursorVermelho[1]--;
		}
	}
	public void pressedDOWN() {
		if(cursorVermelho[1]!=9) {
			selectTile(cursorVermelho[0], cursorVermelho[1], cursorVermelho[0], cursorVermelho[1]+1, "vermelho");
			cursorVermelho[1]++;
		}
	}
	public void pressedLEFT() {
		if(cursorVermelho[0]!=0) {
			selectTile(cursorVermelho[0], cursorVermelho[1], cursorVermelho[0]-1, cursorVermelho[1],"vermelho");
			cursorVermelho[0]--;
		}
	}
	public void pressedRIGHT() {
		if(cursorVermelho[0]!=9) {
			selectTile(cursorVermelho[0], cursorVermelho[1], cursorVermelho[0]+1, cursorVermelho[1], "vermelho");
			cursorVermelho[0]++;
		 }
	}
}
