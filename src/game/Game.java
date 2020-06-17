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
			 jogador1.pressedUp();
			 //tabuleiro.pressedW();
            break;
		case KeyEvent.VK_A:
			jogador1.pressedLeft();
			//tabuleiro.pressedA();
			//banco.pressedA();
            break;
		case KeyEvent.VK_S:
			jogador1.pressedDown();
			//tabuleiro.pressedS();
			break;
		case KeyEvent.VK_D:
			jogador1.pressedRight();
			//tabuleiro.pressedD();
			//banco.pressedD();
		     break;
		case KeyEvent.VK_DOWN:
			jogador2.pressedDown();
			//tabuleiro.pressedDOWN();
            break;
		case KeyEvent.VK_UP:
			jogador2.pressedUp();
			//tabuleiro.pressedUP();
            break;
		case KeyEvent.VK_RIGHT:
			jogador2.pressedRight();
			//tabuleiro.pressedRIGHT();
			//banco.pressedRIGHT();
            break;
		case KeyEvent.VK_LEFT:
			jogador2.pressedLeft();
			//tabuleiro.pressedLEFT();
			//banco.pressedLEFT();
			break;
		
		case KeyEvent.VK_SLASH:
			jogador2.pressedBARRA();
			
			break;
		case KeyEvent.VK_PERIOD:
			jogador2.pressedPONTO();
			
			break;
		case KeyEvent.VK_COMMA:
			jogador2.pressedVIRGULA();
			
			break;
		case KeyEvent.VK_C:
			jogador2.pressedC();
			
			break;
		case KeyEvent.VK_X:
			jogador2.pressedX();
			
			break;
		case KeyEvent.VK_Z:
			jogador2.pressedZ();
			
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
