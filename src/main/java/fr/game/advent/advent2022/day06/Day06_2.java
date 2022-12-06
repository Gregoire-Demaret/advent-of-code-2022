package fr.game.advent.advent2022.day06;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Day06_2 {
	static int EMPLACEMENT_SUR_LA_LIGNE = 13;

	public static void main(String[] args) throws IOException {
		try {

			FileInputStream source = new FileInputStream(
					"C:\\INSEE\\WS\\Advent-of-code\\advent-of-code\\src\\main\\resources\\fr\\game\\advent\\advent2022\\day06\\input06-1.txt");
			Scanner scanner = new Scanner(source);
			String ligne = scanner.nextLine();
			while (EMPLACEMENT_SUR_LA_LIGNE < ligne.length() && !chercherMarqueur(ligne)) {
				EMPLACEMENT_SUR_LA_LIGNE += 1;
			}
			System.out.println(EMPLACEMENT_SUR_LA_LIGNE+1);
			scanner.close();
		} catch (

		IOException e) {
			e.printStackTrace();

		}
	}

	private static boolean chercherMarqueur(String ligne) {
		boolean comparateur = true;
		int curseur1Initial = EMPLACEMENT_SUR_LA_LIGNE;
		int curseur2Initial = EMPLACEMENT_SUR_LA_LIGNE - 1;
		int curseur1 = EMPLACEMENT_SUR_LA_LIGNE;
		int curseur2 = EMPLACEMENT_SUR_LA_LIGNE - 1;
		while ((curseur1Initial - curseur1) != 14 && comparateur == true) {
			while ((curseur2Initial - curseur2) != 13 && comparateur == true) {
				comparateur = comparerLettres(ligne.charAt(curseur1), ligne.charAt(curseur2));
				curseur2--;
			}
			curseur2 = curseur1 - 2;
			curseur1--;
		}
		return comparateur;
	}

	private static boolean comparerLettres(char lettre1, char lettre2) {
		if (lettre1 != lettre2)
			return true;
		else
			return false;
	}

}