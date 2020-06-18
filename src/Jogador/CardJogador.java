package Jogador;

import java.awt.Image;

import card.Card;
import card.ICardJogador;
import peca.Archer;
import peca.IPecaCard;
import peca.IPecaCardJogador;
import peca.Knight;
import peca.Orc;
import peca.Peca;

public class CardJogador extends Card implements ICardJogador{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1679685899782569774L;
	private Image cardAtual;
	private Image cardPadrao;
	private Image cardVermelho;
	private Image cardAzul;
	
	static private String[] refImagens = {"assets/cardPadrao.png","assets/cardAzul.png","assets/cardVermelho.png"};
	
	public CardJogador(IJogadorCard jogador, int i){
		
		super(jogador, refImagens[0]);
	}
	public void setCardAtual(String cor) {
		  switch(cor){
		  case "azul":
			  attImage(refImagens[1]);
			  break;
		  case "vermelho":
			  attImage(refImagens[2]);
			  break;
		  case "padrao":
			  attImage(refImagens[0]);
			  break;
		  }
	}


	@Override
	public void setPeca(IPecaCard peca) {
		if (peca instanceof Archer)this.peca=new Archer(peca,this);
		else if (peca instanceof Knight)this.peca=new Knight(peca,this);
		else if (peca instanceof Orc)this.peca=new Orc(peca,this);
		
	}

	

	
}
