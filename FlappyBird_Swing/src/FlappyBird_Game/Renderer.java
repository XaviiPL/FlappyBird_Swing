package FlappyBird_Game;

import java.awt.Graphics;

import javax.swing.JPanel;

public class Renderer extends JPanel {
	
	// public static FlappyBird flappyBird;

	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		FlappyBird.flappyBird.repaint(g);
	}
	
}