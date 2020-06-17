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

public class Game extends Canvas implements Runnable, IGame,KeyListener{
    
    private static final long serialVersionUID = 7059646278559620203L;

    public static final int WIDTH = 1920, HEIGHT = 1080;
    private Thread thread;
    private GUI gui;
    private boolean running = false;
    private Banco banco = null;
    private Tabuleiro tabuleiro= null;
    private Jogador jogador1= null;
    private Jogador jogador2= null;
        
    public Game() {
    	 new Window(WIDTH, HEIGHT, "MediumWar", this);
    }
    
    public void gameStart(){ 
    	gui=new GUI();
        banco=new Banco(getScale());
        tabuleiro=new Tabuleiro(this);
        Peca.tabuleiro=(Tabuleiro) tabuleiro;
        jogador1=new Jogador(this,1,tabuleiro,banco);
        jogador2=new Jogador(this,2,tabuleiro,banco);
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
		case KeyEvent.VK_W:
			 
			 jogador1.pressedW();
			 tabuleiro.pressedW();
            break;
		case KeyEvent.VK_A:
			jogador1.pressedA();
			tabuleiro.pressedA();
			banco.pressedA();
            break;
		case KeyEvent.VK_S:
			jogador1.pressedS();
			tabuleiro.pressedS();
		case KeyEvent.VK_D:
			jogador1.pressedD();
			tabuleiro.pressedD();
			banco.pressedD();
		     break;
		case KeyEvent.VK_DOWN:
			
			
            break;
		case KeyEvent.VK_UP:
			
            break;
		case KeyEvent.VK_RIGHT:
			
            break;
		
		case KeyEvent.VK_LEFT:
			
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
