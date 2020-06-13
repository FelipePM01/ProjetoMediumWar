package game;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.SwingUtilities;

import Jogador.Jogador;
import banco.Banco;
import tabuleiro.Tabuleiro;
import card.Card;
import peca.Archer;
import peca.Peca;
import tabuleiro.Tile;



public class Game extends Canvas implements Runnable, IGame, KeyListener{
    
    private static final long serialVersionUID = 7059646278559620203L;

    public static final int WIDTH = 1920, HEIGHT = 1080;  //1024*576
    
    private Thread thread;
    private GUI gui;
    private boolean running = false;
    private Banco banco = null;
    private Tabuleiro tabuleiro= null;
    private Jogador jogador1= null;
    private Jogador jogador2= null;
    
    public void gameStart(){ 
       
        
        gui=new GUI();
        banco=new Banco(this);
  
        tabuleiro=new Tabuleiro(this);

        Peca.tabuleiro=tabuleiro;


        jogador1=new Jogador(this,1);
        jogador2=new Jogador(this,2);
        
        addKeyListener(this);
    }
    public Game() {
    	 new Window(WIDTH, HEIGHT, "MediumWar", this);
    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
        tabuleiro.start();
    }
    
    public synchronized void stop() {
        try{
            thread.join();
            running = false;
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void run(){
       
        while(true){ 
             	render();
             	
            	
            	
            	
            	
            	
            }
        
    }

    
    public double getScale() {
    	if(gui!=null)return gui.getScale();
    	return 1;
    }
    

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs==null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        
        if(gui!=null)gui.paintComponent(g);
        if(tabuleiro!=null)tabuleiro.paintComponent(g);
        if(banco!=null)banco.paintComponent(g);
        if(jogador1!=null)jogador1.paintComponent(g);
        if(jogador2!=null)jogador2.paintComponent(g);
        
        
        g.dispose();
        bs.show();
    }

    public static void main(String[] args) {
        new Game();
    }

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int code = e.getKeyCode();
		switch(code){
		case KeyEvent.VK_1:
			System.out.println("1");
            break;
		case KeyEvent.VK_2:
			banco.refresh();
            break;
		case KeyEvent.VK_3:
			tabuleiro.start();
		case KeyEvent.VK_SPACE:
			
	        Peca archer=new Archer(this);
	        tabuleiro.getTiles()[5][5].setPeca(new Archer(archer,tabuleiro.getTiles()[5][5]));
            break;
		case KeyEvent.VK_B:
			System.out.println("B");
            break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}
}
