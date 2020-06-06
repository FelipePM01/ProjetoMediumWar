package banco;

import card.CardBanco;
import game.GUI;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
	
public class Banco extends JPanel{
	private Image banco;
	private CardBanco[] pecasDisponiveis;
	private double scale;
	
	public Banco(GUI gui){
		scale = gui.getScale();
		initializeGui(); 
		for(int i=1;i<=3;i++)
        	pecasDisponiveis[i] = new CardBanco(gui);
	}
	
	public void initializeGui(){
        ImageIcon refbanco=new ImageIcon("assets/banco.png");        
        banco=refbanco.getImage();
        int WIDTH = (int)(banco.getWidth(null)*scale);
        int HEIGHT = (int)(banco.getWidth(null)*scale);
        banco=banco.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_DEFAULT);
    }
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		int positionX = (int)scale*357;
		int positionY = (int)scale*17;
		int newX = positionX;
		int newY = positionY;
        g.drawImage(banco, positionX, positionY, null);
        for(int i=0;i<3;i++){
        	newX = newX+pecasDisponiveis[i].getWidth();
        	newY = newY+pecasDisponiveis[i].getHeight();
        	if(pecasDisponiveis[i]!=null)pecasDisponiveis[i].paintComponent(g,newX,newY);
        }
	}
}
