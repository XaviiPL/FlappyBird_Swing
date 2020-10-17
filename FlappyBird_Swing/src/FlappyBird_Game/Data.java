package FlappyBird_Game;

import java.io.File;

public class Data {

	// Esta clase es un conjunto de variables constantes estaticas
	
	public static final int width = 1280;								// Ancho de la ventana del juego total
	public static final int heigth = 720;								// Alto de la ventana del juego total

	public static final int space = 250;								// Espacio entre tuber�as.
	public static final int pipeWidth = 100;							// Anchura de las tuber�as
	
	public static boolean easyMode = true;								// Dificultad del juego, por defecto estara TRUE
	public static boolean hardMode = false;								// Si esta TRUE, el juego ser� m�s dificil. La puntuaci�n ser� x2
	public static boolean chkMenu = false;
	
	public static File rankingData = new File("rankingData.txt");		// Fichero Ranking
		
}
