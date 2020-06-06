package tabuleiro;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import game.GUI;

public class Tile extends JPanel{

	
	private static final long serialVersionUID = 4878418849447188406L;
	private static int[] startPositionScreen= {282,122};
	private int[] position;
	private double scale;
	private Image tileAtual;
	
	private Image tilePadrao;
	public Tile(GUI gui,int[] position){
		scale=gui.getScale();
		ImageIcon refTilePadrao=new ImageIcon("assets\\tilePadrao.png");
		tilePadrao=refTilePadrao.getImage();
		tilePadrao=tilePadrao.getScaledInstance((int)(tilePadrao.getWidth(null)*scale),(int) (tilePadrao.getHeight(null)*scale), Image.SCALE_DEFAULT);
		this.position=position;
		tileAtual=tilePadrao;
		
	}
	public void paint(Graphics g,Image img) {
		Graphics2D graficos= (Graphics2D) g;
		graficos.drawImage(img, (int)(scale*(startPositionScreen[0])+position[0]*img.getWidth(null)), (int)(scale*(startPositionScreen[1])+position[1]*img.getWidth(null)), this);
		g.dispose();
	}
	public Image getImage() {
		return tileAtual;
	}
}
