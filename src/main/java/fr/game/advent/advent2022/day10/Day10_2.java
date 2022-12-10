package fr.game.advent.advent2022.day10;

import java.io.IOException;
import java.util.List;

import fr.game.advent.outils.FichierServices;

public class Day10_2 {

		private static String LIGNE = null;
		private static List<String> LIGNES = null;
		private static int NUMERO_LIGNE = 0;
		private static int NUMERO_CYCLE = 1;
		private static int X = 1;
		private static char[][] GRILLE = new char[40][6];
		private static int NUMERO_LIGNE_ECRAN = 0;
		private static int COMPTEUR_CHANGEMENT_LIGNE = 0;
		
		public static void main(String[] args) throws IOException {
			try {
				FichierServices fichierServices = new FichierServices();

				LIGNES = fichierServices.lireFichierTexte(
						"C:\\INSEE\\WS\\Advent-of-code\\advent-of-code\\src\\main\\resources\\fr\\game\\advent\\advent2022\\day10\\input10-1.txt");

				while (NUMERO_LIGNE < LIGNES.size()) {
					ligneSuivante();

					if (LIGNE.substring(0, 4).contains("noop")) {
						tracerPixel();
						NUMERO_CYCLE++;
					}
					if (LIGNE.substring(0, 4).contains("addx")) {
						tracerPixel();
						NUMERO_CYCLE++;
						tracerPixel();
						X += Integer.parseInt(LIGNE.substring(5));
						NUMERO_CYCLE++;
					}
					}

				for (int y = 0; y < 6; y++) {
					for (int x = 0; x < 40; x++) {
						System.out.print(GRILLE[x][y]);
					}
					System.out.println();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		private static void tracerPixel() {
			if (NUMERO_CYCLE == 41 + COMPTEUR_CHANGEMENT_LIGNE*40) {
				NUMERO_LIGNE_ECRAN++;
				COMPTEUR_CHANGEMENT_LIGNE++;
			}
//			System.out.println((NUMERO_CYCLE - 1) % 40 +" " + NUMERO_LIGNE_ECRAN);

			if (((NUMERO_CYCLE - 1) % 40) == X || ((NUMERO_CYCLE - 1) % 40) == (X +1) || ((NUMERO_CYCLE - 1) % 40) == (X -1)) {
				GRILLE[(NUMERO_CYCLE - 1) % 40][NUMERO_LIGNE_ECRAN] = '#';
			}
			else {
				GRILLE[(NUMERO_CYCLE - 1) % 40][NUMERO_LIGNE_ECRAN] = '.';
			}
//			System.out.println(GRILLE[(NUMERO_CYCLE - 1) / 40][NUMERO_LIGNE_ECRAN]);
		}
		
		private static void ligneSuivante() {
			LIGNE = LIGNES.get(NUMERO_LIGNE);
			NUMERO_LIGNE++;
		}
	}
