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
		int positionX = (int)(scale*357); //356+1
		int positionY = (int)(scale*17);	//16+1
		int newX = positionX+3; //Adicionada 3 para ajuste visual
		int newY = positionY+3; //Adicionada 3 para ajuste visual
        g.drawImage(banco, positionX, positionY, null);	
        for(int i=0;i<3;i++){
        	//pecasDisponiveis[i].setCoordenadas ();
        	pecasDisponiveis[i].paintComponent(g, newX, newY);
        	newX = newX+pecasDisponiveis[i].getWidth();
        }
	}
}
