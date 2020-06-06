package game;

import java.awt.Graphics; 
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Banco extends JPanel{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 5181648379208411552L;
    private Image card;
    private double scale;

    public Banco(GUI gui){
        scale = gui.getScale();
        initializeGui();
    }  

    public void initializeGui(){
        ImageIcon refcard=new ImageIcon("assets/card.png");        
        card=refcard.getImage();
        int WIDTH = (int)(card.getWidth(null)*scale);
        int HEIGHT = (int)(card.getWidth(null)*scale);
        card=card.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_DEFAULT);
    }
    public void paint(Graphics g){
        Graphics2D graficos= (Graphics2D) g;
        graficos.drawImage(card, (int)scale*356, (int)scale*16, this);
        graficos.dispose();
	}
    
}