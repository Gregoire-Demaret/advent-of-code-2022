package fr.game.advent.advent2022.day12;

import java.io.IOException;
import java.util.List;

import fr.game.advent.outils.FichierServices;

public class Day12_1 {

	private static String LIGNE = null;
	private static List<String> LIGNES = null;
	private static int NUMERO_LIGNE = 0;
	private static Integer[][] HAUTEUR = new Integer[101][41];
	private static Integer[][] DISTANCE = new Integer[101][41];
	private static boolean[][] VISITE = new boolean[101][41];
	private static Integer COMPTEUR = 0;

	public static void main(String[] args) throws IOException {
		try {
			FichierServices fichierServices = new FichierServices();

			LIGNES = fichierServices.lireFichierTexte(
					"C:\\INSEE\\WS\\Advent-of-code\\advent-of-code\\src\\main\\resources\\fr\\game\\advent\\advent2022\\day12\\input12-1.txt");

			remplirGrilles();

			// Tant que le point d'arrivée n'a pas été visité on parcourt la grille
			while (!VISITE[75][20] || COMPTEUR < 10000) {
				parcourirGrille();
				COMPTEUR++;
			}

			System.out.println(DISTANCE[77][20]);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void parcourirGrille() {
		// Sur chaque point
		for (int y = 0; y < 41; y++) {
			for (int x = 0; x < 100; x++) {
				// MàJ du point actuel en fonction de chaque point adjacent
				evaluerPointNord(x, y);
				evaluerPointSud(x, y);
				evaluerPointEst(x, y);
				evaluerPointOuest(x, y);
			}
		}
	}

	private static void evaluerPointNord(int x, int y) {
		if (y > 0) {
			if (testerSiVisite(x, y - 1)) {
				if (testerElevation(x, y, x, y - 1)) {
					majDistance(x, y, x, y - 1);
				}
			}
		}
	}

	private static void evaluerPointSud(int x, int y) {
		if (y < 40) {
			if (testerSiVisite(x, y + 1)) {
				if (testerElevation(x, y, x, y + 1)) {
					majDistance(x, y, x, y + 1);
				}
			}
		}
	}

	private static void evaluerPointEst(int x, int y) {
		if (x < 101) {
			if (testerSiVisite(x + 1, y)) {
				if (testerElevation(x, y, x + 1, y)) {
					majDistance(x, y, x + 1, y);
				}
			}
		}
	}

	private static void evaluerPointOuest(int x, int y) {
		if (x > 0) {
			if (testerSiVisite(x - 1, y)) {
				if (testerElevation(x, y, x - 1, y)) {
					majDistance(x, y, x - 1, y);
				}
			}
		}
	}

	private static boolean testerElevation(int x, int y, int X, int Y) {
		if (HAUTEUR[x][y] <= HAUTEUR[X][Y] + 1) {
			return true;
		}
		return false;
	}

	private static void majDistance(int x, int y, int X, int Y) {
		if (DISTANCE[X][Y] < DISTANCE[x][y] - 1) {
			DISTANCE[x][y] = DISTANCE[X][Y] + 1;
			VISITE[x][y] = true;
		}
	}

	private static boolean testerSiVisite(int x, int y) {
		return VISITE[x][y];
	}


	private static void remplirGrilles() {
		for (int y = 0; y < 41; y++) {
			ligneSuivante();
			for (int x = 0; x < LIGNE.length() - 1; x++) {
				HAUTEUR[x][y] = LIGNE.charAt(x) - 97;
				DISTANCE[x][y] = Integer.MAX_VALUE;
				VISITE[x][y] = false;
			}
		}
		DISTANCE[0][20] = 0;
		HAUTEUR[0][20] = 0;
		VISITE[0][20] = true;
		HAUTEUR[77][20] = 25;
	}

	private static void ligneSuivante() {
		LIGNE = LIGNES.get(NUMERO_LIGNE);
		NUMERO_LIGNE++;
	}
}