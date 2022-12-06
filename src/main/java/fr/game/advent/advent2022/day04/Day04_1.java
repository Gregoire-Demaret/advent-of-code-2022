package fr.game.advent.advent2022.day04;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Day04_1 {
	static String PAIRE_1 = "";
	static String PAIRE_2 = "";
	static int ELFE_1_DEBUT = 0;
	static int ELFE_1_FIN = 0;
	static int ELFE_2_DEBUT = 0;
	static int ELFE_2_FIN = 0;
	static Integer NOMBRE_DE_PAIRES_QUI_RECOUPENT = 0;
			
	public static void main(String[] args) throws IOException {
		try {


			FileInputStream source = new FileInputStream(
					"C:\\INSEE\\WS\\Advent-of-code\\advent-of-code\\src\\main\\resources\\fr\\game\\advent\\advent2022\\day04\\input04-1.txt");
			Scanner scanner = new Scanner(source);
			while (scanner.hasNextLine()) {

				String ligne = scanner.nextLine();
				decouperLigne(ligne);
				decouperSections();
				comparerSections();

			}
			System.out.println(NOMBRE_DE_PAIRES_QUI_RECOUPENT);
			scanner.close();
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	private static void comparerSections() {
		Boolean i = false;
		if (ELFE_1_DEBUT >= ELFE_2_DEBUT && ELFE_1_FIN <= ELFE_2_FIN) {
			i = true;
		}
		if (ELFE_1_DEBUT <= ELFE_2_DEBUT && ELFE_1_FIN >= ELFE_2_FIN) {
			i = true;
		}
		if (i) NOMBRE_DE_PAIRES_QUI_RECOUPENT++;
	}

	private static void decouperSections() {
		int i = 0;
		while (PAIRE_1.charAt(i) != '-') {
			i++;
		}
		ELFE_1_DEBUT = Integer.parseInt(PAIRE_1.substring(0, i));
		ELFE_1_FIN = Integer.parseInt(PAIRE_1.substring(i + 1));

		i = 0;
		while (PAIRE_2.charAt(i) != '-') {
			i++;
		}
		ELFE_2_DEBUT = Integer.parseInt(PAIRE_2.substring(0, i));
		ELFE_2_FIN = Integer.parseInt(PAIRE_2.substring(i + 1));
	}

	private static void decouperLigne(String ligne) {
		int i = 0;
		while (ligne.charAt(i) != ',') {
			i++;
		}
		PAIRE_1 = ligne.substring(0, i);
		PAIRE_2 = ligne.substring(i + 1);
	}
}
