package banco;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import card.Card;
import card.ICardBanco;
import peca.Archer;
import peca.IPecaCard;
import peca.IPecaCardBanco;
import peca.Knight;
import peca.Orc;
import peca.Wizard;

public class CardBanco extends Card implements ICardBanco{
	protected IPecaCardBanco peca=null;
	private static final long serialVersionUID = 3151322956375756610L;
	static private String[] refImagens = {"assets/cardPadrao.png","assets/cardAmbos.png","assets/cardAzul.png","assets/cardVermelho.png"};
    private Image imgCoin;
	
	public CardBanco(IBancoCard banco, int i){
		super(banco, refImagens[i]);
		ImageIcon refCoin=new ImageIcon("assets/coin.png");
		imgCoin=refCoin.getImage();
    	imgCoin=imgCoin.getScaledInstance((int)(scale*imgCoin.getWidth(null)), (int)(scale*imgCoin.getHeight(null)), Image.SCALE_DEFAULT);    	
    } 

	public void setCardAtual(String cor) {
		 attImage(cor);
	}
	public void paintComponent(Graphics g, int positionX, int positionY){
		super.paintComponent(g, positionX, positionY);
		if(imgCoin!=null)g.drawImage(imgCoin,(int)(positionX+(getWidth()/2)-imgCoin.getWidth(null)),(int)(positionY+getHeight()-imgCoin.getHeight(null)-5), this);	
		g.setFont(new Font("Arial",1, 30));
		g.drawString(String.valueOf(peca.getPurchaseValue()), positionX+(getWidth()/2)+10,(int)(positionY+getHeight())-10);
	}
	public IPecaCardBanco getPeca() {
		return peca;
	}
	@Override
	public void setPeca(IPecaCard peca) {
		if (peca instanceof Archer)this.peca=new Archer(peca,this);
		else if (peca instanceof Knight)this.peca=new Knight(peca,this);
		else if (peca instanceof Orc)this.peca=new Orc(peca,this);
		else if (peca instanceof Wizard)this.peca=new Wizard(peca,this);
	}
}
