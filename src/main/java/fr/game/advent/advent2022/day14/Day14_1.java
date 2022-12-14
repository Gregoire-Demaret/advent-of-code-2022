package fr.game.advent.advent2022.day14;

import java.io.IOException;
import java.util.List;

import fr.game.advent.outils.FichierServices;

public class Day14_1 {
	private static String LIGNE = null;
	private static List<String> LIGNES = null;
	private static int NUMERO_LIGNE = 0;
	private static char[][] GRILLE = new char[200][200];
	private static Integer X_DEPART = 0;
	private static Integer Y_DEPART = 0;
	private static Integer X_ARRIVEE = 0;
	private static Integer Y_ARRIVEE = 0;
	private static boolean FIN_LIGNE = false;
	private static boolean TOUCHER_LE_FOND = false;
	private static Integer X_GRAIN;
	private static Integer Y_GRAIN;

	public static void main(String[] args) throws IOException {
		try {
			FichierServices fichierServices = new FichierServices();

			LIGNES = fichierServices.lireFichierTexte(
					"C:\\INSEE\\WS\\Advent-of-code\\advent-of-code\\src\\main\\resources\\fr\\game\\advent\\advent2022\\day14\\input14-1.txt");

			initialiserGrille();
			GRILLE[50][0] = '*';
			while (NUMERO_LIGNE < LIGNES.size()) {
				FIN_LIGNE = false;
				ligneSuivante();
				initialiserDepart();
				while (!FIN_LIGNE) {
					miseAJourCoordonnees();
					tracerLigne();
					miseAJourDepart();
				}

			}
			int nombreDeGrains = 0;
			while (!TOUCHER_LE_FOND) {
				nombreDeGrains++;
				genererSable();

			}
			afficherGrille();

			System.out.println(nombreDeGrains - 1);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void genererSable() {
		X_GRAIN = 50;
		Y_GRAIN = 0;
		boolean grainBouge = true;
		while (grainBouge && !TOUCHER_LE_FOND) {
			if (bougerEnBas()) {
			} else if (bougerEnBasAGauche()) {
			} else if (bougerEnBasADroite()) {
			} else {
				GRILLE[X_GRAIN][Y_GRAIN] = 'o';
				grainBouge = false;
			}
		}
	}

	private static boolean bougerEnBas() {
		if (verifierProfondeur()) {
			return true;
		} else if (GRILLE[X_GRAIN][Y_GRAIN + 1] == '.') {
			Y_GRAIN++;
			return true;
		} else
			return false;
	}

	private static boolean bougerEnBasAGauche() {
		if (GRILLE[X_GRAIN - 1][Y_GRAIN + 1] == '.') {
			Y_GRAIN++;
			X_GRAIN--;
			return true;
		} else
			return false;
	}

	private static boolean bougerEnBasADroite() {
		if (GRILLE[X_GRAIN + 1][Y_GRAIN + 1] == '.') {
			Y_GRAIN++;
			X_GRAIN++;
			return true;
		} else
			return false;
	}

	private static boolean verifierProfondeur() {
		if (Y_GRAIN == 199) {
			TOUCHER_LE_FOND = true;
			return true;
		} else {
			return false;
		}
	}

	private static void miseAJourDepart() {
		X_DEPART = X_ARRIVEE;
		Y_DEPART = Y_ARRIVEE;
	}

	private static void miseAJourCoordonnees() {
		X_ARRIVEE = Integer.parseInt(LIGNE.substring(0, 3)) - 450;
		if (LIGNE.length() > 7 && LIGNE.charAt(6) == ' ') {
			Y_ARRIVEE = Integer.parseInt(LIGNE.substring(4, 6));
			LIGNE = LIGNE.substring(10);
		} else if (LIGNE.length() > 7 && LIGNE.charAt(7) == ' ') {
			Y_ARRIVEE = Integer.parseInt(LIGNE.substring(4, 7));
			LIGNE = LIGNE.substring(11);
		} else if (LIGNE.length() == 6) {
			Y_ARRIVEE = Integer.parseInt(LIGNE.substring(4, 6));
			FIN_LIGNE = true;
		} else {
			Y_ARRIVEE = Integer.parseInt(LIGNE.substring(4, 7));
			FIN_LIGNE = true;
		}
	}

	private static void tracerLigne() {
		Integer distance;
		if (X_DEPART == X_ARRIVEE) {
			// tracer ligne verticale
			distance = Y_ARRIVEE - Y_DEPART;
			if (distance > 0) {
				for (int i = 0; i <= distance; i++) {
					GRILLE[X_DEPART][Y_DEPART + i] = '#';
				}
			} else {
				for (int i = 0; i >= distance; i--) {
					GRILLE[X_DEPART][Y_DEPART + i] = '#';
				}
			}
		} else {
			// tracer ligne horizontale
			distance = X_ARRIVEE - X_DEPART;
			if (distance > 0) {
				for (int i = 0; i <= distance; i++) {
					GRILLE[X_DEPART + i][Y_DEPART] = '#';
				}
			} else {
				for (int i = 0; i >= distance; i--) {
					GRILLE[X_DEPART + i][Y_DEPART] = '#';
				}
			}
		}
	}

	private static void initialiserDepart() {
		X_DEPART = Integer.parseInt(LIGNE.substring(0, 3)) - 450;
		if (LIGNE.charAt(6) == ' ') {
			Y_DEPART = Integer.parseInt(LIGNE.substring(4, 6));
			LIGNE = LIGNE.substring(10);
		} else {
			Y_DEPART = Integer.parseInt(LIGNE.substring(4, 7));
			LIGNE = LIGNE.substring(11);
		}
	}

	private static void afficherGrille() {
		for (int y = 0; y < 200; y++) {
			for (int x = 0; x < 200; x++) {
				System.out.print(GRILLE[x][y]);
			}
			System.out.println();
		}
	}

	private static void initialiserGrille() {
		for (int y = 0; y < 200; y++) {
			for (int x = 0; x < 200; x++) {
				GRILLE[x][y] = '.';
			}
		}
	}

	private static void ligneSuivante() {
		LIGNE = LIGNES.get(NUMERO_LIGNE);
		NUMERO_LIGNE++;
	}
}
