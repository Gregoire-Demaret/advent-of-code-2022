package fr.game.advent.advent2022.day13;

import java.io.IOException;
import java.util.List;

import fr.game.advent.outils.FichierServices;

public class Day13_2 {
	
	
	private static String LIGNE = null;
	private static List<String> LIGNES = null;
	private static int NUMERO_LIGNE = 0;


	public static void main(String[] args) throws IOException {
		try {
			FichierServices fichierServices = new FichierServices();

			LIGNES = fichierServices.lireFichierTexte(
					"C:\\INSEE\\WS\\Advent-of-code\\advent-of-code\\src\\main\\resources\\fr\\game\\advent\\advent2022\\day13\\input13-1.txt");

			while (NUMERO_LIGNE < LIGNES.size()) {
				ligneSuivante();
				
			}


			System.out.println();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	private static void ligneSuivante() {
		LIGNE = LIGNES.get(NUMERO_LIGNE);
		NUMERO_LIGNE++;
	}
}
