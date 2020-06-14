package card;

import java.awt.Graphics; 
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import banco.IBancoCard;
import game.GUI;
import game.Game;
import game.IGame;
import peca.Peca;

public abstract class Card extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6677974077714234653L;
	private Image img, imgpeca;
	private int WIDTH;
	private int HEIGHT;
	private int[] startPointCard = new int[2];
	private double scale;
    private Peca peca=null;

    public Card(IBancoCard banco, String refImagem){
        scale = banco.getScale();
        
        initializeGui(refImagem);
    }
    public Card(IJogadorCard jogador, String refImagem){
        scale = jogador.getScale();
        
        initializeGui(refImagem);
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
    	
    	setOpaque(false);
    	if(img!=null)g.drawImage(img, positionX, positionY, this);
    	startPointCard[0]=positionX;
    	startPointCard[1]=positionY;
    	if(peca!=null)peca.paintComponent(g, positionX, positionY);
    	
	}
    public int[] getGUIPosition(){
    	return startPointCard;
    }
    public int getWidth() {
    	return WIDTH;
    }
    public int getHeight() {
    	return HEIGHT;
    }
    public void setPeca(Peca peca) {
    	this.peca=peca;
    }
}
