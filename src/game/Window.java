package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Window extends JFrame implements ActionListener{
	public JFrame frame;
	private Game game;
	private ImageIcon imgEndRed, imgEndBlue,imgReturn;
	private JButton endHome;
	private JLabel endPageBlue,endPageRed;
	Menu menu;

	private static final long serialVersionUID = -4369730830015653927L;
	public Window(int width,int height,String title,Game game) {
		this.game=game;
		menu = new Menu(width, height, this);
		frame=new JFrame(title);
		frame.setSize(width,height);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);  
		frame.setUndecorated(true);	//false apresenta opcoes no canto da janela(no linux)
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setIgnoreRepaint(true);
		frame.add(menu);
	}
	
	 public void actionPerformed(ActionEvent evento) {
		    menu.setVisible(false);
		 	frame.remove(menu);
		 	SwingUtilities.updateComponentTreeUI(this);
		 	
		 	frame.add(game);
		 	game.gameStart();
		 	game.start();
	 }
	 
	 public void endGame(String cor) {
		 	frame.remove(game);

			
		 	//frame.add(menu);
		 	//menu.cardLayout.show(menu,cor);
		 	//menu.setVisible(true);
	 }
}
