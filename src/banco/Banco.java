package banco;

import game.GUI;
import peca.Archer;
import peca.Knight;
import peca.Orc;
import peca.Peca;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

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
	private Peca[] todas= new Peca[3];
	private Random random = new Random();
	
	public Banco(GUI gui){
		scale = gui.getScale();
		initializeGui(); 
		todas[0] = new Archer(gui);
		todas[1] = new Knight(gui);
		todas[2] = new Orc(gui);
		for(int i=0;i<3;i++) {
			int x;
			x = random.nextInt(todas.length);
			pecasDisponiveis[i] = new CardBanco(gui, 0 );
			if(x==0)pecasDisponiveis[i].setPeca(new Archer(todas[0],pecasDisponiveis[i]));
			if(x==1)pecasDisponiveis[i].setPeca(new Knight(todas[1],pecasDisponiveis[i]));
			if(x==2)pecasDisponiveis[i].setPeca(new Orc(todas[2],pecasDisponiveis[i]));
			
			
			
		}
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
