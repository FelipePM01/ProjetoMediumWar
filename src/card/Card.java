package card;

import java.awt.Graphics; 
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import game.GUI;

public class Card extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6677974077714234653L;
	private Image img;
	private int WIDTH;
	private int HEIGHT;
	private double scale;
    //private Peca peca;

    public Card(GUI gui, String refimagem){
        scale = gui.getScale();
        initializeGui(refimagem);
    }

    public void initializeGui(String refimag){
        ImageIcon refimg=new ImageIcon(refimag);        
        img=refimg.getImage();
        WIDTH = (int)(scale*img.getWidth(null));
        HEIGHT = (int)(scale*img.getHeight(null))-2; //subtrair 2 para ajuste visual
        img=img.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_DEFAULT);
      }
    
    public void paintComponent(Graphics g, int positionX, int positionY){
    	super.paintComponent(g);//Se comentar o construtor funciona
    	setOpaque(false);
    	if(img!=null)g.drawImage(img, positionX, positionY, this);
	}
    
    public int getWidth() {
    	return WIDTH;
    }
    public int getHeight() {
    	return HEIGHT;
    }
}
