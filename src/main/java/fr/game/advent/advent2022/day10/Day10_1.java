package fr.game.advent.advent2022.day10;

import java.io.IOException;
import java.util.List;

import fr.game.advent.outils.FichierServices;

public class Day10_1 {
	private static String LIGNE = null;
	private static List<String> LIGNES = null;
	private static int NUMERO_LIGNE = 0;
	private static int NUMERO_CYCLE = 1;
	private static int SOMME_FORCE_SIGNAL = 0;
	private static int X = 1;
	private static int Y = 0;

	public static void main(String[] args) throws IOException {
		try {
			FichierServices fichierServices = new FichierServices();

			LIGNES = fichierServices.lireFichierTexte(
					"C:\\INSEE\\WS\\Advent-of-code\\advent-of-code\\src\\main\\resources\\fr\\game\\advent\\advent2022\\day10\\input10-1.txt");

			while (NUMERO_LIGNE < LIGNES.size()) {
				ligneSuivante();
				releverForceSignal();

				if (LIGNE.substring(0, 4).contains("noop")) {
					NUMERO_CYCLE++;
				}
				if (LIGNE.substring(0, 4).contains("addx")) {
					NUMERO_CYCLE++;
					releverForceSignal();
					System.out.println(Integer.parseInt(LIGNE.substring(5)));
					X += Integer.parseInt(LIGNE.substring(5));
					System.out.println(X);
					NUMERO_CYCLE++;
				}
				}

			System.out.println(SOMME_FORCE_SIGNAL);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void releverForceSignal() {
		if(NUMERO_CYCLE == (20+40*Y)) {
			SOMME_FORCE_SIGNAL += ((NUMERO_CYCLE) * X);
			Y++;
		}
	}

	private static void ligneSuivante() {
		LIGNE = LIGNES.get(NUMERO_LIGNE);
		NUMERO_LIGNE++;
	}
}
