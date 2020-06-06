package game;

import javax.swing.JFrame;

public class Window extends JFrame {
	JFrame frame;
	private static final long serialVersionUID = -4369730830015653927L;
	public Window(int width,int height,String title,Game game) {
		
		frame=new JFrame(title);
		frame.setSize(width,height);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);  
		frame.setUndecorated(true);	//Para funcionar no linux tem que ser false
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
		frame.add(game);
		game.start();
	}
}
