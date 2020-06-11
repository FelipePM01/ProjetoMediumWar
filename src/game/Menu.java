package game;

import java.awt.CardLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6053970674453213543L;
	private ImageIcon imgPlay, imgCreditsButton, imgMenu, imgCredits;
	private JButton play, credits, home;
	private Window window;
	private JLabel creditsPage, menuPage;
	private CardLayout cardLayout;
	
	public Menu(int WIDTH, int HEIGHT, Window window){
		this.window = window;
		
		setMenu();
		
		//Cria cardLayout e adiciona janelas
		cardLayout = new CardLayout();
	    this.setLayout(cardLayout);
		this.add(menuPage, "home");
		this.add(creditsPage, "credits");
	    
	    
		setVisible(true);
	}
	public void setMenu() {
		//Adiciona imagens usadas
		imgPlay =new ImageIcon("assets/playButton.png");        
		imgCreditsButton=new ImageIcon("assets/creditsButton.png");
		imgMenu = new ImageIcon("C:\\Users\\Cristiano\\Documents\\Area de Trabalho\\maxresdefault.jpg");
		imgCredits = new ImageIcon("C:\\Users\\Cristiano\\Documents\\Area de Trabalho\\Pink-Floyd-Wallpaper.jpg");
		
		//Cria Janelas
		menuPage = new JLabel();
		menuPage.setSize(WIDTH, HEIGHT);
		menuPage.setLayout(null);
		menuPage.setIcon(imgMenu);
		menuPage.setVisible(true);
		
		creditsPage = new JLabel();
		creditsPage.setSize(WIDTH, HEIGHT);
		creditsPage.setLayout(null);
		creditsPage.setIcon(imgCredits);
		creditsPage.setVisible(false);
		
		//Cria Botoes
		play=new JButton(imgPlay);
		play.setBounds(120, 900, 216, 72);
		play.addActionListener(window);
		menuPage.add(play);
		
		credits=new JButton(imgCreditsButton);
		credits.setBounds(360, 900, 216, 72);
		credits.addActionListener(e -> cardLayout.show(this, "credits"));
		menuPage.add(credits);
		
		home=new JButton("Voltar");
		home.setBounds(120, 900, 216, 72);
		home.addActionListener(e -> cardLayout.show(this, "home"));
		creditsPage.add(home);
	}
}
