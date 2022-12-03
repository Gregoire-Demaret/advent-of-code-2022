package fr.game.advent.advent2022.day03;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Day03_2 {

	static String CONTENU_SAC_1 = null;
	static String CONTENU_SAC_2 = null;
	static String CONTENU_SAC_3 = null;

	public static void main(String[] args) throws IOException {
		try {
			int prioriteTotale = 0;

			FileInputStream source = new FileInputStream(
					"C:\\INSEE\\WS\\Advent-of-code\\advent-of-code\\src\\main\\resources\\fr\\game\\advent\\advent2022\\day03\\input03-1.txt");
			Scanner scanner = new Scanner(source);
			while (scanner.hasNextLine()) {
				char objetCommun = 0;
				int valeur = 0;
				CONTENU_SAC_1 = scanner.nextLine();
				CONTENU_SAC_2 = scanner.nextLine();
				CONTENU_SAC_3 = scanner.nextLine();
				objetCommun = comparerContenuSacs();
				valeur = calculerValeur(objetCommun);
				prioriteTotale += valeur;

			}
			System.out.println(prioriteTotale);
			scanner.close();
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	private static int calculerValeur(char objetCommun) {
		int valeur = objetCommun;
		if (valeur < 91) {
			valeur -= 38;
		} else {
			valeur -= 96;
		}
		return valeur;

	}

	private static char comparerContenuSacs() {
		char objetCommun = 0;
		char[] contenuSac1Array = CONTENU_SAC_1.toCharArray();
		for (char c : contenuSac1Array) {
			String str = String.valueOf(c);
			if (CONTENU_SAC_2.contains(str)) {
				if (CONTENU_SAC_3.contains(str)) {
					objetCommun = c;
				}
			}
		}
		return objetCommun;
	}
}
