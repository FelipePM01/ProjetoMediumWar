package banco;

import peca.Archer;
import peca.IPeca;
import peca.Knight;
import peca.Orc;
import peca.Peca;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Jogador.IJogador;
import Jogador.IJogadorBanco;
import Jogador.Jogador;
import card.ICardBanco;
	
public class Banco extends JPanel implements IBanco{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8183722388713930706L;
	private Image banco;
	private ICardBanco[] pecasDisponiveis=new CardBanco[3];
	private double scale=1;
	private IPeca[] todas= new Peca[3];
	private Random random = new Random();
	protected int cursor1=-1;
	protected int cursor2=-1;
	private IJogadorBanco jogador1,jogador2;
	
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
		if(jogador==jogador1){
			if(cursor1!=-1)hideCursor(1);
			setCursor(1);
		}
		else {
			if(cursor2!=-1)hideCursor(2);
			setCursor(2);
		}
	}
	public void setJogador(IJogador jogador) {
		if(jogador1==null)jogador1=jogador;
		else jogador2=jogador;
	}
	public void setCursor(int i) {
		if(i==1) {
			cursor1=0;
			if(cursor1==cursor2)pecasDisponiveis[0].setCardAtual("ambos");
			else pecasDisponiveis[0].setCardAtual("azul");
		}
		else{
			cursor2=2;
			if(cursor1==cursor2)pecasDisponiveis[2].setCardAtual("ambos");
			else pecasDisponiveis[2].setCardAtual("vermelho");
		}		
	}
	public int getCursor(int i) {
		if(i==1)return cursor1;
		return cursor2;
	}
	public void hideCursor(int i){
		if(i==1) {
			if(cursor1==cursor2)pecasDisponiveis[cursor1].setCardAtual("vermelho");
			else pecasDisponiveis[cursor1].setCardAtual("padrao");
			cursor1=-1;
		}
		else{
			if(cursor1==cursor2)pecasDisponiveis[cursor2].setCardAtual("azul");
			else pecasDisponiveis[cursor2].setCardAtual("padrao");
			cursor2=-1;
		}
	}
	public void selectCardBanco(int xAnt, int x, String cor) {
		if(pecasDisponiveis[xAnt]!=null&&(cursor1==cursor2)) {
			if(cor=="vermelho")pecasDisponiveis[xAnt].setCardAtual("azul");
			else if(cor=="azul")pecasDisponiveis[xAnt].setCardAtual("vermelho");
		}
		else if(pecasDisponiveis[xAnt]!=null)pecasDisponiveis[xAnt].setCardAtual("padrao");
		
		if(pecasDisponiveis[x]!=null&&(x==cursor1||x==cursor2))pecasDisponiveis[x].setCardAtual("ambos");
		else if(pecasDisponiveis[x]!=null)pecasDisponiveis[x].setCardAtual(cor);
	}
	public void pressedLEFT() {
		if(cursor2>=0) {
			if(cursor2==0) {
				selectCardBanco(cursor2, 2, "vermelho");
				cursor2=2;
			}
			else {
				selectCardBanco(cursor2, cursor2-1,"vermelho");
				cursor2--;
			}	
		}		
	}
	public void pressedRIGHT() {
		if(cursor2>=0) {
			if(cursor2==2) {
				selectCardBanco(cursor2, 0, "vermelho");
				cursor2=0;
			}
			else {
				selectCardBanco(cursor2, cursor2+1,"vermelho");
				cursor2++;
			}
		}
	}
	public void pressedA() {
		if(cursor1>=0) {
			if(cursor1==0) {
				selectCardBanco(cursor1, 2, "azul");
				cursor1=2;
			}
			else {
				selectCardBanco(cursor1, cursor1-1,"azul");
				cursor1--;
			}	
		}
	}
	public void pressedD() {
		if(cursor1>=0) {
			if(cursor1==2) {
				selectCardBanco(cursor1, 0, "azul");
				cursor1=0;
			}
			else {
				selectCardBanco(cursor1, cursor1+1,"azul");
				cursor1++;
			}
		}
	}
	public void pressedSPACE() {
		if(cursor1!=-1) {
			jogador1.receber(pecasDisponiveis[cursor1].getPeca());
			hideCursor(1);
		}
	}
	public void pressedENTER() {
		if(cursor2!=-1) {
			jogador2.receber(pecasDisponiveis[cursor2].getPeca());
			hideCursor(2);
		}
	}

	@Override
	public void comprar(IJogadorBanco jogador) {
		// TODO Auto-generated method stub
		
	}

	
}
