package game;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;

import Jogador.Jogador;
import banco.Banco;
import tabuleiro.Tabuleiro;
import card.Card;
import peca.Archer;
import tabuleiro.Tile;



public class Game extends Canvas implements Runnable, IGame{
    
    private static final long serialVersionUID = 7059646278559620203L;

    public static final int WIDTH = 1920, HEIGHT = 1080;  //1024*576
    
    private Thread thread;
    private GUI gui;
    private boolean running = false;
    private Banco banco = null;
    private Tabuleiro tabuleiro= null;
    private Jogador jogador1= null;
    private Jogador jogador2= null;
    
    public Game(){
    	//set backgraund 
    	
        new Window(WIDTH, HEIGHT, "MediumWar", this);
        
        gui=new GUI();

        banco=new Banco(this);
        
        tabuleiro=new Tabuleiro(this);

        jogador1=new Jogador(this,1);
        jogador2=new Jogador(this,2);
        
        
    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
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
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks; 
        double delta = 0; 
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running){ 
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();  
                delta--;
            }
            if(running){
            render();
            }
            frames++;

            if(System.currentTimeMillis() - timer > 1000 ){
                timer+= 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick(){

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

        HomeWindow home = new HomeWindow();
		home.showPage("home", g);
        
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
}
