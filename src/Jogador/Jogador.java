package Jogador;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import banco.Banco;
import card.ICardJogador;
import game.IGame;
import peca.Archer;
import peca.IPecaCard;
import peca.IPecaCardJogador;
import peca.Knight;
import peca.Orc;
import peca.Peca;
import tabuleiro.Tabuleiro;

public class Jogador extends JPanel implements IJogador{
	
	private static final long serialVersionUID = 3795017485437177600L;
	private ICardJogador[] mao=new CardJogador[8];
	private Image imgmao;
	private double scale=1;
	private int positionX;
	private int positionY;
	private Banco banco;
	private Tabuleiro tabuleiro;
	private String cor;
	private int cursor = -1;
	private int colocar=-1;
	private IPecaCard recebido;
	private String currentAction;
	
	public Jogador(IGame game, int j,Tabuleiro tabuleiro,Banco banco){
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
	public ICardJogador[] getMao() {
		return mao;
	}
	public double getScale() {
		return scale;
	}
	public void hideCursor() {
		mao[cursor].setCardAtual("padrao");
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
		
		for(int i=0;i<8;i++) {
			if(mao[i].ehNulo()) {
				
				banco.comprar(this);
				colocar=i;
				
				break;
			}
		}
	}
	public void pressedBARRA() {
		
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
		setCursor();
		currentAction="remove";
	}
//	public void pressedX() {
//		setCursor();
//		currentAction="position";
//	}
	public void pressedSPACE() {
		if(currentAction=="remove") {
			System.out.println(1);
			remove();
			currentAction=null;
		}
//		else if(currentAction=="position") {
//			System.out.println(1);
//			position();
//			currentAction=null;
//		}
	}
	public void remove() {
		if(cursor>=0) {
			mao[cursor].setPeca(null);
		}
	}
//	public void position() {
//		if(cursor>=0) {
//			tabuleiro.positionPeca(this,mao[cursor]);
//		}
//	}
	public void receber(IPecaCard peca) {
		recebido=peca;
		
		mao[colocar].setPeca(recebido);
		
		colocar=-1;
	}
	
}
