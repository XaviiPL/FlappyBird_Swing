package FlappyBird;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class MainMenu implements ActionListener{

	public static MainMenu menu;
	public ImagePanel img;
	
	public MainMenu() {
		
		JFrame frame = new JFrame();
		img = new ImagePanel();
		
		frame.add(img);
		frame.setTitle("Flappy Bird - Play");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(Data.width, Data.heigth);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		
		menu = new MainMenu();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
