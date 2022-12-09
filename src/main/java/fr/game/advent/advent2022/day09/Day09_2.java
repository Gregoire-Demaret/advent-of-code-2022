package fr.game.advent.advent2022.day09;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.game.advent.outils.FichierServices;

public class Day09_2 {

	private static String LIGNE = null;
	private static List<String> LIGNES = null;
	private static int NUMERO_LIGNE = 0;

	private static int[] X = new int[10];
	private static int[] Y = new int[10];
	private static char DIRECTION = ' ';
	private static Integer DISTANCE = 0;
	private static List<String> LISTE_POSITIONS_VISITEES = new ArrayList<>();
	private static String POSITION = null;

	public static void main(String[] args) throws IOException {
		try {
			FichierServices fichierServices = new FichierServices();

			LIGNES = fichierServices.lireFichierTexte(
					"C:\\INSEE\\WS\\Advent-of-code\\advent-of-code\\src\\main\\resources\\fr\\game\\advent\\advent2022\\day09\\input09-1.txt");

			POSITION = "x0y0";
			LISTE_POSITIONS_VISITEES.add(POSITION);

			for (int i = 0; i < 10; i++) {
				X[i] = 0;
				Y[i] = 0;
			}

			while (NUMERO_LIGNE < LIGNES.size()) {
				ligneSuivante();
				setDirection();
				setDistance();
				deplacerNoeuds();

			}
			long nombreDePositionsVisitees = LISTE_POSITIONS_VISITEES.stream().distinct().count();
			System.out.println(nombreDePositionsVisitees);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void deplacerNoeuds() {
		while (DISTANCE > 0) {
			for (int i = 0; i < 9; i++) {

				if (i == 0 && DIRECTION == 'U') {
					Y[0]++;
				}
				if (i == 0 && DIRECTION == 'D') {
					Y[0]--;
				}
				if (i == 0 && DIRECTION == 'R') {
					X[0]++;
				}
				if (i == 0 && DIRECTION == 'L') {
					X[0]--;
				}
				testerDiagHautGauche(i);
				testerDiagHautDroite(i);
				testerDiagBasGauche(i);
				testerDiagBasDroite(i);
				testerHaut(i);
				testerBas(i);
				testerDroite(i);
				testerGauche(i);
			}
			DISTANCE--;
		}
	}

	private static void testerDiagBasDroite(int i) {
		if ((X[i] == X[i + 1] + 2) && (Y[i] == Y[i + 1] - 2)) {
			X[i + 1]++;
			Y[i + 1]--;
			if (i == 8) {
				ajouterPositionVisitee(X[i + 1], Y[i + 1]);
			}
		}
	}

	private static void testerDiagBasGauche(int i) {
		if ((X[i] == X[i + 1] - 2) && (Y[i] == Y[i + 1] - 2)) {
			X[i + 1]--;
			Y[i + 1]--;
			if (i == 8) {
				ajouterPositionVisitee(X[i + 1], Y[i + 1]);
			}
		}
	}

	private static void testerDiagHautDroite(int i) {
		if ((X[i] == X[i + 1] + 2) && (Y[i] == Y[i + 1] + 2)) {
			X[i + 1]++;
			Y[i + 1]++;
			if (i == 8) {
				ajouterPositionVisitee(X[i + 1], Y[i + 1]);
			}
		}
	}

	private static void testerDiagHautGauche(int i) {
		if ((X[i] == X[i + 1] - 2) && (Y[i] == Y[i + 1] + 2)) {
			X[i + 1]--;
			Y[i + 1]++;
			if (i == 8) {
				ajouterPositionVisitee(X[i + 1], Y[i + 1]);
			}
		}
	}

	private static void testerGauche(int i) {
		if (X[i] == X[i + 1] - 2) {
			X[i + 1]--;
			Y[i + 1] = Y[i];
			if (i == 8) {
				ajouterPositionVisitee(X[i + 1], Y[i + 1]);
			}
		}
	}

	private static void testerDroite(int i) {
		if (X[i] == X[i + 1] + 2) {
			X[i + 1]++;
			Y[i + 1] = Y[i];
			if (i == 8) {
				ajouterPositionVisitee(X[i + 1], Y[i + 1]);
			}
		}
	}

	private static void testerBas(int i) {
		if (Y[i] == Y[i + 1] - 2) {
			Y[i + 1]--;
			X[i + 1] = X[i];
			if (i == 8) {
				ajouterPositionVisitee(X[i + 1], Y[i + 1]);
			}
		}
	}

	private static void testerHaut(int i) {
		if (Y[i] == Y[i + 1] + 2) {
			Y[i + 1]++;
			X[i + 1] = X[i];
			if (i == 8) {
				ajouterPositionVisitee(X[i + 1], Y[i + 1]);
			}
		}
	}


	private static void ajouterPositionVisitee(int X_TAIL, int Y_TAIL) {
		LISTE_POSITIONS_VISITEES.add("x" + X_TAIL + "y" + Y_TAIL);
	}

	private static void setDistance() {
		DISTANCE = Integer.parseInt(LIGNE.substring(2));
	}

	private static void setDirection() {
		DIRECTION = LIGNE.charAt(0);
	}

	private static void ligneSuivante() {
		LIGNE = LIGNES.get(NUMERO_LIGNE);
		NUMERO_LIGNE++;
	}
}
