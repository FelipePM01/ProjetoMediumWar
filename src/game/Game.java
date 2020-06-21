package game;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import Jogador.IJogador;
import Jogador.IJogadorGame;
import Jogador.Jogador;
import banco.Banco;
import banco.IBanco;
import banco.IBancoGame;
import tabuleiro.ITabuleiro;
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
    private IBanco banco = null;
    private ITabuleiro tabuleiro= null;
    private IJogador jogador1= null;
    private IJogador jogador2= null;
    private static Window window;
    private boolean commands1=true;
    private boolean commands2=true;
        
    public Game() {
    	 window = new Window(WIDTH, HEIGHT, "MediumWar", this);
    }
    
    public void gameStart(){ 
    	gui=new GUI();
        banco=new Banco(getScale());
        tabuleiro=new Tabuleiro(this);
        Peca.tabuleiro=(Tabuleiro) tabuleiro;
        jogador1=new Jogador(this,1,tabuleiro,banco);
        jogador2=new Jogador(this,2,tabuleiro,banco);
        banco.setJogador(jogador1);
        banco.setJogador(jogador2);
        tabuleiro.setJogador(jogador1);
        tabuleiro.setJogador(jogador2);
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
            //thread.join();
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
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		switch(code){
		case KeyEvent.VK_W:
			if(commands1)tabuleiro.pressedW();
            break;
		case KeyEvent.VK_A:
			if(commands1) {
				jogador1.pressedA();
				tabuleiro.pressedA();
				banco.pressedA();
			}
            break;
		case KeyEvent.VK_S:
			
			if(commands1)tabuleiro.pressedS();
			break;
		case KeyEvent.VK_D:
			if(commands1) {
				jogador1.pressedD();
				tabuleiro.pressedD();
				banco.pressedD();
			}
		     break;
		case KeyEvent.VK_DOWN:
			
			if(commands2)tabuleiro.pressedDOWN();
            break;
		case KeyEvent.VK_UP:
			
			if(commands2)tabuleiro.pressedUP();
            break;
		case KeyEvent.VK_RIGHT:
			if(commands2) {
				jogador2.pressedRIGHT();
				tabuleiro.pressedRIGHT();
				banco.pressedRIGHT();
				}
            break;
		case KeyEvent.VK_LEFT:
			if(commands2) {
				jogador2.pressedLEFT();
				tabuleiro.pressedLEFT();
				banco.pressedLEFT();
				}
			break;

		case KeyEvent.VK_SLASH:
			if(commands2)jogador2.pressedBARRA();
			
			break;
		case KeyEvent.VK_PERIOD:
			if(commands2)jogador2.pressedPONTO();
			
			break;
		case KeyEvent.VK_COMMA:
			if(commands2)jogador2.pressedVIRGULA();
			
			break;
		case KeyEvent.VK_C:
			if(commands1)jogador1.pressedC();
			
			break;
		case KeyEvent.VK_X:
			if(commands1)jogador1.pressedX();
			
			break;
		case KeyEvent.VK_Z:
			if(commands1)jogador1.pressedZ();
			
			break;
		case KeyEvent.VK_SPACE:
			if(commands1) {
				tabuleiro.pressedSPACE();
				banco.pressedSPACE();
				jogador1.pressedSPACE();
			}
			break;
		case KeyEvent.VK_ENTER:
			if(commands2) {
				tabuleiro.pressedENTER();
				banco.pressedENTER();
				jogador2.pressedENTER();
			}
			break;
		
		case KeyEvent.VK_Q:
			commands1=false;
			if(banco.obtainCursor("azul")!=-1)banco.hideCursor(1);
			tabuleiro.pressedQ();
			//banco.pressedENTER();
			//jogador2.pressedENTER();
			break;
		case KeyEvent.VK_QUOTE:
			commands2=false;
			if(banco.obtainCursor("vermelho")!=-1)banco.hideCursor(2);
			tabuleiro.pressedAspas();
			//banco.pressedENTER();
			//jogador2.pressedENTER();
			break;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	public void newRound() {
		tabuleiro.clear();
		jogador1.addCash(3);
		jogador2.addCash(3);
		banco.refresh();
		commands1=true;
		commands2=true;
	}
	public void endGame() {
		window.endGame();
		stop();	
		System.out.println("endGame");
	}
	public static void main(String[] args) {
        new Game();  
    }
}
