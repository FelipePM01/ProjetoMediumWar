package card;

import java.awt.Graphics; 
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import game.GUI;
import peca.Peca;

public abstract class Card extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6677974077714234653L;
	private Image img, imgpeca;
	private int WIDTH;
	private int HEIGHT;
	private int[] startPoint = new int[2];
	private int[] startPointCard = new int[2];
	private double scale;
    private Peca peca;

    public Card(GUI gui, String refImagem, String refPeca){
        scale = gui.getScale();
        initializeGui(refImagem);
        initializeGuiPeca(refPeca);
    }

    public void initializeGui(String refimag){
        ImageIcon refimg=new ImageIcon(refimag);        
        img=refimg.getImage();
        WIDTH = (int)(scale*img.getWidth(null));
        HEIGHT = (int)(scale*img.getHeight(null));
        img=img.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_DEFAULT);
      }
    
    public void initializeGuiPeca(String refimag){
        ImageIcon refimg=new ImageIcon(refimag);        
        imgpeca=refimg.getImage();
        imgpeca=imgpeca.getScaledInstance((int)(4*scale*imgpeca.getWidth(null)), (int)(4*scale*imgpeca.getHeight(null)), Image.SCALE_DEFAULT);
      }
    
    public void paintComponent(Graphics g, int positionX, int positionY){
    	super.paintComponent(g);
    	setOpaque(false);
    	if(img!=null)g.drawImage(img, positionX, positionY, this);
    	if(imgpeca!=null)g.drawImage(imgpeca, positionX+25, positionY+25, this);
    	startPointCard[0]=positionX;
    	startPointCard[1]=positionY;
    	//if(peca!=null)g.drawImage(peca, centerXY[0], centerXY[1], this);
	}
    public int[] getStartPoint() {
    	int[] Point = new int[2];
    	for(int i=0;i<2;i++)
    		Point[i]=startPointCard[i]+(int)(scale*startPoint[i]);
    	return startPoint;
    }
    public int getWidth() {
    	return WIDTH;
    }
    public int getHeight() {
    	return HEIGHT;
    }
}
