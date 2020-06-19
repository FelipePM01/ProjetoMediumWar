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
	private IJogadorCard jogador;
	
	static private String[] refImagens = {"assets/cardPadrao.png","assets/cardAzul.png","assets/cardVermelho.png"};
	
	public CardJogador(IJogadorCard jogador, int i){
		super(jogador, refImagens[0]);
		this.jogador=jogador;
	}
	
	public void setCardAtual(String cor) {
		 attImage(cor);

	}


	@Override
	public void setPeca(IPecaCard peca) {
		if (peca instanceof Archer)this.peca=new Archer(peca,this,jogador);
		else if (peca instanceof Knight)this.peca=new Knight(peca,this,jogador);
		else if (peca instanceof Orc)this.peca=new Orc(peca,this,jogador);
		else if(peca==null)this.peca=null;
	}

	public Peca getPeca() {
    	return peca;
    }

	
}
