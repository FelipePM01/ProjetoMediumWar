package tabuleiro;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Jogador.IJogador;
import Jogador.Jogador;
import game.Game;
import game.IGame;
import peca.IPecaCard;
import peca.Peca;
import peca.Projectile;

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
	private IJogador jogador1=null, jogador2=null;
	private int[] cursorVermelho;
	private boolean cAzul = false;
	private boolean cVermelho = false;
	private IPecaCard azulPeca;
	private IPecaCard vermelhoPeca;
	private Game game;
	boolean start1 = false;
	boolean start2 = false;
	private int[] inTab = {0,0};
	protected ArrayList<Projectile> projectiles=new ArrayList<Projectile>();
	
	public Tabuleiro(Game game) {
		scale=game.getScale();
		this.game=game;
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
		for(int i=0;i<projectiles.size();i++) {
			projectiles.get(i).paintComponent(g);
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
				if(matriz[i][j].existsPeca()&&matriz[i][j].getPeca().getOrigem().getCard()!=null)matriz[i][j].getPeca().getOrigem().getCard().setNaoColocado(true);
				if(matriz[i][j].existsPeca()&&matriz[i][j].getPeca().getOrigem().getCard()!=null)matriz[i][j].getPeca().setInBoard(false);
				if(matriz[i][j].existsPeca())matriz[i][j].nullTarget();
				if(matriz[i][j].existsPeca())matriz[i][j].setNull();
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
			cAzul=true;
			cursorAzul[0]=0;
			cursorAzul[1]=0;
			selectTile(0,0,0,0,"azul");
		}
		else if(cor=="vermelho") {
			cursorVermelho=new int[2];
			cVermelho=true;
			cursorVermelho[0]=9;
			cursorVermelho[1]=9;
			selectTile(9,9,9,9,"vermelho");
		}
	}
	public boolean getCursor(String cor) {
		if(cor=="azul")return cAzul;
		return cVermelho;
	}
	public void hideCursor(String cor) {
		if (cor=="azul") {
			selectTile(cursorAzul[0],cursorAzul[1],0,0,"padrao");
			cAzul=false;
			cursorAzul[0]=0;
			cursorAzul[1]=0;
			
		}
		else if(cor=="vermelho") {
			selectTile(cursorVermelho[0],cursorVermelho[1],9,9,"padrao");
			cVermelho=false;
			cursorVermelho[0]=9;
			cursorVermelho[1]=9;
		}
	}
	public void selectTile(int xAnt, int yAnt, int x, int y, String cor) {
		if(matriz[xAnt][yAnt]!=null)matriz[xAnt][yAnt].setTileAtual("padrao");
		if(matriz[x][y]!=null)matriz[x][y].setTileAtual(cor);
	}
	public void pressedW() {
		if(cAzul&&cursorAzul[1]!=0) {
			selectTile(cursorAzul[0], cursorAzul[1], cursorAzul[0], cursorAzul[1]-1, "azul");
			cursorAzul[1]--;
		}
	}
	public void pressedS() {
		if(cAzul&&cursorAzul[1]!=9) {
			selectTile(cursorAzul[0], cursorAzul[1], cursorAzul[0], cursorAzul[1]+1, "azul");
			cursorAzul[1]++;
		}
	}
	public void pressedA() {
		if(cAzul&&cursorAzul[0]!=0) {
			selectTile(cursorAzul[0], cursorAzul[1], cursorAzul[0]-1, cursorAzul[1],"azul");
			cursorAzul[0]--;
		}
	}
	public void pressedD() {
		if(cAzul&&cursorAzul[0]!=2) {
			selectTile(cursorAzul[0], cursorAzul[1], cursorAzul[0]+1, cursorAzul[1], "azul");
			cursorAzul[0]++;
		 }
	}
	public void pressedUP() {
		if(cVermelho&&cursorVermelho[1]!=0) {
			selectTile(cursorVermelho[0], cursorVermelho[1], cursorVermelho[0], cursorVermelho[1]-1, "vermelho");
			cursorVermelho[1]--;
		}
	}
	public void pressedDOWN() {
		if(cVermelho&&cursorVermelho[1]!=9) {
			selectTile(cursorVermelho[0], cursorVermelho[1], cursorVermelho[0], cursorVermelho[1]+1, "vermelho");
			cursorVermelho[1]++;
		}
	}
	public void pressedLEFT() {
		if(cVermelho&&cursorVermelho[0]!=7) {
			selectTile(cursorVermelho[0], cursorVermelho[1], cursorVermelho[0]-1, cursorVermelho[1],"vermelho");
			cursorVermelho[0]--;
		}
	}
	public void pressedRIGHT() {
		if(cVermelho&&cursorVermelho[0]!=9) {
			selectTile(cursorVermelho[0], cursorVermelho[1], cursorVermelho[0]+1, cursorVermelho[1], "vermelho");
			cursorVermelho[0]++;
		 }
	}
	public void positionPeca(Jogador jogador,IPecaCard peca) {
		if(jogador1==jogador) {
			setCursor("azul");  
			azulPeca=peca;
		}
		else if(jogador2==jogador) {
			setCursor("vermelho");
			vermelhoPeca=peca;
		}
	}
	public void setJogador(IJogador jogador) {
		if(jogador1==null)jogador1=jogador;
		else jogador2=jogador;
	}
	public void pressedSPACE() {
		if(azulPeca!=null && cAzul) {
			if(!matriz[cursorAzul[0]][cursorAzul[1]].existsPeca()) {
				matriz[cursorAzul[0]][cursorAzul[1]].setPeca(azulPeca);
				inTab[0]+=1;
			}
			hideCursor("azul");
			azulPeca=null;
		}
		
	}
	public void pressedENTER() {
		if(vermelhoPeca!=null && cVermelho) {
			if(!matriz[cursorVermelho[0]][cursorVermelho[1]].existsPeca()) {
				matriz[cursorVermelho[0]][cursorVermelho[1]].setPeca(vermelhoPeca);
				matriz[cursorVermelho[0]][cursorVermelho[1]].getPeca().flip();
				inTab[1]+=1;
			}
			
			hideCursor("vermelho");
			vermelhoPeca=null;
		}
		
	}
	public void pressedQ() {
		if(cAzul!=false)hideCursor("azul");
		if(jogador1.obtainCursor()!=-1)jogador1.hideCursor();
		start1=true;
		if(start1&&start2)start();
		
	}
	public void pressedAspas() {
		if(cVermelho!=false)hideCursor("vermelho");
		if(jogador2.obtainCursor()!=-1)jogador2.hideCursor();
		start2=true;
		if(start1&&start2)start();
	}
	public void addProjectiles(Projectile projectile) {
		projectiles.add(projectile);
	}
	public void removeProjectiles(Projectile projectile) {
		projectiles.remove(projectile);
	}
	public int[] getIntab() {
		return inTab;
	}
	public void eliminateInTab(int i) {
		if(inTab[i]!=0)inTab[i]=inTab[i]-1;
		if(inTab[i]==0) {
			if(i==0) {
				jogador2.addPoint();
				if(jogador2.getPoints()>0)game.endGame("azul");
			}
			if(i==1) {
				jogador1.addPoint();
				if(jogador1.getPoints()>0)game.endGame("vermelho");
			}
			//game.newRound();
		}
	}
}
