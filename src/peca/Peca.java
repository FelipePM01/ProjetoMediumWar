package peca;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import Jogador.CardJogador;
import Jogador.IJogadorCard;
import card.ICardBanco;
import excecoes.*;

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
	protected CardJogador card;
	protected boolean inBoard;
	protected int[] correction = {0,0};
	protected Image[] currentAnimation;
	protected int currentFrame;
	protected GUI gui;
	protected double scale;
	protected static Peca marcado;
	protected double[] translation={0.0,0.0};
	protected int[] basePosition = {0,0};
	protected String currentAction=null;
	protected ITilePeca moveTarget=null;
	protected double speed;
	protected int[] direction= {0,0};
	protected int baseMoveAnimDuration;
	protected int frameCounter=0;
	protected boolean flipped=false;
	protected Timer timer;
	protected int flipCorrection;
	protected double life;
	protected double endurance;
	protected IPecaCardJogador origem;
	protected String cor=null;
	protected ArrayList<int[]> triedDirections=null;
	protected int[] lastPositionDirection=null;
	protected int purchaseValue;
	protected int saleValue;
	protected int giftValue;
	ArrayList<int[]> tried=new ArrayList<int[]>();
	protected int baseAttackAnimDuration;
	protected double attackSpeed;
	protected double attackDamage;
	protected IPecaTile attackTarget;
	protected double alcance;
	protected boolean esperando=false;
	protected int[] lastPosition=null;
	protected boolean morto=false;
	
	public Peca(IPecaCardJogador peca,Tile tile) {
		set(peca);
		this.tile=tile;
		inBoard=true;
		basePosition=tile.getGUIPosition();
		origem=peca;
		
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
		this.card=card;
		cor=card.getJogador().getCor();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(currentAnimation!=null&&currentAnimation[currentFrame]!=null&&!flipped)g.drawImage(currentAnimation[currentFrame], (basePosition[0]+(int)(scale*correction[0])+(int)(translation[0])), (basePosition[1]+(int)(scale*correction[1])+(int)(translation[1])),null);
		else if(currentAnimation!=null&&currentAnimation[currentFrame]!=null)g.drawImage(currentAnimation[currentFrame], (basePosition[0]+(int)(scale*correction[0])+(int)(translation[0]))+currentAnimation[currentFrame].getWidth(null), (basePosition[1]+(int)(scale*correction[1])+(int)(translation[1])),-currentAnimation[currentFrame].getWidth(null),currentAnimation[currentFrame].getHeight(null),null);
	}
	public void paintComponent(Graphics g, int positionX, int positionY) {
		super.paintComponent(g);
		if(currentAnimation!=null&&currentAnimation[currentFrame]!=null&&!flipped)g.drawImage(currentAnimation[currentFrame], (basePosition[0]+(int)(scale*correction[0])+(int)(translation[0]))+positionX, (basePosition[1]+(int)(scale*correction[1])+(int)(translation[1]))+positionY,null);
		else if(currentAnimation!=null&&currentAnimation[currentFrame]!=null)g.drawImage(currentAnimation[currentFrame], (basePosition[0]+(int)(scale*correction[0])+(int)(translation[0]))+currentAnimation[currentFrame].getWidth(null)+positionX, (basePosition[1]+(int)(scale*correction[1])+(int)(translation[1]))+positionY,-currentAnimation[currentFrame].getWidth(null),currentAnimation[currentFrame].getHeight(null),null);
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
		this.cor=peca.getCor();
		this.giftValue=peca.getGiftValue();
		this.purchaseValue=peca.getPurchaseValue();
		this.saleValue=peca.getSaleValue();
		this.giftValue=peca.getGiftValue();
	
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
		if(!morto) {
			if(esperando) {
				esperando=false;
				moveOrAttack();
				
				
			}
			else if(currentAction=="moving"&&moveTarget!=null) move();
			else if(currentAction=="attacking")attack();
		}
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
			moveTarget.setPeca(this);
			tile.setNull();
			
			tile=moveTarget;
			tile.setMarcado();
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
	public abstract double[] getCenterPosition();
	
	public void moveOrAttack() {
		
		ITilePeca[][] matriz=tile.getOtherTiles();
		double dist=100;
		ITilePeca alvo=null;
		for(int i=0;i<matriz.length;i++) {
			for(int j=0;j<matriz[0].length;j++) {
				if(matriz[i][j].existsPeca()&&matriz[i][j].getPeca().getCor()!=this.cor&&Tile.dist(matriz[i][j], tile)<dist) {
					dist=Tile.dist(matriz[i][j], tile);
					alvo=matriz[i][j];
				}
			}
		}
		if(dist<=alcance&&dist!=100) {
			currentAction="attacking";
			moveTarget=null;
			if(alvo!=null)attackTarget=alvo.getPeca();
			currentAnimation=animationFramesAttack;
			currentFrame=0;
		}
		else if(dist!=100) {
			
			
			try {
				direction=chooseDirection(alvo,tried);
				lastPosition=new int[2];
				lastPosition[0]=-direction[0];
				lastPosition[1]=-direction[1];
				
				moveTarget=tile.getOtherTiles()[tile.getPosition()[0]+direction[0]][tile.getPosition()[1]+direction[1]];
				
				attackTarget=null;
				tried=new ArrayList<int[]>();
				tried.add(lastPosition);
				currentAction="moving";
				currentAnimation=animationFramesMove;
				currentFrame=0;
				moveTarget.setMarcado();
			} catch (FormatoInvalido e) {
				System.out.println("formato invalido");
			}catch (MovimentoInvalido e) {
				
				
				if(tried.size()<4) {
					moveOrAttack();
					
					
				}
				else {
					
					esperando=true;
					tried=new ArrayList<int[]>();
					if(lastPosition!=null)tried.add(lastPosition);
				}
				
			}
			
		}
	}
	protected int[] chooseDirection(ITilePeca alvo,ArrayList<int[]> tried) throws MovimentoInvalido{
		if(alvo==tile)throw new FormatoInvalido();
		Tile[][] tabuleiro=tile.getOtherTiles();
		int[] position=tile.getPosition();
		int deltaX=Tile.distX(tile, alvo);
		int deltaY=Tile.distY(tile, alvo);
		int[] direction=new int[2];
		boolean pronto=false;
		if(Math.abs(deltaX)>Math.abs(deltaY) ){
			
			direction[0]=(deltaX!=0)?deltaX/Math.abs(deltaX):1;
			direction[1]=0;
			if(direction[0]+position[0]>=tabuleiro.length||direction[0]+position[0]<0) {
				boolean jaTestado=false;
				for(int i=0;i<tried.size();i++) {
					
					if(Arrays.equals(tried.get(i),direction))jaTestado=true;
				}
				if(!jaTestado) {
					tried.add(direction);
					
					throw new ForaDoTabuleiro();
				}
				System.out.println("");
			}
			else if(tabuleiro[direction[0]+position[0]][direction[1]+position[1]].existsPeca()||tabuleiro[direction[0]+position[0]][direction[1]+position[1]].getMarcado()) {
				boolean jaTestado=false;
				for(int i=0;i<tried.size();i++) {
					
					if(Arrays.equals(tried.get(i),direction))jaTestado=true;
					
				}
				if(!jaTestado) {
					tried.add(direction);
					
					throw new PosicaoOcupada();
					
				}
				
			}
			else {
				pronto=true;
			}
			if(!pronto) {
				
				direction[0]=0;
				direction[1]=(deltaY!=0)?deltaY/Math.abs(deltaY):1;
				if(direction[1]+position[1]>=tabuleiro.length||direction[1]+position[1]<0) {
					boolean jaTestado=false;
					for(int i=0;i<tried.size();i++) {
						
						if(Arrays.equals(tried.get(i),direction))jaTestado=true;
					}
					if(!jaTestado) {
						tried.add(direction);
						throw new ForaDoTabuleiro();
					}
				}
				else if(tabuleiro[direction[0]+position[0]][direction[1]+position[1]].existsPeca()||tabuleiro[direction[0]+position[0]][direction[1]+position[1]].getMarcado()) {
					boolean jaTestado=false;
					for(int i=0;i<tried.size();i++) {
						
						if(Arrays.equals(tried.get(i),direction))jaTestado=true;
					}
					if(!jaTestado) {
						tried.add(direction);
						throw new PosicaoOcupada();
					}
				}
				else {
					pronto=true;
				}
			}
			if(!pronto) {
				
				direction[0]=0;
				direction[1]=(deltaY!=0)?-deltaY/Math.abs(deltaY):-1;
				if(direction[1]+position[1]>=tabuleiro.length||direction[1]+position[1]<0) {
					boolean jaTestado=false;
					for(int i=0;i<tried.size();i++) {
						
						if(Arrays.equals(tried.get(i),direction))jaTestado=true;
					}
					if(!jaTestado) {
						tried.add(direction);
						throw new ForaDoTabuleiro();
					}
				}
				else if(tabuleiro[direction[0]+position[0]][direction[1]+position[1]].existsPeca()||tabuleiro[direction[0]+position[0]][direction[1]+position[1]].getMarcado()) {
					boolean jaTestado=false;
					for(int i=0;i<tried.size();i++) {
						
						if(Arrays.equals(tried.get(i),direction))jaTestado=true;
					}
					if(!jaTestado) {
						tried.add(direction);
						throw new PosicaoOcupada();
					}
				}
				else {
					pronto=true;
				}
			}
			if(!pronto) {
				
				direction[0]=(deltaX!=0)?-deltaX/Math.abs(deltaX):-1;
				direction[1]=0;
				if(direction[0]+position[0]>=tabuleiro.length||direction[0]+position[0]<0) {
					boolean jaTestado=false;
					for(int i=0;i<tried.size();i++) {
						
						if(Arrays.equals(tried.get(i),direction))jaTestado=true;
					}
					if(!jaTestado) {
						tried.add(direction);
						throw new ForaDoTabuleiro();
					}
				}
				else if(tabuleiro[direction[0]+position[0]][direction[1]+position[1]].existsPeca()||tabuleiro[direction[0]+position[0]][direction[1]+position[1]].getMarcado()) {
					boolean jaTestado=false;
					for(int i=0;i<tried.size();i++) {
						
						if(Arrays.equals(tried.get(i),direction))jaTestado=true;
					}
					if(!jaTestado) {
						tried.add(direction);
						throw new PosicaoOcupada();
					}
					
				}
				else {
					pronto=true;
				}
			}
		}
		else {
			direction[0]=0;
			direction[1]=(deltaY!=0)?deltaY/Math.abs(deltaY):1;
			if(direction[1]+position[1]>=tabuleiro.length||direction[1]+position[1]<0) {
				boolean jaTestado=false;
				for(int i=0;i<tried.size();i++) {
					
					if(Arrays.equals(tried.get(i),direction))jaTestado=true;
				}
				if(!jaTestado) {
					tried.add(direction);
					
					throw new ForaDoTabuleiro();
				}
				
			}
			else if(tabuleiro[direction[0]+position[0]][direction[1]+position[1]].existsPeca()||tabuleiro[direction[0]+position[0]][direction[1]+position[1]].getMarcado()) {
				boolean jaTestado=false;
				for(int i=0;i<tried.size();i++) {
					
					if(Arrays.equals(tried.get(i),direction))jaTestado=true;
				}
				if(!jaTestado) {
					tried.add(direction);
					
					throw new PosicaoOcupada();
				}
				
			}
			else {
				pronto=true;
			}
			if(!pronto) {
				
				direction[0]=(deltaX!=0)?deltaX/Math.abs(deltaX):1;
				direction[1]=0;
				if(direction[0]+position[0]>=tabuleiro.length||direction[0]+position[0]<0) {
					boolean jaTestado=false;
					for(int i=0;i<tried.size();i++) {
						
						if(Arrays.equals(tried.get(i),direction))jaTestado=true;
					}
					if(!jaTestado) {
						tried.add(direction);
						throw new ForaDoTabuleiro();
					}
				}
				else if(tabuleiro[direction[0]+position[0]][direction[1]+position[1]].existsPeca()||tabuleiro[direction[0]+position[0]][direction[1]+position[1]].getMarcado()) {
					boolean jaTestado=false;
					for(int i=0;i<tried.size();i++) {
						
						if(Arrays.equals(tried.get(i),direction))jaTestado=true;
					}
					if(!jaTestado) {
						tried.add(direction);
						throw new PosicaoOcupada();
					}
				}
				else {
					pronto=true;
				}
			}
			if(!pronto) {
				
				direction[0]=(deltaX!=0)?-deltaX/Math.abs(deltaX):-1;
				direction[1]=0;
				if(direction[0]+position[0]>=tabuleiro.length||direction[0]+position[0]<0) {
					boolean jaTestado=false;
					for(int i=0;i<tried.size();i++) {
						
						if(Arrays.equals(tried.get(i),direction))jaTestado=true;
					}
					if(!jaTestado) {
						tried.add(direction);
						throw new ForaDoTabuleiro();
					}
				}
				else if(tabuleiro[direction[0]+position[0]][direction[1]+position[1]].existsPeca()||tabuleiro[direction[0]+position[0]][direction[1]+position[1]].getMarcado()) {
					boolean jaTestado=false;
					for(int i=0;i<tried.size();i++) {
						
						if(Arrays.equals(tried.get(i),direction))jaTestado=true;
					}
					if(!jaTestado) {
						tried.add(direction);
						throw new PosicaoOcupada();
					}
				}
				else {
					pronto=true;
				}
			}
			if(!pronto) {
				
				direction[0]=0;
				direction[1]=(deltaY!=0)?-deltaY/Math.abs(deltaY):-1;
				if(direction[1]+position[1]>=tabuleiro.length||direction[1]+position[1]<0) {
					boolean jaTestado=false;
					for(int i=0;i<tried.size();i++) {
						
						if(Arrays.equals(tried.get(i),direction))jaTestado=true;
					}
					if(!jaTestado) {
						tried.add(direction);
						throw new ForaDoTabuleiro();
					}
				}
				else if(tabuleiro[direction[0]+position[0]][direction[1]+position[1]].existsPeca()||tabuleiro[direction[0]+position[0]][direction[1]+position[1]].getMarcado()) {
					boolean jaTestado=false;
					for(int i=0;i<tried.size();i++) {
						
						if(Arrays.equals(tried.get(i),direction))jaTestado=true;
					}
					if(!jaTestado) {
						tried.add(direction);
						throw new PosicaoOcupada();
					}
				}
				else {
					pronto=true;
				}
			}
		}
		
		return direction;
	}
	
	public void receberDano(double dano) {
		life=life-(dano-(dano*(endurance/100)));

		if(life<0) {
			morto=true;
			tile.clearTile();
			inBoard=false;
		}
		if(currentAction=="moving")moveOrAttack();
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
	public void setTargetNull() {
		moveTarget=null;
		
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
			centerPosition[0]=(basePosition[0]+(scale*correction[0])+(translation[0])+(scale*currentAnimation[currentFrame].getWidth(null)/2)-(scale*x));
			centerPosition[1]=(basePosition[1]+(scale*correction[1])+(translation[1])+(scale*currentAnimation[currentFrame].getWidth(null)/2)-(scale*y));
		}
		return centerPosition;
	}
	public IJogadorCard getJogador() {
		 IJogadorCard jogador=null;
		 if(card!=null)jogador=card.getJogador();
		return jogador;
	}
	public IPecaCardJogador getOrigem() {
		return origem;
	}
	public String getCor() {
		return cor;
	}
	public int getPurchaseValue() {
		return purchaseValue;
	}
	public int getSaleValue() {
		return saleValue;
	}
	public int getGiftValue() {
		return giftValue;
	}
	public boolean getMorto() {
		return morto;
	}
}
