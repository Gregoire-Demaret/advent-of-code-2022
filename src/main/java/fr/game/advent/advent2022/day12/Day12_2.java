package fr.game.advent.advent2022.day12;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.game.advent.outils.FichierServices;

public class Day12_2 {

	private static String LIGNE = null;
	private static List<String> LIGNES = null;
	private static int NUMERO_LIGNE = 0;
	private static Integer[][] HAUTEUR_INITIALE = new Integer[101][41];
	private static Integer[][] HAUTEUR = new Integer[101][41];
	private static Integer[][] DISTANCE = new Integer[101][41];
	private static boolean[][] VISITE = new boolean[101][41];
	private static Integer COMPTEUR = 0;
	private static List<PointDeDepart> LISTE_POINTS_DE_DEPART = new ArrayList<>();
	private static PointDeDepart DEPART_ACTUEL = null;
	private static List<Integer> RESULTATS = new ArrayList<>();


	public static void main(String[] args) throws IOException {
		try {
			FichierServices fichierServices = new FichierServices();

			LIGNES = fichierServices.lireFichierTexte(
					"C:\\INSEE\\WS\\Advent-of-code\\advent-of-code\\src\\main\\resources\\fr\\game\\advent\\advent2022\\day12\\input12-1.txt");
			
			remplirGrilles();
			listerPointsDeDepart();

//				afficherGrille();

			while (!LISTE_POINTS_DE_DEPART.isEmpty()) {
				DEPART_ACTUEL = LISTE_POINTS_DE_DEPART.get(0);
				remplirGrillesSuivantes(DEPART_ACTUEL.getX(), DEPART_ACTUEL.getY());
				LISTE_POINTS_DE_DEPART.remove(0);
				// Tant que le point d'arrivée n'a pas été visité on parcourt la grille
				while (!VISITE[75][20] || COMPTEUR < 200) {

					parcourirGrille();
					COMPTEUR++;
					//				System.out.println("passe n° : " + COMPTEUR);
					
				} 
				RESULTATS.add(DISTANCE[77][20]);
			}
			System.out.println(Collections.max(RESULTATS));
			afficherGrille();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void listerPointsDeDepart() {
		for (int y = 0; y < 41; y++) {
			for (int x = 0; x < 100; x++) {
				if (HAUTEUR_INITIALE[x][y] == 0) {
					LISTE_POINTS_DE_DEPART.add(new PointDeDepart(x, y));
				}
			}

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

	private static void afficherGrille() {
		for (int y = 0; y < 41; y++) {
			for (int x = 0; x < 100; x++) {
				if (DISTANCE[x][y] < 10) {
					System.out.print("--" + DISTANCE[x][y]);

				} else if (DISTANCE[x][y] < 100) {
					System.out.print("-" + DISTANCE[x][y]);
				} else {
					System.out.print(DISTANCE[x][y]);
				}

			}
			System.out.println();
		}
	}
	private static void remplirGrilles() {
		for (int y = 0; y < 41; y++) {
			ligneSuivante();
			for (int x = 0; x < LIGNE.length() - 1; x++) {
				HAUTEUR_INITIALE[x][y] = LIGNE.charAt(x) - 97;
				DISTANCE[x][y] = Integer.MAX_VALUE;
				VISITE[x][y] = false;
			}
		}
		HAUTEUR_INITIALE[0][20] = 0;
		HAUTEUR_INITIALE[77][20] = 25;
	}
	private static void remplirGrillesSuivantes(int xDepart, int yDepart) {
		for (int y = 0; y < 41; y++) {
			for (int x = 0; x < LIGNE.length() - 1; x++) {
				HAUTEUR[x][y] = HAUTEUR_INITIALE[x][y];
				DISTANCE[x][y] = Integer.MAX_VALUE;
				VISITE[x][y] = false;
			}
		}
		DISTANCE[xDepart][yDepart] = 0;
		VISITE[xDepart][yDepart] = true;
	}

	private static void ligneSuivante() {
		LIGNE = LIGNES.get(NUMERO_LIGNE);
		NUMERO_LIGNE++;
	}
}