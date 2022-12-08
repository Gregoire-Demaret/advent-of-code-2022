package fr.game.advent.advent2022.day08;

import java.io.IOException;
import java.util.List;

import fr.game.advent.outils.FichierServices;

public class Day08_2 {

	private static String LIGNE = null;
	private static List<String> LIGNES = null;
	private static int NUMERO_LIGNE = 0;
	private static int[][] GRILLE = new int[99][99];;
	private static int VALEUR_DE_VUE_MAX = 0;

	public static void main(String[] args) throws IOException {
		try {
			FichierServices fichierServices = new FichierServices();

			LIGNES = fichierServices.lireFichierTexte(
					"C:\\INSEE\\WS\\Advent-of-code\\advent-of-code\\src\\main\\resources\\fr\\game\\advent\\advent2022\\day08\\input08-1.txt");
			remplirGrille();
			calculerValeurDeLaVue();
			System.out.println(VALEUR_DE_VUE_MAX);

//				afficherGrille();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void calculerValeurDeLaVue() {

		for (int y = 0; y < 99; y++) {
			for (int x = 0; x < 99; x++) {
				int valeurActuelle = 0;
				valeurActuelle = comparerAGauche(x, y) * comparerADroite(x, y) * comparerEnHaut(x, y)
						* comparerEnBas(x, y);
				if (valeurActuelle > VALEUR_DE_VUE_MAX) {
					VALEUR_DE_VUE_MAX = valeurActuelle;
				}
			}

		}
	}

	private static int comparerEnBas(int x, int y) {
		int valeurVue = 0;
		int arbre = GRILLE[x][y];
		y++;
		while (y < 99) {
			valeurVue += 1;
			if (arbre <= GRILLE[x][y]) {
				break;
			}
			y++;
		}
		return valeurVue;
	}

	private static int comparerEnHaut(int x, int y) {
		int valeurVue = 0;
		int arbre = GRILLE[x][y];
		y--;
		while (y >= 0) {
			valeurVue += 1;
			if (arbre <= GRILLE[x][y]) {
				break;
			}
			y--;
		}
		return valeurVue;
	}

	private static int comparerADroite(int x, int y) {
		int valeurVue = 0;
		int arbre = GRILLE[x][y];
		x++;
		while (x < 99) {
			valeurVue += 1;
			if (arbre <= GRILLE[x][y]) {
				break;
			}
			x++;
		}
		return valeurVue;
	}

	private static int comparerAGauche(int x, int y) {
		int valeurVue = 0;
		int arbre = GRILLE[x][y];
		x--;
		while (x >= 0) {
			valeurVue += 1;
			if (arbre <= GRILLE[x][y]) {
				break;
			}
			x--;
		}
		return valeurVue;
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
