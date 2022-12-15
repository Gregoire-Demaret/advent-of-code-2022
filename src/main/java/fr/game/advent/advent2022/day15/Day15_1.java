package fr.game.advent.advent2022.day15;

import java.io.IOException;
import java.util.List;

import fr.game.advent.outils.FichierServices;

public class Day15_1 {

	private static String LIGNE = null;
	private static List<String> LIGNES = null;
	private static int NUMERO_LIGNE = 0;
	private static char[] LIGNE_2M = new char[10000000];
	private static Integer X_SENSOR;
	private static Integer Y_SENSOR;
	private static Integer X_BEACON;
	private static Integer Y_BEACON;
	private static Integer Y_2M = 2000000;
	private static String LIGNE_INT;
	private static Integer DECALAGE_X = 5000000;

	public static void main(String[] args) throws IOException {
		try {
			FichierServices fichierServices = new FichierServices();

			LIGNES = fichierServices.lireFichierTexte(
					"C:\\INSEE\\WS\\Advent-of-code\\advent-of-code\\src\\main\\resources\\fr\\game\\advent\\advent2022\\day15\\input15-1.txt");

			initialiserLigne();
//			afficherLigne();

			while (NUMERO_LIGNE < LIGNES.size()) {
				System.out.println("Ligne :" + NUMERO_LIGNE);
				ligneSuivante();
				recupererCoordonnees();
				tracerPositions();
			}

			System.out.println();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static int compterPositions() {
		int compteur = 0;
		for (int i = 0; i < 10000000; i++) {
			if (LIGNE_2M[i] == '#') {
				compteur++;
			}
		}
		return compteur;
	}

	private static void tracerPositions() {
		Integer distanceYSensorY2M = Math.abs(Y_SENSOR - Y_2M);
		Integer distanceBeaconSensor = calculerDistanceBeaconSensor();
		int distanceRelative = distanceBeaconSensor - distanceYSensorY2M;
		int distanceRelativeInitiale = distanceRelative;
		while (distanceRelative >= 0) {
//			System.out.println("Y_S : " + Y_SENSOR + " Y_B : " + Y_BEACON + " distBS : " + distanceBeaconSensor + " distanceYSensorY2M : "+ distanceYSensorY2M + "distanceRelativeInitiale :" + distanceRelativeInitiale);
//			System.out.println("X_S : " + X_SENSOR + " X_B : " + X_BEACON + " distanceRleative : " + distanceRelative + " X_SENSOR + distanceRelative + DECALAGE_X : " + (X_SENSOR + distanceRelative + DECALAGE_X) + " X_SENSOR - distanceRelative + DECALAGE_X : " + (X_SENSOR - distanceRelative + DECALAGE_X));
			LIGNE_2M[X_SENSOR + distanceRelative + DECALAGE_X] = '#';
			LIGNE_2M[X_SENSOR - distanceRelative + DECALAGE_X] = '#';
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
			System.out.print(LIGNE_2M[x]);
		}
	}

	private static void initialiserLigne() {
		for (int x = 0; x < 10000000; x++) {
			LIGNE_2M[x] = '.';
		}
	}

	private static void ligneSuivante() {
		LIGNE = LIGNES.get(NUMERO_LIGNE);
		NUMERO_LIGNE++;
	}
}
