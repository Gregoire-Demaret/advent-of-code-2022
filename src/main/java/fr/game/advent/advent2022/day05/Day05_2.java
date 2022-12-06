package fr.game.advent.advent2022.day05;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Day05_2 {

	static ArrayList<ArrayList<String>> LISTE_PILES = new ArrayList<ArrayList<String>>();
	static ArrayList<String> PILE_1 = new ArrayList<String>();
	static ArrayList<String> PILE_2 = new ArrayList<String>();
	static ArrayList<String> PILE_3 = new ArrayList<String>();
	static ArrayList<String> PILE_4 = new ArrayList<String>();
	static ArrayList<String> PILE_5 = new ArrayList<String>();
	static ArrayList<String> PILE_6 = new ArrayList<String>();
	static ArrayList<String> PILE_7 = new ArrayList<String>();
	static ArrayList<String> PILE_8 = new ArrayList<String>();
	static ArrayList<String> PILE_9 = new ArrayList<String>();
	static ArrayList<String> PILE_TEMP = new ArrayList<String>();

	public static void main(String[] args) throws IOException {
		try {

			LISTE_PILES.add(PILE_1);
			LISTE_PILES.add(PILE_2);
			LISTE_PILES.add(PILE_3);
			LISTE_PILES.add(PILE_4);
			LISTE_PILES.add(PILE_5);
			LISTE_PILES.add(PILE_6);
			LISTE_PILES.add(PILE_7);
			LISTE_PILES.add(PILE_8);
			LISTE_PILES.add(PILE_9);
			LISTE_PILES.add(PILE_TEMP);

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
				pileDestination = lireIntDansLigne(ligne, indexCaisseSurLigne + 5) - 1;

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
		for (int i = 1; i < 35; i += 4) {
			if (!ligne.substring(i, i + 1).equals(" ")) {
				chiffreVersNomPile(numeroPile).add(ligne.substring(i, i + 1));
			}
			numeroPile++;
		}
	}

	private static ArrayList<String> chiffreVersNomPile(int numeroPile) {
		return LISTE_PILES.get(numeroPile);
	}
}
