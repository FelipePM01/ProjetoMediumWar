package card;

import java.awt.Graphics; 
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Jogador.IJogadorCard;
import banco.IBancoCard;
import peca.IPecaCard;


public abstract class Card extends JPanel implements ICardPeca{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6677974077714234653L;
	private Image img, imgpeca, cardPadrao, cardAzul, cardVermelho, cardAmbos;
	private int width;
	private int height;
	private int[] startPointCard = new int[2];
	protected double scale;

    public Card(IBancoCard banco, String refImagem){
        scale = banco.getScale();
        initializeGui(refImagem);
    }
    public Card(IJogadorCard jogador, String refImagem){
        scale = jogador.getScale();
        initializeGui(refImagem);
    }

    public void initializeGui(String refimag){
    	ImageIcon refimg=new ImageIcon(refimag);        
        img=refimg.getImage();
        width = (int)(scale*img.getWidth(null));
        height = (int)(scale*img.getHeight(null));
        img=img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
      
    	ImageIcon refCardPadrao=new ImageIcon("assets/cardPadrao.png");
    	cardPadrao=refCardPadrao.getImage();
    	cardPadrao=cardPadrao.getScaledInstance((int)(scale*cardPadrao.getWidth(null)), (int)(scale*cardPadrao.getHeight(null)), Image.SCALE_DEFAULT);
    	
    	ImageIcon refCardAzul=new ImageIcon("assets/cardAzul.png");
    	cardAzul=refCardAzul.getImage();
    	cardAzul=cardAzul.getScaledInstance((int)(scale*cardAzul.getWidth(null)), (int)(scale*cardAzul.getHeight(null)), Image.SCALE_DEFAULT);
    	
    	ImageIcon refCardVermelho=new ImageIcon("assets/cardVermelho.png");
    	cardVermelho=refCardVermelho.getImage();
    	cardVermelho=cardVermelho.getScaledInstance((int)(scale*cardVermelho.getWidth(null)), (int)(scale*cardVermelho.getHeight(null)), Image.SCALE_DEFAULT);
    	
    	ImageIcon refCardAmbos=new ImageIcon("assets/cardAmbos.png");
    	cardAmbos=refCardAmbos.getImage();
    	cardAmbos=cardAmbos.getScaledInstance((int)(scale*cardAmbos.getWidth(null)), (int)(scale*cardAmbos.getHeight(null)), Image.SCALE_DEFAULT);
    }
    public void initializeGuiPeca(String refimag){
        ImageIcon refimg=new ImageIcon(refimag);        
        imgpeca=refimg.getImage();
        imgpeca=imgpeca.getScaledInstance((int)(4*scale*imgpeca.getWidth(null)), (int)(4*scale*imgpeca.getHeight(null)), Image.SCALE_DEFAULT);
    }
    public void paintComponent(Graphics g, int positionX, int positionY){
    	setOpaque(false);
    	if(img!=null)g.drawImage(img, positionX, positionY, this);
    	startPointCard[0]=positionX;
    	startPointCard[1]=positionY;
    	if(getPeca()!=null&&positionY<300)getPeca().paintComponent(g, 0, -20);
    	else if(getPeca()!=null)getPeca().paintComponent(g);
	}
    public int[] getGUIPosition(){
    	return startPointCard;
    }
    public int getWidth() {
    	return width;
    }
    public int getHeight() {
    	return height;
    }
    public boolean ehNulo() {
    	boolean retorno=false;
    	if(getPeca()==null)retorno=true;
    	return retorno;
    }
    public void attImage(String refImgAtual) {
    	switch(refImgAtual){
		  case "ambos":
			  img=cardAmbos;
			  break;
		  case "azul":
			  img=cardAzul;
			  break;
		  case "vermelho":
			  img=cardVermelho;
			  break;
		  case "padrao":
			  img=cardPadrao;
			  break;
		  }
    }
    protected abstract <t extends IPecaCard> t getPeca();   
}
