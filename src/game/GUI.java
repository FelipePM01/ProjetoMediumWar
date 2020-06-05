package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
//import java.awt.image.BufferedImage;
//import java.io.File;

//import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GUI extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7022854761987105962L;
	private Image background;
	private Window window;
	private static double scale;

	public GUI() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		ImageIcon refBackground=new ImageIcon("assets/background.png");
		background=refBackground.getImage();
		Dimension d1=new Dimension(background.getWidth(null),background.getHeight(null));
		Dimension correct=getScaledDimension(d1, screenSize);
		background=background.getScaledInstance(correct.width,correct.height, Image.SCALE_DEFAULT);
		Dimension d2=new Dimension(background.getWidth(null),background.getHeight(null));
	}
	public void paint(Graphics g) {
		Graphics2D graficos= (Graphics2D) g;
		graficos.drawImage(background, 0, 0, this);
		g.dispose();
	}
	public static Dimension getScaledDimension(Dimension imgSize, Dimension boundary) {

	    int original_width = imgSize.width;
	    int original_height = imgSize.height;
	    int bound_width = boundary.width;
	    int bound_height = boundary.height;
	    int new_width = original_width;
	    int new_height = original_height;

	    // first check if we need to scale width
	    if (original_width < bound_width) {
	        //scale width to fit
	        new_width = bound_width;
	        //scale height to maintain aspect ratio
	        new_height = (new_width * original_height) / original_width;
	    }

		scale = ((double)new_height/(double)original_height);

	    return new Dimension(new_width, new_height);
	}

}
