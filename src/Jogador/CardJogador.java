package Jogador;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import card.Card;
import card.ICardJogador;
import game.GUI;
import game.Game;
import game.IGame;
import peca.Peca;

public class CardJogador extends Card implements ICardJogador{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1679685899782569774L;
	
	static private String[] refImagens = {"assets/cardPadrao.png","assets/cardAzul.png","assets/cardVermelho.png"};
	//static private String[] refPeca = {"assets/archer0.png", "assets/knight0.png","assets/orc0.png"};
	
	public CardJogador(IJogadorCard jogador, int i){
		super(jogador, refImagens[0]);
	}
	
	
}
