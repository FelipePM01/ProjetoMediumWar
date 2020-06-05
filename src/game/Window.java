package game;

import java.awt.Color;

import javax.swing.JFrame;

public class Window extends JFrame {

	private static final long serialVersionUID = -4369730830015653927L;
	public Window(int width,int height,String title,Game game) {
		
		JFrame frame=new JFrame(title);
		frame.setSize(width,height);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);  
		frame.setUndecorated(false);	//false apresenta opções no canto da janela(no linux)
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		//frame.getContentPane().setBackground(Color.BLACK);
		frame.add(game);
		game.start();
	}
}
