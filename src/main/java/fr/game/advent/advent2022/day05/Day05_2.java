package fr.game.advent.advent2022.day05;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Day05_2 {

		static ArrayList<ArrayList<String>> listePiles = new ArrayList<ArrayList<String>>();
		static ArrayList<String> pile1 = new ArrayList<String>();
		static ArrayList<String> pile2 = new ArrayList<String>();
		static ArrayList<String> pile3 = new ArrayList<String>();
		static ArrayList<String> pile4 = new ArrayList<String>();
		static ArrayList<String> pile5 = new ArrayList<String>();
		static ArrayList<String> pile6 = new ArrayList<String>();
		static ArrayList<String> pile7 = new ArrayList<String>();
		static ArrayList<String> pile8 = new ArrayList<String>();
		static ArrayList<String> pile9 = new ArrayList<String>();
		static ArrayList<String> PILE_TEMP = new ArrayList<String>();

		public static void main(String[] args) throws IOException {
			try {

				listePiles.add(pile1);
				listePiles.add(pile2);
				listePiles.add(pile3);
				listePiles.add(pile4);
				listePiles.add(pile5);
				listePiles.add(pile6);
				listePiles.add(pile7);
				listePiles.add(pile8);
				listePiles.add(pile9);
				listePiles.add(PILE_TEMP);
				

				FileInputStream source = new FileInputStream(
						"C:\\INSEE\\WS\\Advent-of-code\\advent-of-code\\src\\main\\resources\\fr\\game\\advent\\advent2022\\day05\\input05-1.txt");
				Scanner scanner = new Scanner(source);

				String ligne = null;
				for (int i = 0; i < 8; i++) {
					ligne = scanner.nextLine();
					initialiserPiles(ligne);
				}
				retournerPiles();
				afficherPiles();

				scanner.nextLine();
				scanner.nextLine();
				while (scanner.hasNextLine()) {
					ligne = scanner.nextLine();
					int pileOrigine = 0;
					int pileDestination = 0;
					int nombreDeCaissesADeplacer = 0;
					nombreDeCaissesADeplacer = lireNombreDeCaisseADeplacer(ligne);
					int indexCaisseSurLigne = 13;
					if (nombreDeCaissesADeplacer > 9) {
						indexCaisseSurLigne++;
					}
					pileOrigine = lireIntDansLigne(ligne, indexCaisseSurLigne) - 1;
//					System.out.println(pileOrigine);
					pileDestination = lireIntDansLigne(ligne, indexCaisseSurLigne + 5) - 1;
//					System.out.println(pileDestination);
//					System.out.println("caisses a deplacer : " + nombreDeCaissesADeplacer);

					int compteur = nombreDeCaissesADeplacer;
					while (compteur > 0) {
						deplacerCaisse(pileOrigine, 9);
						compteur--;
						chiffreVersNomPile(pileOrigine).remove(chiffreVersNomPile(pileOrigine).size() - 1);
					}
					compteur = nombreDeCaissesADeplacer;
					while (compteur > 0) {
						deplacerCaisse(9, pileDestination);
						compteur--;
						chiffreVersNomPile(9).remove(chiffreVersNomPile(9).size() - 1);

					}
				}
				afficherPiles();
				scanner.close();
			} catch (IOException e) {
				e.printStackTrace();

			}
		}

		private static void deplacerCaisse(int pileOrigine, int pileDestination) {
			String caisseADeplacer = chiffreVersNomPile(pileOrigine).get((chiffreVersNomPile(pileOrigine).size()) - 1);
			chiffreVersNomPile(pileDestination).add(caisseADeplacer);

		}

		private static int lireNombreDeCaisseADeplacer(String ligne) {
			int nombreCaisses = 0;
			if (ligne.substring(6, 7).equals(" ")) {
				nombreCaisses = Character.getNumericValue(ligne.charAt(5));
			} else
				nombreCaisses = Integer.parseInt(ligne.substring(5, 7));

			return nombreCaisses;
		}

		private static int lireIntDansLigne(String ligne, int emplacement) {
			return Character.getNumericValue(ligne.charAt(emplacement - 1));
		}

		private static void afficherPiles() {
			int numeroPile = 0;
			for (int i = 0; i < 9; i++) {
				System.out.println("\npile " + numeroPile);
				chiffreVersNomPile(numeroPile).forEach(t -> System.out.print(t));
				numeroPile++;
			}
		}

		private static void retournerPiles() {
			int numeroPile = 0;
			for (int i = 0; i < 9; i++) {
				Collections.reverse(chiffreVersNomPile(numeroPile));
				numeroPile++;
			}
		}

		private static void initialiserPiles(String ligne) {
			rangerCaisseDansPile(ligne);
		}

		private static void rangerCaisseDansPile(String ligne) {
			int numeroPile = 0;
			String caisse = null;
			for (int i = 1; i < 35; i += 4) {
				if (!ligne.substring(i, i + 1).equals(" ")) {
					chiffreVersNomPile(numeroPile).add(ligne.substring(i, i + 1));
				}
				numeroPile++;
			}
		}

		private static ArrayList<String> chiffreVersNomPile(int numeroPile) {
			return listePiles.get(numeroPile);
		}
	}
