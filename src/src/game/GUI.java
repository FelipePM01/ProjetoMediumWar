package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GUI extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7022854761987105962L;
	private Image background;
	private static double scale=1.0;

	public GUI() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		ImageIcon refBackground=new ImageIcon("assets/background.png");
		background=refBackground.getImage();
		Dimension d1=new Dimension(background.getWidth(null),background.getHeight(null));
		Dimension correct=getScaledDimension(d1, screenSize);
		background=background.getScaledInstance(correct.width,correct.height, Image.SCALE_DEFAULT);
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, this);
	}
	public static Dimension getScaledDimension(Dimension imgSize, Dimension boundary) {
	    int original_width = imgSize.width;
	    int original_height = imgSize.height;
	    int bound_width = boundary.width; 
	    int new_width = original_width;
	    int new_height = original_height;

        new_width = bound_width;        
        new_height = (new_width * original_height) / original_width;
		scale = ((double)new_height/(double)original_height);
	    return new Dimension(new_width, new_height);
	}
	public double getScale() {
		return scale;
	}
}
