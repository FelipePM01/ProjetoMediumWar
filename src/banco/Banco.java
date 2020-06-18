package banco;

import peca.Archer;
import peca.Knight;
import peca.Orc;
import peca.Peca;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Jogador.Jogador;
	
public class Banco extends JPanel implements IBanco{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8183722388713930706L;
	private Image banco;
	private CardBanco[] pecasDisponiveis=new CardBanco[3];
	private double scale=1;
	private Peca[] todas= new Peca[3];
	private Random random = new Random();
	private int cursor1=-1;
	private int cursor2=-1;
	private Jogador jogador1,jogador2;
	
	public Banco(double scale){
		this.scale=scale;
		initializeGui(); 
		todas[0] = new Archer(scale);
		todas[1] = new Knight(scale);
		todas[2] = new Orc(scale);
		refresh();
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
		atualizar();
		int positionX = (int)(scale*356);
		int positionY = (int)(scale*16);
		int newX = positionX+4;
		int newY = positionY+4;
        g.drawImage(banco, positionX, positionY, null);	
        for(int i=0;i<3;i++){
        	pecasDisponiveis[i].paintComponent(g, newX, newY);
        	newX = newX+pecasDisponiveis[i].getWidth();
        }
	}
	public void atualizar() {
		for(int i=0;i<3;i++) {
			if(cursor1!=i&&cursor2!=i)pecasDisponiveis[i].attImage("assets/cardPadrao.png");
			else if(cursor2!=i)pecasDisponiveis[i].attImage("assets/cardAzul.png");
			else if(cursor1!=i)pecasDisponiveis[i].attImage("assets/cardVermelho.png");
			else pecasDisponiveis[i].attImage("assets/cardAmbos.png");
		}
	}
	public void refresh() {
		for(int i=0;i<3;i++) {
			int x;
			x = random.nextInt(todas.length);
			pecasDisponiveis[i] = new CardBanco(this, 0 );
			if(x==0)pecasDisponiveis[i].setPeca(new Archer(todas[0],pecasDisponiveis[i]));
			if(x==1)pecasDisponiveis[i].setPeca(new Knight(todas[1],pecasDisponiveis[i]));
			if(x==2)pecasDisponiveis[i].setPeca(new Orc(todas[2],pecasDisponiveis[i]));
			
			
		}
	}
	public double getScale() {
		return scale;
	}
	public void comprar(Jogador jogador) {
		if(jogador==jogador1)cursor1=0;
		else cursor2=2;
	}
	public void setJogador(Jogador jogador) {
		if(jogador1==null)jogador1=jogador;
		else jogador2=jogador;
	}
	public void pressedLeft() {
		if(cursor2<0)cursor2--;
		
	}
	public void pressedRight() {
		if(cursor2!=-1&&cursor2<2)cursor2++;
	}
	public void pressedA() {
		if(cursor1<0)cursor1--;
		
	}
	public void pressedD() {
		if(cursor1!=-1&&cursor1<2)cursor1++;
	}
	public void pressedSPACE() {
		if(cursor1!=-1) {
			jogador1.receber(pecasDisponiveis[cursor1].getPeca());
		}
	}
	public void pressedENTER() {
		if(cursor1!=-1) {
			jogador2.receber(pecasDisponiveis[cursor2].getPeca());
		}
	}
}
