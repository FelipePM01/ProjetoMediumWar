package tabuleiro;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import game.GUI;
import peca.Peca;

public class Tile extends JPanel{

	
	private static final long serialVersionUID = 4878418849447188406L;
	private static int[] startPositionScreen= {282,122};
	private int[] position;
	private double scale;
	private static Image tileAtual;
	private static Image tileVermelho;
	private static Image tileAzul;
	
	private Image tilePadrao;
	private Peca peca=null;
	public Tile(GUI gui,int[] position){
		scale=gui.getScale();
		
		ImageIcon refTilePadrao=new ImageIcon("assets\\tilePadrao.png");
		tilePadrao=refTilePadrao.getImage();
		tilePadrao=tilePadrao.getScaledInstance((int)(tilePadrao.getWidth(null)*scale),(int) (tilePadrao.getHeight(null)*scale), Image.SCALE_DEFAULT);
		ImageIcon refTileVermelho=new ImageIcon("assets\\tileVermelho.png");
		tileVermelho=refTileVermelho.getImage();
		tileVermelho=tileVermelho.getScaledInstance((int)(tileVermelho.getWidth(null)*scale),(int) (tileVermelho.getHeight(null)*scale), Image.SCALE_DEFAULT);
		ImageIcon refTileAzul=new ImageIcon("assets\\tileAzul.png");
		tileAzul=refTileAzul.getImage();
		tileAzul=tileAzul.getScaledInstance((int)(tileAzul.getWidth(null)*scale),(int) (tileAzul.getHeight(null)*scale), Image.SCALE_DEFAULT);
		
		this.position=position;
		tileAtual=tilePadrao;
		
	}
	public void paintComponent(Graphics g,Image img) {
		
		super.paintComponent(g);
		if(img==null)System.out.println(1);
		g.drawImage(img, (int)(scale*(startPositionScreen[0])+position[0]*img.getWidth(null)), (int)(scale*(startPositionScreen[1])+position[1]*img.getWidth(null)), this);
		if(peca!=null)peca.paintComponent(g, position[0], position[1]);
	}
	public Image getImage() {
		return tileAtual;
	}
	
	public int[] getGUIPosition() {
		return position;
	}
	public void setPeca(Peca peca) {
		this.peca=peca;
	}
	
}
