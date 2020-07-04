package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Window extends JFrame implements ActionListener{
	private static final long serialVersionUID = -4369730830015653927L;
	private Game game;
	private ImageIcon imgReturn;
	private JButton endHome;
	private JPanel principal;
	private Menu menu;

	public Window(int width,int height,String title,Game game) {
		this.game=game;
		menu = new Menu(this);
		
		this.setTitle(title);
		this.setSize(width,height);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);  
		this.setUndecorated(true);	//false apresenta opcoes no canto da janela(no linux)
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setIgnoreRepaint(true);
		this.add(menu);
	}
	 public void actionPerformed(ActionEvent evento) {
		 	principal=new JPanel();
			principal.setOpaque(false);
			principal.setLayout(null);
			principal.setVisible(true);
			
			imgReturn=menu.resize(new ImageIcon("assets/returnButton.png"));
			endHome=new JButton(imgReturn);
			endHome.setBounds((this.getWidth()/2)-(imgReturn.getIconWidth()/2), (HEIGHT/2)+450, 432, 144);
			endHome.addActionListener(e->setMenu());
			endHome.setVisible(false);
			principal.add(endHome);
			principal.add(game);
			
			menu.add(principal,"principal");
			menu.cardLayout.show(menu,"principal");
		 	
		 	game.gameStart();
		 	game.start(); 	
	 }
	 public void endGame() {
		 endHome.setVisible(true);
		 SwingUtilities.updateComponentTreeUI(this);
	 }
	 public void setMenu() {
		 game.stop();
		 menu.cardLayout.show(menu,"home");
	 }
}
