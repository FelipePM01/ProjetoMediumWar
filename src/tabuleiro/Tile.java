package tabuleiro;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import peca.Archer;
import peca.IPeca;
import peca.IPecaCard;
import peca.IPecaTile;
import peca.Knight;
import peca.Orc;
import peca.Peca;

public class Tile extends JPanel implements ITile{

	private static final long serialVersionUID = 4878418849447188406L;
	private static int[] startPositionScreen= {282,122};
	private int[] position=new int[2];
	private double scale;
	private Image tileAtual;
	private Image tilePadrao;
	private Image tileVermelho;
	private Image tileAzul;
	private ITabuleiroTile tabuleiro;
	private IPecaTile peca=null;
	
	public Tile(ITabuleiroTile tabuleiro,int[] position){
		scale=tabuleiro.getScale();
		this.tabuleiro=tabuleiro;
		ImageIcon refTilePadrao=new ImageIcon("assets/tilePadrao.png");
		tilePadrao=refTilePadrao.getImage();
		tilePadrao=tilePadrao.getScaledInstance((int)(tilePadrao.getWidth(null)*scale),(int) (tilePadrao.getHeight(null)*scale), Image.SCALE_DEFAULT);
		ImageIcon refTileVermelho=new ImageIcon("assets/tileVermelho.png");
		tileVermelho=refTileVermelho.getImage();
		tileVermelho=tileVermelho.getScaledInstance((int)(tileVermelho.getWidth(null)*scale),(int) (tileVermelho.getHeight(null)*scale), Image.SCALE_DEFAULT);
		ImageIcon refTileAzul=new ImageIcon("assets/tileAzul.png");
		tileAzul=refTileAzul.getImage();
		tileAzul=tileAzul.getScaledInstance((int)(tileAzul.getWidth(null)*scale),(int) (tileAzul.getHeight(null)*scale), Image.SCALE_DEFAULT);
		
		this.position=position;
		tileAtual=tilePadrao;
		
	}
	
	public void paintComponent(Graphics g,Image img) {
		super.paintComponent(g);
		setOpaque(false);
		g.drawImage(img, (int)(scale*(startPositionScreen[0])+position[0]*img.getWidth(null)), (int)(scale*(startPositionScreen[1])+position[1]*img.getWidth(null)), this);
	}
	public int[] getGUIPosition() {
		int[] GUIPosition={(int)(scale*(startPositionScreen[0])+position[0]*tileAtual.getWidth(null)),(int)(scale*(startPositionScreen[1])+position[1]*tileAtual.getWidth(null))};
		return GUIPosition;
	}
	public Image getImage() {
		return tileAtual;
	}
	public void paintPeca(Graphics g) {
		if(peca!=null&&peca.getInBoard())peca.paintComponent(g,0,0);
	}
	public void actionPeca() {
		if(peca!=null)peca.moveOrAttack();
	}
	public boolean existsPeca() {
		if(peca!=null)return true;
		return false;
	}
	public void nullTarget() {
		peca.setTarget(null);
	}
	
	public void clearTile() {
		nullTarget();
		setNull();
	}
	
	public void setPeca(IPecaCard peca) {
	
		if (peca instanceof Archer)this.peca=new Archer(peca,this);
		else if (peca instanceof Knight)this.peca=new Knight(peca,this);
		else if (peca instanceof Orc)this.peca=new Orc(peca,this);
		else if(peca==null)this.peca=null;
		
	}
	public IPecaTile getPeca() {
		return peca;
	}
	public int[] getPosition() {
		int[] pos={position[0],position[1]};
		return pos;
	}
	public Tile[][] getOtherTiles() {
		return tabuleiro.getTiles();
	}
	public double getScale() {
		return scale;
	}
	public void setTileAtual(String cor) {
		  switch(cor){
		  case "azul":
			  tileAtual=tileAzul;
			  break;
		  case "vermelho":
			  tileAtual=tileVermelho;
			  break;
		  case "padrao":
			  tileAtual=tilePadrao;
			  break;
		  	}
	  }

	
	public void setPeca(Peca peca) {
		this.peca=peca;
		
	}
	public void setNull() {
		peca=null;
	}
	public static double dist(ITilePeca tile1,ITilePeca tile2) {
		int[] pos1=tile1.getPosition(),pos2=tile2.getPosition();
		return Math.sqrt(Math.pow(pos1[0]-pos2[0],2)+Math.pow(pos1[1]-pos2[1],2));
		
	}
	
}
