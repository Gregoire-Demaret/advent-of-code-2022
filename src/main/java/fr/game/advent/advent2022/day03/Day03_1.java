package fr.game.advent.advent2022.day03;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Day03_1 {
	static String CONTENU_COMPARTIMENT_1 = null;
	static String CONTENU_COMPARTIMENT_2 = null;

	public static void main(String[] args) throws IOException {
		try {
			int prioriteTotale = 0;

			FileInputStream source = new FileInputStream(
					"C:\\INSEE\\WS\\Advent-of-code\\advent-of-code\\src\\main\\resources\\fr\\game\\advent\\advent2022\\day03\\input03-1.txt");
			Scanner scanner = new Scanner(source);
			while (scanner.hasNextLine()) {
				char objetCommun = 0;
				int valeur = 0;
				String contenuSac = scanner.nextLine();
				decouperLigne(contenuSac);
				objetCommun = comparerContenuCompartiments();
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

	private static char comparerContenuCompartiments() {
		char objetCommun = 0;

		char[] contenuCompartiment1Array = CONTENU_COMPARTIMENT_1.toCharArray();
		for (char c : contenuCompartiment1Array) {
			String str = String.valueOf(c);
			if (CONTENU_COMPARTIMENT_2.contains(str)) {
				objetCommun = c;
			}
		}
		return objetCommun;
	}

	private static void decouperLigne(String contenuSac) {
		Integer longueurLigne = contenuSac.length();
		CONTENU_COMPARTIMENT_1 = contenuSac.substring(0, (longueurLigne / 2));
		CONTENU_COMPARTIMENT_2 = contenuSac.substring((longueurLigne / 2), longueurLigne);
	}
}
