package card;

import java.awt.Graphics; 
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import game.GUI;

public abstract class Card extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6677974077714234653L;
	private Image img, peca;
	private int WIDTH;
	private int HEIGHT;
	private int[] centerXY = new int[2];
	private double scale;
    //private Peca peca;

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
        peca=refimg.getImage();
        peca=peca.getScaledInstance((int)(4*scale*peca.getWidth(null)), (int)(4*scale*peca.getHeight(null)), Image.SCALE_DEFAULT);
      }
    
    public void paintComponent(Graphics g, int positionX, int positionY){
    	super.paintComponent(g);
    	setOpaque(false);
    	if(img!=null)g.drawImage(img, positionX, positionY, this);
    	setCenterXY(positionX, positionY);
    	if(peca!=null)g.drawImage(peca, positionX+25, positionY+25, this);
    	//if(peca!=null)g.drawImage(peca, centerXY[0], centerXY[1], this);
	}
    public void setCenterXY(int positionX, int positionY) {
    	centerXY[0] = positionX+WIDTH/2;
    	centerXY[1] = positionY+HEIGHT/2;
    }
    
    public int getWidth() {
    	return WIDTH;
    }
    public int getHeight() {
    	return HEIGHT;
    }
}
