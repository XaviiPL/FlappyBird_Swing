package FlappyBird_Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

public class Ranking extends JFrame {

	private static final long serialVersionUID = 1L;
	public static Ranking ranking;
	public static JButton btBack;

	public Ranking() {

		data();
		ranking();

	}

	public void ranking() {

		setTitle("Flappy Bird - Ranking");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(250, 250);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);

	}

	public void data() {

		JLabel lblRankingName = new JLabel("Xavi");
		JLabel lblRankingScore = new JLabel("0");

		// Este TRY obtiene del fichero rankingData.txt el nombre introducido
		// y su puntuación.

		try {
			BufferedReader br = new BufferedReader(new FileReader(Data.rankingData));
			if (!(Data.rankingData.length() == 0)) {
				String line = br.readLine();
				String[] lineSplit = line.split(" // ");

				// Obtiene el nombre y puntuación y los inserta a las JLabel.
				
				lblRankingName.setText(lineSplit[0]);
				lblRankingScore.setText(lineSplit[1]);
			}else {
				
				// Si no hay nada en el fichero deja las JLabel vacías.
				
				lblRankingName.setText("");
				lblRankingScore.setText("");
			}
			

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Botón BACK
		// Al presionar este botón cierra el JFrame Ranking y vuelve al JFrame MainMenu

		btBack = new JButton("TANCA");
		btBack.setBounds(1000, 600, 200, 50);
		btBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu menu = new MainMenu();
				setVisible(false);
				menu.setVisible(true);
			}
		});

		// GroupLayout

		JLabel lblName = new JLabel("NOM");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setVerticalAlignment(SwingConstants.BOTTOM);

		JLabel lblScore = new JLabel("PUNTS");
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);

		lblRankingName.setHorizontalAlignment(SwingConstants.CENTER);

		lblRankingScore.setHorizontalAlignment(SwingConstants.CENTER);

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(btBack, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
				.addGroup(groupLayout.createSequentialGroup().addGap(43)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblName, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblRankingName, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 77,
										Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblRankingScore, GroupLayout.PREFERRED_SIZE, 74,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblScore, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(Alignment.LEADING,
				groupLayout.createSequentialGroup().addGap(40)
						.addGroup(groupLayout
								.createParallelGroup(Alignment.BASELINE).addComponent(lblName).addComponent(lblScore))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblRankingName)
								.addComponent(lblRankingScore))
						.addPreferredGap(ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
						.addComponent(btBack, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)));
		getContentPane().setLayout(groupLayout);
	}

	public static void main(String[] args) {

		ranking = new Ranking();

	}
}
