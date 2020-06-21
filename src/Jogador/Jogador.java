package Jogador;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import banco.Banco;
import banco.IBanco;
import banco.IBancoJogador;
import card.ICardJogador;
import game.IGame;
import peca.IPecaCard;
import tabuleiro.ITabuleiro;
import tabuleiro.ITabuleiroJogador;
import peca.IPecaCardJogador;
import peca.Knight;
import peca.Orc;
import peca.Peca;
import tabuleiro.ITabuleiroJogador;
import tabuleiro.Tabuleiro;

public class Jogador extends JPanel implements IJogador{
	
	private static final long serialVersionUID = 3795017485437177600L;
	private ICardJogador[] mao=new CardJogador[8];
	private Image imgmao, imgCoin, imgTrophy;
	private double scale=1;
	private int positionX;
	private int positionY;
	private IBancoJogador banco;
	private ITabuleiroJogador tabuleiro;
	private String cor;
	private int cursor = -1;
	private int colocar=-1;
	private IPecaCard recebido;
	private String currentAction;
	private int cash = 10;
	private int points = 0;
	
	
	public Jogador(IGame game, int j,ITabuleiro tabuleiro,IBanco banco){
		scale=game.getScale();
		this.tabuleiro=tabuleiro;
		this.banco=banco;
		if(j==1) {
			positionX=(int)(scale*20);
			positionY=(int)(scale*240);
			cor="azul";

		}
		else {
			positionX=(int)(scale*696);
			positionY=(int)(scale*240);
			cor="vermelho";
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
        
        ImageIcon refCoin=new ImageIcon("assets/coin.png");
		imgCoin=refCoin.getImage();
    	imgCoin=imgCoin.getScaledInstance((int)(1.6*scale*imgCoin.getWidth(null)), (int)(1.5*scale*imgCoin.getHeight(null)), Image.SCALE_DEFAULT);    	
    
    	ImageIcon refTrophy=new ImageIcon("assets/trophy.png");
 		imgTrophy=refTrophy.getImage();
     	imgTrophy=imgTrophy.getScaledInstance((int)(1.6*scale*imgTrophy.getWidth(null)), (int)(1.5*scale*imgTrophy.getHeight(null)), Image.SCALE_DEFAULT);    	
     
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		if(imgCoin!=null)g.drawImage(imgCoin,10,10,this);	
		if(imgCoin!=null)g.drawImage(imgCoin,1920-10-imgCoin.getWidth(null),10,this);
		
		
		g.setFont(new Font("Arial",1, 55));
		if(cor=="azul") {
			g.drawString(String.valueOf(cash), (int)(15+imgCoin.getWidth(null)),imgCoin.getHeight(null)+6);
			for(int i=0;i<points;i++)
				 g.drawImage(imgTrophy,5+i*(5+imgTrophy.getWidth(null)),20+imgCoin.getHeight(null),this);
		}
		if(cor=="vermelho") {
			g.drawString(String.valueOf(cash),(int)(1920-(g.getFontMetrics().stringWidth(String.valueOf(cash))+imgCoin.getWidth(null)+15)),imgCoin.getHeight(null)+6);
			for(int i=1;i<=points;i++)
				 g.drawImage(imgTrophy,1920-i*(5+imgTrophy.getWidth(null)),20+imgCoin.getHeight(null),this);
		}
		
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
	public ICardJogador[] getMao() {
		return mao;
	}
	public double getScale() {
		return scale;
	}
	public void hideCursor() {
		if(cursor>=0&&mao[cursor]!=null)mao[cursor].setCardAtual("padrao");
		cursor=-1;
	}
	public void setCursor() {
		cursor=0;
		selectCardJogador(0,0,cor);
	}
	public void selectCardJogador(int xAnt, int x, String cor) {
		if(mao[xAnt]!=null)mao[xAnt].setCardAtual("padrao");
		if(mao[x]!=null)mao[x].setCardAtual(cor);
	}
	public void pressedA() {
		if(cursor>=0) {
			if(cursor-1<0) {
				selectCardJogador(cursor, 7, cor);
				cursor=7;
			}
			else {
				selectCardJogador(cursor, cursor-1,cor);
				cursor--;
			}	
		}
	}
	public void pressedC() {
		if(cursor!=-1)hideCursor();
		if(tabuleiro.getCursor(cor))tabuleiro.hideCursor(cor);
		for(int i=0;i<8;i++) {
			if(mao[i].ehNulo()) {
				banco.comprar(this);
				colocar=i;
				break;
			}
		}
	}
	public void pressedVIRGULA() {
		if(cursor!=-1)hideCursor();
		if(tabuleiro.getCursor(cor))tabuleiro.hideCursor(cor);
		for(int i=0;i<8;i++) {
			if(mao[i].ehNulo()) {
				
				banco.comprar(this);
				colocar=i;
				
				break;
			}
		}
	}
	public void pressedD() {
		if(cursor>=0) {
			if(cursor+1>7) {
				selectCardJogador(cursor, 0, cor);
				cursor=0;
			}
			else {
				selectCardJogador(cursor, cursor+1,cor);
				cursor++;
			}
		}
	}
	public void pressedLEFT() {
		if(cursor>=0) {
			if(cursor-1<0) {
				selectCardJogador(cursor, 7, cor);
				cursor=7;
			}
			else {
				selectCardJogador(cursor, cursor-1,cor);
				cursor--;
			}	
		}
	}
	public void pressedRIGHT() {
		if(cursor>=0) {
			if(cursor+1>7) {
				selectCardJogador(cursor, 0, cor);
				cursor=0;
			}
			else {
				selectCardJogador(cursor, cursor+1,cor);
				cursor++;
			}
		}
	}
	public void pressedZ() {
		if(cursor!=-1)hideCursor();
		if(banco.getCursor(1)!=-1)banco.hideCursor(1);
		if(tabuleiro.getCursor(cor))tabuleiro.hideCursor(cor);
		setCursor();
		currentAction="remove";
	}
	public void pressedX() {
		if(cursor!=-1)hideCursor();
		if(tabuleiro.getCursor(cor))tabuleiro.hideCursor(cor);
		if(banco.getCursor(1)!=-1)banco.hideCursor(1);
		setCursor();
		currentAction="position";
	}
	public void pressedPONTO() {
		if(cursor!=-1)hideCursor();
		if(tabuleiro.getCursor(cor))tabuleiro.hideCursor(cor);
		if(banco.getCursor(2)!=-1)banco.hideCursor(2);
		setCursor();
		currentAction="position";
	}
	public void pressedBARRA() {
		if(cursor!=-1)hideCursor();
		if(banco.getCursor(2)!=-1)banco.hideCursor(2);
		if(tabuleiro.getCursor(cor))tabuleiro.hideCursor(cor);
		setCursor();
		currentAction="remove";
	}
	public void pressedSPACE() {
		if(currentAction=="remove") {
			
			remove();
			currentAction=null;
			if(cursor!=-1)hideCursor();
		}
		else if(currentAction=="position") {
			
			position();
		}
	}
	public void pressedENTER() {
		if(currentAction=="remove") {
			remove();
			currentAction=null;
			if(cursor!=-1)hideCursor();
		}
		else if(currentAction=="position") {
			position();
			//currentAction=null;
		}
	}
	
	public void remove() {
		if(cursor>=0) {
			addCash(mao[cursor].getPeca().getSaleValue());
			mao[cursor].setPeca(null);
		}
	}
	public void position() {
		if(cursor>=0) {
			if(mao[cursor].getPeca()!=null&&mao[cursor].getNaoColocado()) {
				tabuleiro.positionPeca(this,mao[cursor].getPeca());
				if(cursor!=-1)hideCursor();
				 currentAction="posicionando";
			}
			else hideCursor();
		}
	}
	public void receber(IPecaCard peca) {
		recebido=peca;
		
		mao[colocar].setPeca(recebido);
		
		colocar=-1;
	}
	public void posicionado() {
		currentAction=null;
		setCursor();
	}

	@Override
	public String getCor() {
		
		return cor;
	}
	public int getCash() {
		return cash;
	}
	public void addCash(int valor) {
		cash+=valor;
	}
	public void addPoint() {
		points+=1;
	}
}
