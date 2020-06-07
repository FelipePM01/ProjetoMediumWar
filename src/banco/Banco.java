package banco;

import game.GUI;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
	
public class Banco extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8183722388713930706L;
	private Image banco;
	private CardBanco[] pecasDisponiveis=new CardBanco[3];
	private double scale;
	
	public Banco(GUI gui){
		scale = gui.getScale();
		initializeGui(); 
		for(int i=0;i<3;i++)
			pecasDisponiveis[i] = new CardBanco(gui, 0);
	}
	
	public void initializeGui(){
        ImageIcon refbanco=new ImageIcon("assets/banco.png");        
        banco=refbanco.getImage();
        int WIDTH = (int)(banco.getWidth(null)*scale);
        int HEIGHT = (int)(banco.getHeight(null)*scale);
        banco=banco.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_DEFAULT);
    }
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		int positionX = (int)(scale*356); //356
		int positionY = (int)(scale*16);	//16
		int newX = positionX+4; //Adicionada 4 para ajuste visual
		int newY = positionY+4; //Adicionada 4 para ajuste visual
        g.drawImage(banco, positionX, positionY, null);	
        for(int i=0;i<3;i++){
        	pecasDisponiveis[i].paintComponent(g, newX, newY);
        	newX = newX+pecasDisponiveis[i].getWidth();
        }
	}
	
}
