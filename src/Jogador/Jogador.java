package Jogador;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import game.GUI;
import peca.Orc;

public class Jogador extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3795017485437177600L;
	/**
	 * 
	 */
	
	private CardJogador[] mao=new CardJogador[8];
	private Image imgmao;
	private double scale;
	private int positionX;
	private int positionY;
	
	public Jogador(GUI gui, int j){
		scale=gui.getScale();
		if(j==1) {
			positionX=(int)(scale*20);
			positionY=(int)(scale*240);
		}
		else {
			positionX=(int)(scale*696);
			positionY=(int)(scale*240);
		}
		initializeGui();
		
		for(int i=0;i<8;i++) {
			mao[i]= new CardJogador(gui, i%3);
		}
		
	}
	public void initializeGui(){
        ImageIcon refimgmao=new ImageIcon("assets/mao.png");        
        imgmao=refimgmao.getImage();
        int WIDTH = (int)(imgmao.getWidth(null)*scale);
        int HEIGHT = (int)(imgmao.getHeight(null)*scale);
        imgmao=imgmao.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_DEFAULT);
    }
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		int newX = positionX+4+(mao[0].getWidth()/2); //Adicionada 4 para ajuste visual
		int newY = positionY+4; //Adicionada 4 para ajuste visual
        g.drawImage(imgmao, positionX, positionY, null);	
        for(int i=0;i<8;i++){
        	if(mao[i]!=null) {
        		mao[i].paintComponent(g, newX, newY);
        		newX = newX+mao[i].getWidth();
        	}
        	if(i==1||i==4) {
        		newX=positionX+4;
        		newY=newY+mao[i].getHeight();
        	}
        }
	}
}
