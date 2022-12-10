package fr.game.advent.advent2022.day08;

import java.io.IOException;
import java.util.List;

import fr.game.advent.outils.FichierServices;

public class Day08_1 {

	private static String LIGNE = null;
	private static List<String> LIGNES = null;
	private static int NUMERO_LIGNE = 0;
	private static int[][] GRILLE = new int[99][99];
	private static int NOMBRE_ARBRES_VISIBLES = 99 + 99 + 97 + 97;

	public static void main(String[] args) throws IOException {
		try {
			FichierServices fichierServices = new FichierServices();

			LIGNES = fichierServices.lireFichierTexte(
					"C:\\INSEE\\WS\\Advent-of-code\\advent-of-code\\src\\main\\resources\\fr\\game\\advent\\advent2022\\day08\\input08-1.txt");
			remplirGrille();
			arbreEstIlVisible();
			System.out.println(NOMBRE_ARBRES_VISIBLES);

//			afficherGrille();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void arbreEstIlVisible() {
		for (int y = 1; y < 98; y++) {
			for (int x = 1; x < 98; x++) {
				if (comparerAGauche(x, y) || comparerADroite(x, y) || comparerEnHaut(x, y) || comparerEnBas(x, y)) {
					NOMBRE_ARBRES_VISIBLES++;
				}
			}
		}
	}

	private static boolean comparerEnBas(int x, int y) {
		boolean comparateur = true;
		int arbre = GRILLE[x][y];
		y++;
		while (y < 99) {
			if (arbre <= GRILLE[x][y]) {
				comparateur = false;
			}
			y++;
		}
		return comparateur;
	}

	private static boolean comparerEnHaut(int x, int y) {
		boolean comparateur = true;
		int arbre = GRILLE[x][y];
		y--;
		while (y >= 0) {
			if (arbre <= GRILLE[x][y]) {
				comparateur = false;
			}
			y--;
		}
		return comparateur;
	}

	private static boolean comparerADroite(int x, int y) {
		boolean comparateur = true;
		int arbre = GRILLE[x][y];
		x++;
		while (x < 99) {
			if (arbre <= GRILLE[x][y]) {
				comparateur = false;
			}
			x++;
		}
		return comparateur;
	}

	private static boolean comparerAGauche(int x, int y) {
		boolean comparateur = true;
		int arbre = GRILLE[x][y];
		x--;
		while (x >= 0) {
			if (arbre <= GRILLE[x][y]) {
				comparateur = false;
			}
			x--;
		}
		return comparateur;
	}

	private static void remplirGrille() {
		for (int y = 0; y < 99; y++) {
			ligneSuivante();
			for (int x = 0; x < LIGNE.length(); x++) {
				GRILLE[x][y] = Character.getNumericValue(LIGNE.charAt(x));
			}
		}
	}

	private static void afficherGrille() {
		for (int y = 0; y < 99; y++) {
			for (int x = 0; x < 99; x++) {
				System.out.print(GRILLE[x][y]);
			}
			System.out.println();
		}
	}

	private static void ligneSuivante() {
		LIGNE = LIGNES.get(NUMERO_LIGNE);
		NUMERO_LIGNE++;
	}
}
