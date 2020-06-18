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
	private boolean concluida=false;
	private IPecaCard recebido;
	
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
		//setCursor();
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
		selectCardJogador(0,0,"padrao");
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
	public void pressedRight() {
//		if(cursor[0]!=9) {
//			tabuleiro.selectTile(cursor[0], cursor[1], cursor[0]+1, cursor[1], cor);
//			cursor[0]++;
//		 }
	}
	public void pressedC() {
		
		for(int i=0;i<8;i++) {
			if(mao[i].ehNulo()) {
				banco.comprar(this);
				while(!concluida) {}
				mao[i].setPeca(recebido);
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
	public void pressedLeft() {
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
	public void receber(IPecaCard peca) {
		recebido=peca;
		concluida=true;
	}
	
}
