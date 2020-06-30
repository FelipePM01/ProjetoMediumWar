package game;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6053970674453213543L;
	private ImageIcon imgPlay, imgCreditsButton, imgExit, imgReturn, imgMenu, imgCredits;
	private JButton play, credits, exit, home;
	private Window window;
	private JLabel creditsPage, menuPage;
	protected CardLayout cardLayout;
	
	public Menu(Window window){
		this.window = window;
		setMenu();
		
		//Cria cardLayout e adiciona janelas
		cardLayout = new CardLayout();
	    this.setLayout(cardLayout);
		this.add(menuPage, "home");
		this.add(creditsPage, "credits");
	    
		setVisible(true);
	}
	private void setMenu() {
		//Adiciona e ajusta imagens usadas    
		imgPlay = resize(new ImageIcon("assets/playButton.png"));
		imgCreditsButton = resize(new ImageIcon("assets/creditsButton.png"));
		imgExit = resize(new ImageIcon("assets/exitButton.png"));
		imgReturn = resize(new ImageIcon("assets/returnButton.png"));
		imgMenu = resize(new ImageIcon("assets/menuBackground.png"));
		imgCredits = resize(new ImageIcon("assets/creditsBackground.png"));
		
		//Cria Janelas
		menuPage = new JLabel();
		menuPage.setLayout(null);
		menuPage.setIcon(imgMenu);
		menuPage.setVisible(false);
		
		creditsPage = new JLabel();
		creditsPage.setOpaque(false);
		creditsPage.setLayout(null);
		creditsPage.setIcon(imgCredits);
		creditsPage.setVisible(false);
		
		//Cria Botoes
		play=new JButton(imgPlay);
		play.setBounds(1400, 400, 432, 144);
		play.addActionListener(window);
		menuPage.add(play);
		
		credits=new JButton(imgCreditsButton);
		credits.setBounds(1400, 570, 432, 144);
		credits.addActionListener(e -> cardLayout.show(this, "credits"));
		menuPage.add(credits);
		
		exit=new JButton(imgExit);
		exit.setBounds(1400, 740, 432, 144);
		exit.addActionListener(e -> System.exit(0));
		menuPage.add(exit);
		
		home=new JButton(imgReturn);
		home.setBounds(120, 900, 432, 144);
		home.addActionListener(e -> cardLayout.show(this, "home"));
		creditsPage.add(home);
	}
	
	public ImageIcon resize(ImageIcon img) {
		Image auxImg = img.getImage();
		Image newImg = auxImg.getScaledInstance((int)(img.getIconWidth()*2),(int)(img.getIconHeight()*2),java.awt.Image.SCALE_SMOOTH);
		ImageIcon imgResize = new ImageIcon(newImg);
		return imgResize;
	}
}
