package peca;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import Jogador.CardJogador;
import Jogador.Jogador;
import banco.CardBanco;
import card.Card;
import card.ICardBanco;
import card.ICardPeca;
import game.GUI;
import tabuleiro.ITilePeca;
import tabuleiro.Tabuleiro;
import tabuleiro.Tile;

public abstract class Peca extends JPanel implements IPecaCard, IPecaTile{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3059669044498752498L;
	public static Tabuleiro tabuleiro;
	protected Image[] animationFramesMove;
	protected Image[] animationFramesAttack;
	protected ITilePeca tile=null;
	protected boolean inBoard;
	protected int[] correction = {0,0};
	protected Image[] currentAnimation;
	protected int currentFrame;
	protected GUI gui;
	protected double scale;
	protected double[] translation={0.0,0.0};
	protected int[] basePosition = {0,0};
	protected String currentAction=null;
	protected ITilePeca moveTarget=null;
	protected double speed;;
	protected int[] direction= {0,0};
	protected int baseMoveAnimDuration;
	protected int frameCounter=0;
	protected boolean flipped=false;
	protected Timer timer;
	protected int flipCorrection;
	protected double life;
	protected double endurance;
	public Jogador jogador=null;
	
	protected int baseAttackAnimDuration;
	protected double attackSpeed;
	protected double attackDamage;
	protected IPecaTile attackTarget;
	protected double alcance;
	public Peca(IPecaCardJogador peca,Tile tile) {
		set(peca);
		this.tile=tile;
		inBoard=true;
		basePosition=tile.getGUIPosition();
	}
	public Peca(IPeca peca,ICardBanco card) {
		set(peca);		
		inBoard=false;
		basePosition=card.getGUIPosition();
	}
	public Peca(IPecaCardBanco peca,CardJogador card) {
		set(peca);		
		inBoard=false;
		basePosition=card.getGUIPosition();
	}
	
	public void paintComponent(Graphics g, int positionX, int positionY) {
		super.paintComponent(g);
		if(currentAnimation!=null&&currentAnimation[currentFrame]!=null&&!flipped)g.drawImage(currentAnimation[currentFrame], (basePosition[0]+(int)(scale*correction[0])+(int)(translation[0])), (basePosition[1]+(int)(scale*correction[1])+(int)(translation[1])),null);
		else if(currentAnimation!=null&&currentAnimation[currentFrame]!=null)g.drawImage(currentAnimation[currentFrame], (basePosition[0]+(int)(scale*correction[0])+(int)(translation[0]))+currentAnimation[currentFrame].getWidth(null), (basePosition[1]+(int)(scale*correction[1])+(int)(translation[1])),-currentAnimation[currentFrame].getWidth(null),currentAnimation[currentFrame].getHeight(null),null);
	}
	//cria uma peca que eh uma copia de outra ja existente
	public void set(IPeca peca) {
		this.animationFramesAttack=peca.getAnimationFramesAttack();
		this.animationFramesMove=peca.getAnimationFramesMove();
		this.currentAnimation=peca.getCurrentAnimation();
		this.currentFrame=0;
		this.scale=peca.getScale();
		this.baseMoveAnimDuration=peca.getBaseMoveAnimDuration();
		this.speed=peca.getSpeed();
		this.life=peca.getLife();
		this.endurance=peca.getEndurance();
		this.baseAttackAnimDuration=peca.getBaseAttackAnimDuration();
		this.attackSpeed=peca.getAttackSpeed();
		this.attackDamage=peca.getAttackDamage();
		this.alcance=peca.getAlcance();
	
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				tick();
		    }
		};
		timer=new Timer(1, taskPerformer);
		timer.start();	
	}
	public Image adjustScale(String refImg, int x){
		ImageIcon refimg=new ImageIcon(refImg);
        Image img=refimg.getImage();
        return img.getScaledInstance((int)(img.getWidth(null)*scale*x),(int)(img.getHeight(null)*scale*x),Image.SCALE_DEFAULT);
    }
	void tick() {
		if(currentAction=="moving") move();
		else if(currentAction=="attacking")attack();
	}

	protected void move() {

		frameCounter+=1;
		if (frameCounter>=(double)baseMoveAnimDuration/speed/animationFramesMove.length) {
			
			if(currentFrame==animationFramesMove.length-1)currentFrame=0;
			else currentFrame++;
			
			frameCounter=0;
		}
		if(direction[0]!=0) {
			
			translation[0]+=direction[0]*speed*tile.getImage().getWidth(null)/1000;
			if(direction[0]==-1 ) {
				if(!flipped) {
					flipped=true;
					correction[0]-=flipCorrection;
				}
			}
			else if(flipped){
				flipped=false;
				correction[0]+=flipCorrection;
			}
		}
		else if(direction[1]!=0){
			
			translation[1]+=direction[1]*speed*tile.getImage().getWidth(null)/1000;	
		}
		if(Math.abs(translation[0])>=tile.getImage().getWidth(null)||Math.abs(translation[1])>=tile.getImage().getHeight(null)){

			tile.setNull();
			moveTarget.setPeca(this);
			tile=moveTarget;
			moveTarget=null;
			translation[0]=0;
			translation[1]=0;
			basePosition=tile.getGUIPosition();
			currentAction=null;
			direction[0]=0;
			direction[1]=0;
			moveOrAttack();		
		}
	}
	public void flip(){
		flipped=true;
        correction[0]-=flipCorrection;
	}
	protected abstract void attack();
	protected abstract double[] getCenterPosition();
	
	public void moveOrAttack() {
		ITilePeca[][] matriz=tile.getOtherTiles();
		double dist=0;
		ITilePeca alvo=null;
		for(int i=0;i<matriz.length;i++) {
			for(int j=0;j<matriz[0].length;j++) {
				if(matriz[i][j].existsPeca()&&matriz[i][j].getPeca().getJogador()!=this.getJogador()&&Tile.dist(matriz[i][j], tile)>dist) {
					dist=Tile.dist(matriz[i][j], tile);
					alvo=matriz[i][j];
				}
			}
		}
		if(dist<=alcance) {
			currentAction="attacking";
			moveTarget=null;
			attackTarget=alvo.getPeca();
		}
	}
	
	public void receberDano(double dano) {
		life=life-(dano-(dano*(endurance/100)));

		if(life<0) {
			inBoard=false;
			tile.clearTile();
		}
	}
	
	public Peca(double scale) {
		this.scale=scale;
	}
	public ITilePeca getTile() {
		return tile;
	}
	public void setTarget(Tile tile) {
		moveTarget=tile;
	}
	public Image[] getAnimationFramesAttack() {
		return animationFramesAttack;
	}
	public Image[] getAnimationFramesMove() {
		return animationFramesMove;
	}
	public Image[] getCurrentAnimation(){
		return currentAnimation;
	}
	public double getScale() {
		return scale;
	}
	public int getBaseMoveAnimDuration() {
		return baseMoveAnimDuration;
	}
	public boolean getInBoard() {
		return inBoard;
	}
	public void setLife(double life) {
		this.life=life;
	}
	public double getLife() {
		return life;
	}
	public void setEndurance(double endurance) {
		this.endurance=endurance;
	}
	public double getEndurance() {
		return endurance;
	}
	public double getSpeed() {
		return speed;
	}
	public double getAttackSpeed() {
		return attackSpeed;
	}
	public double getAttackDamage() {
		return attackDamage;
	}
	public int getBaseAttackAnimDuration() {
		return baseAttackAnimDuration;
	}
	public double getAlcance() {
		return alcance;
	}
	public double[] getCenterPosition(int x, int y){
		double[] centerPosition=new double[2];
		if(currentAnimation!=null&&currentAnimation[currentFrame]!=null&&!flipped) {
			centerPosition[0]=(basePosition[0]+(scale*correction[0])+(translation[0])+(scale*x));
			centerPosition[1]=(basePosition[1]+(scale*correction[1])+(translation[1])+(scale*y));
		}
		else if(currentAnimation!=null&&currentAnimation[currentFrame]!=null) {
			centerPosition[0]=(basePosition[0]+(scale*correction[0])+(translation[0])+currentAnimation[currentFrame].getWidth(null)+(scale*x));
			centerPosition[1]=(basePosition[1]+(scale*correction[1])+(translation[1])-currentAnimation[currentFrame].getWidth(null)+(scale*y));
		}
		return centerPosition;
	}
	public Jogador getJogador() {
		return jogador;
	}
}
