package fr.game.advent.advent2022.day09;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.game.advent.outils.FichierServices;

public class Day09_1 {

		private static String LIGNE = null;
		private static List<String> LIGNES = null;
		private static int NUMERO_LIGNE = 0;
		private static int X_HEAD = 0;
		private static int Y_HEAD = 0;
		private static int X_TAIL = 0;
		private static int Y_TAIL = 0;
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
				while (NUMERO_LIGNE < LIGNES.size()) {
					ligneSuivante();
					setDirection();
					setDistance();
					deplacerHeadEtTail();
				}
				LISTE_POSITIONS_VISITEES.forEach(System.out::println);
				long nombreDePositionsVisitees = LISTE_POSITIONS_VISITEES.stream().distinct().count();
				
				
				
				System.out.println(nombreDePositionsVisitees);


			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		private static void deplacerHeadEtTail() {
			System.out.println(DIRECTION + "" + DISTANCE);
			while (DISTANCE > 0) {
				switch (DIRECTION) {
				case 'U':
					Y_HEAD++;
					if (Y_HEAD == Y_TAIL+2) {
						Y_TAIL++;
						X_TAIL = X_HEAD;
					ajouterPositionVisitee();						
					}
					break;
				case 'D':
					Y_HEAD--;
					if (Y_HEAD == Y_TAIL-2) {
						Y_TAIL--;
						X_TAIL = X_HEAD;
					ajouterPositionVisitee();
					}
					break;
				case 'R':
					X_HEAD++;
					if (X_HEAD == X_TAIL+2) {
						X_TAIL++;
						Y_TAIL = Y_HEAD;
					ajouterPositionVisitee();
					}
					break;
				case 'L':
					X_HEAD--;
					if (X_HEAD == X_TAIL-2) {
						X_TAIL--;
						Y_TAIL = Y_HEAD;
					ajouterPositionVisitee();
					}
					break;
				}
				
				DISTANCE--;
			}
			System.out.println("x"+X_TAIL+"y"+Y_TAIL);
		}
		private static void ajouterPositionVisitee() {
			LISTE_POSITIONS_VISITEES.add("x"+X_TAIL+"y"+Y_TAIL);
		}
		private static void setDistance() {
			DISTANCE = Integer.parseInt(LIGNE.substring(2)) ;
		}
		private static void setDirection() {
			DIRECTION = LIGNE.charAt(0);
		}
		private static void ligneSuivante() {
			LIGNE = LIGNES.get(NUMERO_LIGNE);
			NUMERO_LIGNE++;
		}
	}
