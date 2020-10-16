package FlappyBird_Game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private BufferedImage img;
	
	public ImagePanel() {
		try {
			img = ImageIO.read(new File("MainMenuBackground.png"));
			JLabel picLabel = new JLabel(new ImageIcon(img));
			add(picLabel);
		} catch (IOException e) {
			System.out.println("File not found...");
			e.printStackTrace();
		}
		
	}
	
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this);
	}
	
}