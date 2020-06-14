package game;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import Jogador.IJogadorGame;
import Jogador.Jogador;
import banco.Banco;
import banco.IBancoGame;
import tabuleiro.ITabuleiroGame;
import tabuleiro.Tabuleiro;
import peca.Archer;
import peca.Knight;
import peca.Orc;
import peca.Peca;

public class Game extends Canvas implements Runnable, IGame, KeyListener{
    
    private static final long serialVersionUID = 7059646278559620203L;

    public static final int WIDTH = 1920, HEIGHT = 1080;
    private Thread thread;
    private GUI gui;
    private boolean running = false;
    private IBancoGame banco = null;
    private ITabuleiroGame tabuleiro= null;
    private IJogadorGame jogador1= null;
    private IJogadorGame jogador2= null;
        
    public Game() {
    	 new Window(WIDTH, HEIGHT, "MediumWar", this);
    }
    
    public void gameStart(){ 
    	gui=new GUI();
        banco=new Banco(getScale());
        tabuleiro=new Tabuleiro(this);
        Peca.tabuleiro=(Tabuleiro) tabuleiro;
        jogador1=new Jogador(this,1);
        jogador2=new Jogador(this,2);
        addKeyListener(this);
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
       while(running){ 
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
			 Peca knight=new Knight(getScale());
		     tabuleiro.getTiles()[2][7].setPeca(new Knight(knight,tabuleiro.getTiles()[2][7]));
            break;
		case KeyEvent.VK_2:
			 Peca orc=new Orc(getScale());
		     tabuleiro.getTiles()[7][7].setPeca(new Orc(orc,tabuleiro.getTiles()[7][7]));
            break;
		case KeyEvent.VK_3:
			 Peca archer=new Archer(getScale());
		     tabuleiro.getTiles()[2][2].setPeca(new Archer(archer,tabuleiro.getTiles()[2][2]));
		     break;
		case KeyEvent.VK_4:
			 tabuleiro.clear();
		     break;
		case KeyEvent.VK_SPACE:
			
			tabuleiro.start();
            break;
		case KeyEvent.VK_B:
			banco.refresh();
            break;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	
	public static void main(String[] args) {
        new Game();
    }
}
