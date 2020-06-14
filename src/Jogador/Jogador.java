package Jogador;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import card.ICardJogador;
import game.IGame;

public class Jogador extends JPanel implements IJogador{
	
	private static final long serialVersionUID = 3795017485437177600L;
	private ICardJogador[] mao=new CardJogador[8];
	private Image imgmao;
	private double scale=1;
	private int positionX;
	private int positionY;
	
	public Jogador(IGame game, int j){
		scale=game.getScale();
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
			mao[i]= new CardJogador(this, i%3);
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
		int newX = positionX+4+(mao[0].getWidth()/2);
		int newY = positionY+4;
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
	public CardJogador[] getMao() {
		return (CardJogador[])mao;
	}
	public double getScale() {
		return scale;
	}
}
