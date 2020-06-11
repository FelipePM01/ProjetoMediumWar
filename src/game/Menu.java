package game;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class  Menu extends JLabel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6053970674453213543L;
	private ImageIcon imgPlay, imgCreditsButton, imgMenu, imgCredits;
	private JButton play, credits, home;
	private Window window;
	private JLabel creditsPage;
	private boolean visible = true;
	
	public Menu(int WIDTH, int HEIGHT, Window window){
		imgPlay =new ImageIcon("assets/playButton.png");        
		imgCreditsButton=new ImageIcon("assets/creditsButton.png");
		imgMenu = new ImageIcon("C:\\Users\\Cristiano\\Documents\\Area de Trabalho\\maxresdefault.jpg");
		imgCredits = new ImageIcon("C:\\Users\\Cristiano\\Documents\\Area de Trabalho\\Pink-Floyd-Wallpaper.jpg");
		
		this.window = window;
		this.setSize(WIDTH, HEIGHT);
		this.setLayout(null);
		this.setIcon(imgMenu);
		
		
		creditsPage = new JLabel();
		creditsPage.setSize(WIDTH, HEIGHT);
		creditsPage.setLayout(null);
		creditsPage.setIcon(imgCredits);
		creditsPage.setVisible(false);
		this.add(creditsPage);
		
		setHome();
		setVisible(true);
	}
	public void setHome() {
		
		play=new JButton(imgPlay);
		play.setBounds(120, 900, 216, 72);
		play.addActionListener(window);
		this.add(play);
		
		credits=new JButton(imgCreditsButton);
		credits.setBounds(360, 900, 216, 72);
		credits.addActionListener(this);
		this.add(credits);
		
		home=new JButton("Voltar");
		home.setBounds(120, 900, 216, 72);
		home.addActionListener(this);
		creditsPage.add(home);
	}
	
	public void actionPerformed(ActionEvent evento) {
		
		if(visible==true) {
			creditsPage.setVisible(true);
			play.setVisible(false);
			credits.setVisible(false);
			visible=false;
		}
		else {
			
			creditsPage.setVisible(false);
			play.setVisible(true);
			credits.setVisible(true);
			visible=true;
		}		
   }
}