package banco;

import card.Card;
import peca.Archer;
import peca.IPecaCard;
import peca.Knight;
import peca.Orc;

public class CardBanco extends Card{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3151322956375756610L;
	static private String[] refImagens = {"assets/cardPadrao.png","assets/cardAmbos.png","assets/cardAzul.png","assets/cardVermelho.png"};
    
	public CardBanco(IBancoCard banco, int i){
		super(banco, refImagens[i]);
    } 
	@Override
	public void setPeca(IPecaCard peca) {
		if (peca instanceof Archer)this.peca=new Archer(peca,this);
		else if (peca instanceof Knight)this.peca=new Knight(peca,this);
		else if (peca instanceof Orc)this.peca=new Orc(peca,this);
		
	}
}
