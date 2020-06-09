package peca;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import card.Card;
import game.GUI;
import tabuleiro.Tile;

public class Orc extends Peca{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5644930813218984359L;
	static private String[] imagens= {"assets/orc0.png","assets/orc1.png","assets/orc2.png","assets/orc3.png","assets/orc4.png","assets/orc5.png"};
	
	public Orc(GUI gui) {
		super(gui);
		setup(4);
	}
	public Orc(Peca peca,Card card) {
		super(peca,card);
	}
	public void setup(int x) {
		animationFramesMove = new Image[3];
		animationFramesAttack = new Image[3];
		for(int i=0;i<6;i++){
			if(i<3)
				animationFramesMove[i]=adjustScale(imagens[i],x);
			else
				animationFramesAttack[i-3]=adjustScale(imagens[i],x);
		}
		currentFrame = 0;
		currentAnimation = animationFramesMove;
	}
	
	public Orc(Peca peca,Tile tile){
		super(peca, tile);
		setup(2);	
	}
	/*
	//Para inserir a imagem dentro do card na posi��o correta
	public void paintCard(Graphics g,int positionX,int positionY) {
		paintComponent(g,positionX+applyScale(14),positionY+applyScale(13));
	}
	//Para inserir a imagem dentro do tile na posi��o correta
	public void paintTile(Graphics g,int positionX,int positionY) {
		paintComponent(g,positionX+applyScale(7),positionY+applyScale(3));
	}
	*/
}
