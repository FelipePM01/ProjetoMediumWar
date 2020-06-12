package peca;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import card.Card;
import game.GUI;
import game.Game;
import game.IGame;
import tabuleiro.Tabuleiro;
import tabuleiro.Tile;

public abstract class Peca extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3059669044498752498L;
	public static Tabuleiro tabuleiro;
	protected Image[] animationFramesMove;
	protected Image[] animationFramesAttack;
	protected Tile tile=null;
	protected Card card=null;
	protected boolean inBoard;
	protected int[] correction = {0,0};
	protected Image[] currentAnimation;
	protected int currentFrame;
	protected GUI gui;
	protected double scale;
	protected double[] translation={0.0,0.0};
	protected int[] basePosition = {0,0};
	protected String currentAction=null;
	protected Tile moveTarget=null;
	protected double speed;
	protected int[] direction= {0,0};
	protected int baseMoveAnimDuration;
	protected int frameCounter=0;
	
	public Peca(Peca peca,Tile tile) {
		set(peca);
		this.tile=tile;
		inBoard=true;
		basePosition=tile.getGUIPosition();
	}
	public Peca(Peca peca,Card card) {
		set(peca);
		this.card=card;
		
		inBoard=false;
		basePosition=card.getGUIPosition();
	}
	public void paintComponent(Graphics g, int positionX, int positionY) {
		super.paintComponent(g);
		if(currentAnimation!=null&&currentAnimation[currentFrame]!=null)g.drawImage(currentAnimation[currentFrame], basePosition[0]+(int)(scale*correction[0])+(int)(translation[0]*scale), basePosition[1]+(int)(scale*correction[1])+(int)(translation[1]*scale), this);
	}
	public void set(Peca peca) {//cria uma peca que eh uma copia de outra ja existente
		this.animationFramesAttack=peca.animationFramesAttack;
		this.animationFramesMove=peca.animationFramesMove;
		this.currentAnimation=peca.currentAnimation;
		this.currentFrame=0;
		this.scale=peca.scale;
		ActionListener taskPerformer = new ActionListener() {
		      public void actionPerformed(ActionEvent evt) {
		          tick();
		      }
		  };
		  new Timer(1, taskPerformer).start();
		
	}
	public Peca(IGame game) {
		//this.gui=gui;
		scale=game.getScale();
	}
	
	public Image adjustScale(String refImg, int x){
		
        ImageIcon refimg=new ImageIcon(refImg);
        Image img=refimg.getImage();
        //Escala de acordo com o card
        return img.getScaledInstance((int)(img.getWidth(null)*scale*x),(int)(img.getHeight(null)*scale*x),Image.SCALE_DEFAULT);
    }
	public int applyScale(int x) {
		return (int)(x*scale); 
	}
	protected void tick() {
		if(currentAction=="moving") {
			frameCounter+=1;
			if (frameCounter>(double)animationFramesMove.length/baseMoveAnimDuration*speed) {
				if(currentFrame==animationFramesMove.length)currentFrame=0;
				else currentFrame++;
			}
			if(direction[0]!=0) {
				translation[0]+=direction[0]*speed/1000;
			}
			else if(direction[1]!=0){
				translation[1]+=direction[1]*speed/1000;
			}
			if(Math.abs(translation[0])>=tile.getImage().getWidth(null)||Math.abs(translation[1])>=tile.getImage().getHeight(null)){
				tile.setPeca(null);
				moveTarget.setPeca(this);
				translation[0]=0;
				translation[1]=0;
				currentAction=null;
				direction[0]=0;
				direction[1]=0;
				moveOrAttack();
				
			}
		}
	}
	public Tile getTile() {
		return tile;
	}
	public void moveOrAttack() {
		currentAction="moving";
		Random random=new Random();
		direction[random.nextInt(1)]=random.nextInt(1)==0?-1:1;
		moveTarget=tabuleiro.getTiles()[tile.getPosition()[0]+direction[0]][tile.getPosition()[1]+direction[1]];
		
	}
}
