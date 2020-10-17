package FlappyBird_Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class MainMenu extends JFrame {

	// public static FlappyBird flappyBird;
	public static Renderer renderer;
	private static final long serialVersionUID = 1L;
	public static MainMenu menu;
	public ImagePanel img;
	public static JButton btStart, btMode, btRanking, btExit;
	public static JButton btModeEasy, btModeHard;

	public MainMenu() {

		btStart(); // JButton JUGA!
		btMode(); // JButton MODE
		btRanking(); // JButton RANKING
		btExit(); // JButton TANCA
		mainMenu(); // JFrame, debe de ser la ultima funcion.

	}

	private void mainMenu() {

		// Propiedades del MainMenu

		setTitle("Flappy Bird - Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(Data.width, Data.heigth);
		setLocationRelativeTo(null);
		setResizable(false);

		// Añadimos la imagen de fondo de la aplicación

		ImagePanel img = new ImagePanel();
		add(img);

		// Finalmente hacemos el JFrame visible

		setVisible(true);

	}

	private void btStart() {

		// Botón START
		// Este botón iniciará la clase FlappyBird(), es decir
		// una vez que el jugador le de a este botón cambiará de
		// JFrame e irá al JFrame del juego.

		btStart = new JButton("JUGA!");
		btStart.setBounds(100, 300, 200, 50);
		btStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FlappyBird gameBird = new FlappyBird();
				if (Data.chkMenu) {
					gameBird.setVisible(true);
					setVisible(false);
				}else {
					gameBird.setVisible(false);
					setVisible(false);
				}
				
			}
		});

		add(btStart);
	}

	private void btMode() {

		// Botón MODE
		// Este botón creará un JOptionPanel con 2 JButton
		// dentro de el, estos botones serán los niveles de
		// dificultad del juego, EASY y HARD.

		// El jugador podrá elegir con que dificultad quiere jugar,
		// por defecto el juego estará en EASY.

		btMode = new JButton("MODE");
		btMode.setBounds(100, 400, 200, 50);

		// Crea 2 JButton EASY / HARD.

		btModeEasy = new JButton("EASY");
		btModeHard = new JButton("HARD");
		btModeEasy.setBounds(350, 400, 100, 50);
		btModeHard.setBounds(500, 400, 100, 50);
		btModeEasy.setVisible(false);
		btModeHard.setVisible(false);

		btMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Hace visibles los 2 botones extra

				btModeEasy.setVisible(true);
				btModeHard.setVisible(true);

				// Bloquea otra pulsación hasta elegir dificultad
				btMode.setEnabled(false);

			}
		});

		btModeEasy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Al presionar el JButton EASY, cambia el boolean easyMode a TRUE y hardMode a
				// FALSE

				Data.easyMode = true;
				Data.hardMode = false;
				btModeEasy.setVisible(false);
				btModeHard.setVisible(false);
				btMode.setEnabled(true);
			}
		});

		// Al presionar el JButton HARD, cambia el boolean easyMode a FALSE y hardMode a
		// TRUE
		btModeHard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Data.hardMode = true;
				Data.easyMode = false;
				btModeEasy.setVisible(false);
				btModeHard.setVisible(false);
				btMode.setEnabled(true);
			}
		});

		// Una vez presionados, ambos botones se ocultan y activa de nuevo
		// el JButton MODE

		add(btMode);
		add(btModeEasy);
		add(btModeHard);
	}

	private void btRanking() {

		// Botón RANKING
		// Al ejecutar este botón, ejecutará la clase Ranking()
		// que consta de otro JFrame donde estará registrada
		// la mejor puntuacion y el nombre del jugador.

		btRanking = new JButton("RANKING");
		btRanking.setBounds(100, 500, 200, 50);
		btRanking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ranking ranking = new Ranking();
				setVisible(false);
				ranking.setVisible(true);
			}
		});

		add(btRanking);

	}

	private void btExit() {

		// Botón TANCA
		// Al presionar este botón el programa termina y cierra este JFrame

		btExit = new JButton("TANCA");
		btExit.setBounds(1000, 600, 200, 50);
		btExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		add(btExit);

	}

	public static void main(String[] args) {

		menu = new MainMenu();

	}

}
