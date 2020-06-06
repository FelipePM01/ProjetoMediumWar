package card;

import java.awt.Graphics; 
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import game.GUI;

public class Card extends JPanel{
	
	private int WIDTH;
	private int HEIGHT;
	private Image img;
	private double scale;
    //private Peca peca;

    public Card(GUI gui, String refimagem){
        scale = gui.getScale();
        initializeGui(refimagem);
    }

    public void initializeGui(String refimag){
        ImageIcon refimg=new ImageIcon("refimag");        
        img=refimg.getImage();
        WIDTH = (int)(img.getWidth(null)*scale);
        HEIGHT = (int)(img.getWidth(null)*scale);
        img=img.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_DEFAULT);
      }
    
    public void paintComponent(Graphics g, positionX, positonY){
    	super.paintComponent(g);
        g.drawImage(img, positionX, positionY, this);
	}
    
    public int getWidth() {
    	return WIDTH;
    }
    public int getHeight() {
    	return HEIGHT;
    }
}
