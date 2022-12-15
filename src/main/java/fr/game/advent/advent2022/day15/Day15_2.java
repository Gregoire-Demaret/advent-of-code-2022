package fr.game.advent.advent2022.day15;

import java.io.IOException;
import java.util.List;

import fr.game.advent.outils.FichierServices;

public class Day15_2 {

	private static String LIGNE = null;
	private static List<String> LIGNES = null;
	private static int NUMERO_LIGNE = 0;
	private static char[] LIGNE_TEST = new char[10000000];
	private static Integer X_SENSOR;
	private static Integer Y_SENSOR;
	private static Integer X_BEACON;
	private static Integer Y_BEACON;
	private static Integer Y_TEST = 0;
	private static String LIGNE_INT;
	private static Integer DECALAGE_X = 5000000;
	private static boolean BALISE_TROUVEE = false;
	private static int FREQUENCE;

	public static void main(String[] args) throws IOException {
		try {
			FichierServices fichierServices = new FichierServices();

			LIGNES = fichierServices.lireFichierTexte(
					"C:\\INSEE\\WS\\Advent-of-code\\advent-of-code\\src\\main\\resources\\fr\\game\\advent\\advent2022\\day15\\input15-1.txt");

//				afficherLigne();
			while (!BALISE_TROUVEE || Y_TEST <= 4000000) {
				initialiserLigneTest();
				NUMERO_LIGNE = 0;
				System.out.println("Y_TEST = " + Y_TEST);
				while (NUMERO_LIGNE < LIGNES.size()) {
//					System.out.println("Ligne :" + NUMERO_LIGNE);
					ligneSuivante();
					recupererCoordonnees();
					tracerPositions();
					chercherBalise();
					
					
					Y_TEST++;
				}
			}
			System.out.println(FREQUENCE);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void chercherBalise() {
		for (int i = 0; i<4000000; i++) {
			if (LIGNE_TEST[i] == '#') {
				FREQUENCE = (i+DECALAGE_X) * 4000000 + Y_TEST;
				BALISE_TROUVEE = true;
			}
		}
	}

	private static int compterPositions() {
		int compteur = 0;
		for (int i = 0; i < 10000000; i++) {
			if (LIGNE_TEST[i] == '#') {
				compteur++;
			}
		}
		return compteur;
	}

	private static void tracerPositions() {
		Integer distanceYSensorYTest = Math.abs(Y_SENSOR - Y_TEST);
		Integer distanceBeaconSensor = calculerDistanceBeaconSensor();
		int distanceRelative = distanceBeaconSensor - distanceYSensorYTest;
		int distanceRelativeInitiale = distanceRelative;
		while (distanceRelative >= 0) {
//				System.out.println("Y_S : " + Y_SENSOR + " Y_B : " + Y_BEACON + " distBS : " + distanceBeaconSensor + " distanceYSensorY2M : "+ distanceYSensorY2M + "distanceRelativeInitiale :" + distanceRelativeInitiale);
//				System.out.println("X_S : " + X_SENSOR + " X_B : " + X_BEACON + " distanceRleative : " + distanceRelative + " X_SENSOR + distanceRelative + DECALAGE_X : " + (X_SENSOR + distanceRelative + DECALAGE_X) + " X_SENSOR - distanceRelative + DECALAGE_X : " + (X_SENSOR - distanceRelative + DECALAGE_X));
			LIGNE_TEST[X_SENSOR + distanceRelative + DECALAGE_X] = '#';
			LIGNE_TEST[X_SENSOR - distanceRelative + DECALAGE_X] = '#';
			distanceRelative--;
		}
	}

	private static Integer calculerDistanceBeaconSensor() {
		Integer distanceBeaconSensor;
		distanceBeaconSensor = Math.abs(X_SENSOR - X_BEACON) + Math.abs(Y_SENSOR - Y_BEACON);
		return distanceBeaconSensor;
	}

	private static void recupererCoordonnees() {
		LIGNE_INT = extractInt(LIGNE).substring(1);

		int longueurNombre = longueurNombre(LIGNE_INT);

		X_SENSOR = Integer.parseInt(LIGNE_INT.substring(0, longueurNombre));
		LIGNE_INT = LIGNE_INT.substring(longueurNombre + 1);

		longueurNombre = longueurNombre(LIGNE_INT);
		Y_SENSOR = Integer.parseInt(LIGNE_INT.substring(0, longueurNombre));
		LIGNE_INT = LIGNE_INT.substring(longueurNombre + 1);

		longueurNombre = longueurNombre(LIGNE_INT);
		X_BEACON = Integer.parseInt(LIGNE_INT.substring(0, longueurNombre));
		LIGNE_INT = LIGNE_INT.substring(longueurNombre + 1);

		Y_BEACON = Integer.parseInt(LIGNE_INT);

	}

	private static int longueurNombre(String ligneInt) {
		int position = 0;
		while (ligneInt.charAt(position) != ' ') {
			position++;
		}
		return position;
	}

	static String extractInt(String string) {
		string = string.replaceAll("[^0-9]", " ");
		string = string.replaceAll(" +", " ");
		return string;
	}

	private static void afficherLigne() {
		for (int x = 0; x < 10000000; x++) {
			System.out.print(LIGNE_TEST[x]);
		}
	}

	private static void initialiserLigneTest() {
		for (int x = 0; x < 10000000; x++) {
			LIGNE_TEST[x] = '.';
		}
	}

	private static void ligneSuivante() {
		LIGNE = LIGNES.get(NUMERO_LIGNE);
		NUMERO_LIGNE++;
	}
}
