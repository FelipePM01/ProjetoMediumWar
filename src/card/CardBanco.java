package card;

import java.awt.Image;

import game.GUI;

public class CardBanco extends Card{
	static private Image cardVermelho;
    static private Image cardAzul;
    static private Image cardAmbos;
    private String refImagem;
    
    
    public CardBanco(GUI gui){
    	super(gui, refImagem);   	
    }
    public void paintCardBanco() {
    	 super.initializeGui(refImagem);
    }
    	
    
    
    
}
