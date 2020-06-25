package Jogador;

import java.awt.Image;

import card.Card;
import card.ICardJogador;
import peca.Archer;
import peca.ICardJogadorPeca;
import peca.IPecaCard;
import peca.IPecaCardJogador;
import peca.Knight;
import peca.Orc;
import peca.Peca;
import peca.Wizard;

public class CardJogador extends Card implements ICardJogador,ICardJogadorPeca{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1679685899782569774L;
	private Image cardAtual;
	private Image cardPadrao;
	private Image cardVermelho;
	private Image cardAzul;
	private IJogadorCard jogador;
	private boolean naoColocado=true;
	
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
		if (peca instanceof Archer)this.peca=new Archer(peca,this);
		else if (peca instanceof Knight)this.peca=new Knight(peca,this);
		else if (peca instanceof Orc)this.peca=new Orc(peca,this);
		else if (peca instanceof Wizard)this.peca=new Wizard(peca,this);
		else if(peca==null)this.peca=null;
	}

	public Peca getPeca() {
    	return peca;
    }

	public IJogadorCard getJogador() {
		return jogador;
	}
	public boolean getNaoColocado() {
		return naoColocado;
	}
	public void setNaoColocado(boolean naoColocado) {
		this.naoColocado=naoColocado;
	}

	public void recompensar(int value) {
		jogador.addCash(value);
		
	}

	
}
