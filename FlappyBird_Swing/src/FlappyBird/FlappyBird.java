package FlappyBird;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

public class FlappyBird implements ActionListener, MouseListener, KeyListener {

	public static FlappyBird flappyBird;
	public Renderer renderer;
	public Rectangle bird;
	public ArrayList<Rectangle> pipes;
	public boolean start, finish;
	public int ticks, ySpeed, score;
	public Random random;

	public FlappyBird() {

		JFrame frame = new JFrame();
		Timer timer = new Timer(20, this);
		
		renderer = new Renderer();
		random = new Random();

		// Propiedades del JFrame principal del juego
		frame.add(renderer);
		frame.setTitle("Flappy Bird with Swing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(Data.width, Data.heigth);
		frame.setLocationRelativeTo(null);
		frame.addMouseListener(this);
		frame.addKeyListener(this);
		frame.setResizable(false);
		frame.setVisible(true);

		// Creamos un objecto Rectangle que representará a nuestro pájaro
		// proporcionando su tamaño con las variables "Width" y "Heigth" entre 2
		bird = new Rectangle(Data.width / 2 - 10, Data.heigth / 2 - 10, 20, 20);
		pipes = new ArrayList<Rectangle>();

		addPipe(true);
		addPipe(true);
		addPipe(true);
		addPipe(true);

		// Comenzar el juego.
		timer.start();
		
	}

	// Esta función se encarga de crear las tuberías donde el pájaro (bird) debe
	// atravesarlas por el hueco que hay entre ellas.
	public void addPipe(boolean start) {

		int pipeHeigth = 50 + random.nextInt(300);
		
		// Crea una nueva tubería pipe, le proporciona sus Axis X e Y, y su ancho y
		// alto.
		if (start) {
			pipes.add(new Rectangle(Data.width + Data.pipeWidth + pipes.size() * 300,
					Data.heigth - pipeHeigth - 120, Data.pipeWidth, pipeHeigth));
			pipes.add(new Rectangle(Data.width + Data.pipeWidth + (pipes.size() - 1) * 300, 0, Data.pipeWidth,
					Data.heigth - pipeHeigth - Data.space));
		} else {
			pipes.add(new Rectangle(pipes.get(pipes.size() - 1).x + 600, Data.heigth - pipeHeigth - 120,
					Data.pipeWidth, pipeHeigth));
			pipes.add(new Rectangle(pipes.get(pipes.size() - 1).x, 0, Data.pipeWidth,
					Data.heigth - pipeHeigth - Data.space));
		}

	}

	// Esta función pinta el objecto Rectangle "pipe" de color rojo.
	public void paintPipe(Graphics g, Rectangle pipe) {

		g.setColor(Color.yellow);
		g.fillRect(pipe.x, pipe.y, pipe.width, pipe.height);

	}

	// Esta funcion se encarga de iniciar el juego y de acabarlo. Consta de 3 fases	
	public void stageGame() {

		// Si el juego ha acabado. Borra todas las tuberías añadidas al ArrayList,
		// reinicia marcador y crea un nuevo Rectangle del pájaro en la posición inicial.
		// Tambíen crea otra tanda de tuberías. 
		
		// Una vez hecho el reseteo, con el boolean "finish" lo ""verifica"" 
		if (finish) {
			bird = new Rectangle(Data.width / 2, Data.heigth / 2, 20, 20);
			pipes.clear();
			ySpeed = 0;
			score = 0;

			addPipe(true);
			addPipe(true);
			addPipe(true);
			addPipe(true);

			finish = false;
		}
		
		// Si el juego no ha empezado aun,
		if (!start) {
			start = true;
			
			// Si el juego aun no ha acabado.
		} else if (!finish) {
			if (ySpeed > 0) {
				ySpeed = 0;
			}
			
		}
		
		jump();

	}

	// Función basica que se ejecuta al pulsar SPACE o CLICK.
	public void jump(){
		// Por cada click, la posY del pájaro aumenta 10.
		ySpeed -= 10;
		
	}
	
	public static void main(String[] args) {

		flappyBird = new FlappyBird();

	}

	public void actionPerformed(ActionEvent e) {

		// Velocidad de paso de las tuberías. Cuanto mas, más rapido vienen.
		int speed = 10;
		
		// Bucles del juego, se produce cada 0,05s == "1seg = 20 ticks"
		ticks++;

		// Movimiento de las tuberías

		if (start) {
			// Este FOR se encarga de mover las tuberías, cuanto más alto sea
			// el valor de "speed", mas rapido avanzarán.
			// Al moverse va reduciendo su eje X (IZQ < DER) 
			for (int i = 0; i < pipes.size(); i++) {
				Rectangle pipe = pipes.get(i);
				pipe.x -= speed;
			} 

			// Este IF se encarga de "simular la gravedad", si el MOD de 2 es 0 (ocurrirá
			// cada 0,1s). El valor de "ySpeed" augmentará en 3. Cuanto más alto sea el valor
			// que aumente ySpeed, más fuerte será la fuerza de la "gravedad" que enviará al
			// pajaro contra el suelo. Afecta tanto en la caida como en el bote al bajar de velocidad			
			if (ticks % 2 == 0 && ySpeed < 15) {
				ySpeed += 3;
			}

			// Este FOR se encarga de eliminar del ArrayList una tubería cuando una de ellas llegue
			// a 0 sumando su posiciónX + su anchura (aprox. -300x)
			for (int i = 0; i < pipes.size(); i++) {
				Rectangle pipe = pipes.get(i);
				if (pipe.x + pipe.width < 0) {
					pipes.remove(pipe);
					if (pipe.y == 0) {
						addPipe(false);
					}
				}
			}

			bird.y += ySpeed;

			for (Rectangle pipe : pipes) {
				// Si el pájaro atraviesa correctamente y sin tocar una tubería
				// gana 1 punto
				if (pipe.y == 0 && bird.x + bird.width / 2 > pipe.x + pipe.width / 2 - 10
						&& bird.x + bird.width / 2 < pipe.x + pipe.width / 2 + 10) {
					score++;
				}
				// Si el pájaro se estrella...
				if (pipe.intersects(bird)) {
					finish = true;

					// El pájaro cae hasta abajo (?) -- Revisar, el pajaro sube xd
					if (bird.x <= pipe.x) {
						bird.x = pipe.x - bird.width;
					} else {
						if (pipe.y != 0) {
							bird.y = pipe.y - bird.height;
						} else if (bird.y < pipe.height) {
							bird.y = pipe.height;
						}
					}
				}
			}

			// Si el pajaro toca el techo...
			if (bird.y > Data.heigth - 120 || bird.y < 0) {
				finish = true;
			}

		
			// Si el pájaro toca el suelo...
			if (bird.y + ySpeed >= Data.heigth - 120) {
				bird.y = Data.heigth - 120 - bird.height;
				finish = true;
			}
			

		}

		renderer.repaint();

	}

	// Esta funcion colorea por completo el juego, funciona en forma de CAPAS, es
	// decir
	// crea objetos que sobrepone entre ellos, por lo cual lo primero que coloreará
	// el
	// juego será el cielo, seguidamente la acera y el césped, luego el pájaro y
	// finalmente
	// las tuberías, de otro orden puede ser que no se viese correctamente.

	// Una vez ya coloreado todo lo que sería el fondo, pintará por pantalla un
	// mensaje
	// sobrepuesto por todos los elementos, dependeiendo del punto del juego donde
	// estemos
	// (boolean) se escribirá un mensaje u otro.

	public void repaint(Graphics g) {

		// Colorear el cielo, 1a capa
		g.setColor(Color.cyan);
		g.fillRect(0, 0, Data.width, Data.heigth);

		// Colorear la acera. 2a capa
		g.setColor(Color.gray);
		g.fillRect(0, Data.heigth - 120, Data.width, 120);

		// Colorear el césped. 3a capa
		g.setColor(Color.green);
		g.fillRect(0, Data.heigth - 120, Data.width, 20);

		// Colorear al pájaro (bird). 4a capa
		g.setColor(Color.red);
		g.fillRect(bird.x, bird.y, bird.width, bird.height);

		// Aqui con un for-each colorearé las tuberías (pipes), utilizaremos
		// la clase anteriormente creada "paintPipes". 5a capa
		for (Rectangle pipe : pipes) {
			paintPipe(g, pipe);
		}

		// Crearemos y colorearemos un mensaje de texto en pantalla dependiendo del
		// estado
		// del juego, utilizará la clase Font.
		g.setColor(Color.white);
		g.setFont(new Font("Calibri", 1, 100));

		// Si aun no ha empezado a jugar...
		if (!start) {
			g.drawString("Comença!", 75, Data.heigth / 2);
		}

		// Si ha acabado de jugar o ha muerto...
		if (finish) {
			g.drawString("Fi de la partida...", 100, Data.heigth / 2);
		}

		// En mitad de la partida, esta hara de marcador.
		if (!finish && start) {
			g.drawString(String.valueOf(pipes.size()), Data.width / 2, 100);
		}

	}

	// Si el jugador deja de presionar la tecla "SPACE" (Por lo cual antes debe de
	// presionarla)
	// ejecutará la funcion stageGame()
	public void keyReleased(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			stageGame();
		}

	}

	// Si el jugador clicka, ejecutará la funcion stageGame()
	public void mouseClicked(MouseEvent e) {

		stageGame();

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
