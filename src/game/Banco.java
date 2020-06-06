package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.awt.FlowLayout;
import java.awt.Image;

public class Banco extends JPanel{
    
    private JPanel bancogui;
    private Image card;
    private double scale;

    public Banco(GUI gui){
        scale = gui.getScale();
        initializeGui();
    }  

    public void initializeGui(){
        ImageIcon refcard=new ImageIcon("assets/card.png");
        card=refcard.getImage();
        card = card.getScaledInstance(card.getWidth(null), card.getHeight(null), Image.SCALE_DEFAULT);
    }
    public void paint(Graphics g){
        Graphics2D graficos= (Graphics2D) g;
        //graficos.drawImage(card, (int)(scale)*356, (int)(scale)*16, card.getWidth(null), card.getHeight(null), this);
        graficos.drawImage(card, (int)scale*356, (int)scale*16, 500, 500, this);
        //graficos.drawImage(card, 0, 0, null);
        //graficos.fillRect(card.getWidth(null), card.getHeight(null), 80, 80);
        graficos.dispose();
	}
    
}