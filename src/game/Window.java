package game;

import javax.swing.JFrame;

public class Window extends JFrame {

	private static final long serialVersionUID = -4369730830015653927L;
	public Window(int width,int height,String title,Game game) {
		
		JFrame frame=new JFrame(title);
		frame.setSize(width,height);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);  
<<<<<<< refs/remotes/origin/Felipe
		frame.setUndecorated(true);	//false apresenta opções no canto da janela(no linux)
=======
		frame.setUndecorated(true);	//Para funcionar no linux tem que ser false
>>>>>>> Atualzando Banco
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
		frame.add(game);
		game.start();
	}
}
