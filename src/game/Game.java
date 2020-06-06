package game;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;

import tabuleiro.Tile;


public class Game extends Canvas implements Runnable{
    
    private static final long serialVersionUID = 7059646278559620203L;

    public static final int WIDTH = 1920, HEIGHT = 1080;  //1024*576
    
    private Thread thread;
    private GUI gui;
    private boolean running = false;
    private Tile[] tiles=new Tile[1];
    public Game(){
    	
        new Window(WIDTH, HEIGHT, "MediumWar", this);
        gui=new GUI();
<<<<<<< refs/remotes/origin/Felipe
        int[] vet= {0,0};
        tiles[0]=new Tile(gui,vet);
=======
        banco=new Banco(gui);
>>>>>>> Atualzando Banco
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

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs==null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        if(gui!=null)gui.paint(g);
        for(int i=0;i<tiles.length;i++) {
        	if(tiles[i]!=null) {
        		Image img=tiles[i].getImage();
        		if(tiles[i]!=null)tiles[i].paint(g,img);
        	}
        }
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.dispose();
        bs.show();
    }

    public static void main(String[] args) {
        new Game();

    }

}
