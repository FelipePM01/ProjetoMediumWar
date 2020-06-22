package game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Window extends JFrame implements ActionListener{
	public JFrame frame;
	private Game game;
	private ImageIcon imgReturn;
	private JButton endHome;
	private JPanel principal;
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
			
		 	principal=new JPanel();
			principal.setOpaque(false);
			principal.setSize(WIDTH, HEIGHT);
			principal.setLayout(null);
			principal.setVisible(true);
			
			imgReturn=menu.resize(new ImageIcon("assets/returnButton.png"));
			endHome=new JButton(imgReturn);
			endHome.setBounds((frame.getWidth()/2)-(imgReturn.getIconWidth()/2), (HEIGHT/2)+450, 432, 144);
			endHome.addActionListener(e->setMenu());
			endHome.setVisible(false);
			principal.add(endHome);
			principal.add(game);
			
			frame.add(principal);
		 	
		 	game.gameStart();
		 	game.start(); 	
	 }
	 
	 public void endGame() {
		    endHome.setVisible(true);
		    SwingUtilities.updateComponentTreeUI(this);
	 }
	 public void setMenu() {
		 game.stop();
		 frame.remove(principal);
		 frame.add(menu);
		 menu.cardLayout.show(menu,"home");
		 menu.setVisible(true);
		 menu.setFocusable(true);
	 }
}
