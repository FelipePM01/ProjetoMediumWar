package game;

import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HomeWindow extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6053970674453213543L;
	private JPanel home, credits, controls;
	private CardLayout pages;
	private Image imgHome;
	private JButton start;
	
	public HomeWindow() {
		setHome();
	}
	public void setHome() {
		pages = new CardLayout();
		setLayout(pages);
		
		ImageIcon refImgHome=new ImageIcon("assets/banco.png");        
		imgHome=refImgHome.getImage();
		//imgHome=imgHome.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_DEFAULT);
		
		start = new JButton("Start");
		start.setSize(216,72);
		home = new JPanel();
		home.setName("home");
		home.setLayout(null);
		home.add(start);
		home.setBackground(Color.RED);
		//home.add(imgHome);
		add(home, home.getName());
		
	}
	 public void showPage(String nome,Graphics g){
		 pages.show(this,nome);
		 g.drawImage(imgHome, 0, 0, this);
	 }
}