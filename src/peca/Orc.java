package peca;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

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
		setup();
	}
	public void setup() {
		animationFramesMove = new Image[3];
		animationFramesAttack = new Image[3];
		for(int i=0;i<6;i++){
			if(i<3)
				animationFramesMove[i]=adjustScale(imagens[i]);
			else
				animationFramesAttack[i-3]=adjustScale(imagens[i]);
		}
		currentFrame = 0;
		currentAnimation = animationFramesMove;
	}
	//Para inserir a imagem dentro do card na posição correta
	public void paintCard(Graphics g,int positionX,int positionY) {
		paintComponent(g,positionX+applyScale(14),positionY+applyScale(13));
	}
	//Para inserir a imagem dentro do tile na posição correta
	public void paintTile(Graphics g,int positionX,int positionY) {
		paintComponent(g,positionX+applyScale(7),positionY+applyScale(3));
	}
}
