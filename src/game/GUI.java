package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7022854761987105962L;
	private Image background;
	
	public GUI() {
		ImageIcon refBackground=new ImageIcon("assets\\background.png");
		background=refBackground.getImage();
		
	}
	public void paint(Graphics g) {
		Graphics2D graficos= (Graphics2D) g;
		graficos.drawImage(background, 0, 0, null);
		g.dispose();
	}

}
