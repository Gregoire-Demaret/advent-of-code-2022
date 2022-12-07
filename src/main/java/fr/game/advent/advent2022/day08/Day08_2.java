package fr.game.advent.advent2022.day08;

import java.io.IOException;
import java.util.List;

import fr.game.advent.outils.FichierServices;

public class Day08_2 {

	private static Integer NEXT_ID = 1;
	private static String LIGNE = null;
	private static boolean LIGNE_LUE = false;
	private static List<String> LIGNES = null;
	private static int NUMERO_LIGNE = 1;

	public static void main(String[] args) throws IOException {
		try {
			FichierServices fichierServices = new FichierServices();

			LIGNES = fichierServices.lireFichierTexte(
					"C:\\INSEE\\WS\\Advent-of-code\\advent-of-code\\src\\main\\resources\\fr\\game\\advent\\advent2022\\day07\\input08-1.txt");
			while (NUMERO_LIGNE < LIGNES.size()) {
				if (LIGNE_LUE == false) {
					ligneSuivante();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void ligneSuivante() {
		LIGNE = LIGNES.get(NUMERO_LIGNE);
		NUMERO_LIGNE++;
	}
}
