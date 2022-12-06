package fr.game.advent.advent2022.day06;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Day06_1 {
	static char CHAR_0 = ' ';
	static char CHAR_1 = ' ';
	static char CHAR_2 = ' ';
	static char CHAR_3 = ' ';

	public static void main(String[] args) throws IOException {
		try {

			FileInputStream source = new FileInputStream(
					"C:\\INSEE\\WS\\Advent-of-code\\advent-of-code\\src\\main\\resources\\fr\\game\\advent\\advent2022\\day06\\input06-1.txt");
			Scanner scanner = new Scanner(source);
			Integer resultat = 0;

			String ligne = scanner.nextLine();

			CHAR_0 = ligne.charAt(0);
			CHAR_1 = ligne.charAt(1);
			CHAR_2 = ligne.charAt(2);
			CHAR_3 = ligne.charAt(3);

			for (int i = 4; i < ligne.length(); i++) {
				CHAR_0 = CHAR_1;
				CHAR_1 = CHAR_2;
				CHAR_2 = CHAR_3;
				CHAR_3 = ligne.charAt(i);
				if (comparerLettres()) {
					resultat = i+1;
					break;
				}

			}
			System.out.println(resultat);
			scanner.close();
		} catch (

		IOException e) {
			e.printStackTrace();

		}
	}

	private static boolean comparerLettres() {
		if (CHAR_0 != CHAR_1 && CHAR_0 != CHAR_2 && CHAR_0 != CHAR_3 && CHAR_1 != CHAR_2 && CHAR_1 != CHAR_3 && CHAR_2 != CHAR_3)
			return true;
		else
			return false;
	}

}
