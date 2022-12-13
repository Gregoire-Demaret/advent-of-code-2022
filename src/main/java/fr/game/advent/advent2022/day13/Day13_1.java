package fr.game.advent.advent2022.day13;

import java.io.IOException;
import java.util.List;

import fr.game.advent.outils.FichierServices;

public class Day13_1 {

	private static String LIGNE = null;
	private static List<String> LIGNES = null;
	private static int NUMERO_LIGNE = 0;
	private static String LIGNE_A = null;
	private static String LIGNE_B = null;
	private static Integer INDICE_PAIRE = 0;
	private static Integer RESULTAT = 0;
	private static boolean CONTINUER_BOUCLES = true;
	private static String SOUS_CHAINE_A = "";
	private static String SOUS_CHAINE_B = "";

	public static void main(String[] args) throws IOException {
		try {
			FichierServices fichierServices = new FichierServices();

			LIGNES = fichierServices.lireFichierTexte(
					"C:\\INSEE\\WS\\Advent-of-code\\advent-of-code\\src\\main\\resources\\fr\\game\\advent\\advent2022\\day13\\input13-test.txt");

			while (NUMERO_LIGNE < LIGNES.size()) {
				recupererLignes();

				comparerPaires();

				effacerLigneVide();
				System.out.println(RESULTAT);
			}

			System.out.println(RESULTAT);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void effacerLigneVide() {
		if (NUMERO_LIGNE < LIGNES.size()) {
			ligneSuivante();
		}
	}

	private static void comparerPaires() {
		CONTINUER_BOUCLES = true;
		while (CONTINUER_BOUCLES) {
			if (LIGNE_A.isEmpty()) {
				resultatChaineDansLeBonOrdre();
			} else if (LIGNE_B.isEmpty()) {
				resultatChaineDansLeMauvaisOrdre();
			} else
				comparerChaines();
		}
	}

	private static void comparerChaines() {
		CONTINUER_BOUCLES = true;
		while (CONTINUER_BOUCLES) {
			System.out.println("Paire : " + INDICE_PAIRE);

			System.out.println("LIGNE_A : " + LIGNE_A);
			System.out.println("LIGNE_B : " + LIGNE_B);
			if (SOUS_CHAINE_A.length() == 2) {
				resultatChaineDansLeBonOrdre();
			} else if (SOUS_CHAINE_B.length() == 2) {
				resultatChaineDansLeMauvaisOrdre();
			} else {
				recupererPremierGroupe();
				comparerSousChaines();

			}
		}
	}

	private static void recupererPremierGroupe() {
		String a = "";
		String b = "";

		int debutCommun = 1;
		int debutA = 1;
		int debutB = 1;
		int finA = 0;
		int finB = 0;

		while (LIGNE_A.charAt(debutCommun) == '[' && LIGNE_B.charAt(debutCommun) == '[') {
			debutCommun++;
		}

		if (LIGNE_A.charAt(debutCommun) == '[') {
			debutA = debutCommun + 1;
		} else {
			debutA = debutCommun;
		}
		finA = trouverIndiceFin(LIGNE_A);
		a = LIGNE_A.substring(debutA - 1, finA + 1);
		if (LIGNE_A.length() > finA + 1) {
			if (LIGNE_A.charAt(finA + 1) == ',') {
				LIGNE_A = LIGNE_A.substring(debutA - 1, debutA) + LIGNE_A.substring(finA + 2);
			} else {
				LIGNE_A = LIGNE_A.substring(debutA - 1, debutA) + LIGNE_A.substring(finA + 1);
			}
		}
		SOUS_CHAINE_A = a;

		if (LIGNE_B.charAt(debutCommun) == '[') {
			debutB = debutCommun + 1;
		} else {
			debutB = debutCommun;
		}
		finB = trouverIndiceFin(LIGNE_B);
		b = LIGNE_B.substring(debutB - 1, finB + 1);
		if (LIGNE_B.length() > finB + 1) {
			if (LIGNE_B.charAt(finB + 1) == ',') {
				LIGNE_B = LIGNE_B.substring(debutB - 1, debutB) + LIGNE_B.substring(finB + 2);
			} else {
				LIGNE_B = LIGNE_B.substring(debutB - 1, debutB) + LIGNE_B.substring(finB + 1);
			}
		}
//		b = LIGNE_B.substring(debutCommun - 1, finB + 1);
//		if (LIGNE_B.length() > finB + 1) {
//			if (LIGNE_B.charAt(finB + 1) == ',') {
//				LIGNE_B = LIGNE_B.substring(0, debutCommun) + LIGNE_B.substring(finB + 2);
//			} else {
//				LIGNE_B = LIGNE_B.substring(0, debutCommun) + LIGNE_B.substring(finB + 1);
//			}
//		}
		SOUS_CHAINE_B = b;
		System.out.println("LIGNE_A : " + LIGNE_A + " SOUS_CHAINE_A : " + SOUS_CHAINE_A);
		System.out.println("LIGNE_B : " + LIGNE_B + " SOUS_CHAINE_B : " + SOUS_CHAINE_B);
	}

	private static void comparerSousChaines() {
		while (CONTINUER_BOUCLES) {
			if (SOUS_CHAINE_A.charAt(1) == ']') {
				chaineAEstVide();
				break;
			} else if (SOUS_CHAINE_B.charAt(1) == ']') {
				chaineBEstVide();
				break;
			} else if (SOUS_CHAINE_A.charAt(1) == '[') {

				if (SOUS_CHAINE_A.charAt(2) < SOUS_CHAINE_B.charAt(1)) {
					coteGaucheEstPlusPetit();
				} else if (SOUS_CHAINE_A.charAt(2) > SOUS_CHAINE_B.charAt(1)) {
					coteDroitEstPlusPetit();
				}
			} else if (SOUS_CHAINE_B.charAt(1) == '[') {
				if (SOUS_CHAINE_A.charAt(1) < SOUS_CHAINE_B.charAt(2)) {
					coteGaucheEstPlusPetit();
				} else if (SOUS_CHAINE_A.charAt(1) > SOUS_CHAINE_B.charAt(2)) {
					coteDroitEstPlusPetit();
				}
			} else if (SOUS_CHAINE_A.charAt(1) < SOUS_CHAINE_B.charAt(1)) {
				coteGaucheEstPlusPetit();
			} else if (SOUS_CHAINE_A.charAt(1) > SOUS_CHAINE_B.charAt(1)) {
				coteDroitEstPlusPetit();
			} else
//			} else if (SOUS_CHAINE_B.charAt(1) == '[') {
//				if (SOUS_CHAINE_A.charAt(0) < SOUS_CHAINE_B.charAt(1)) {
//					coteGaucheEstPlusPetit();
//				} else if (SOUS_CHAINE_A.charAt(0) > SOUS_CHAINE_B.charAt(1)) {
//					coteDroitEstPlusPetit();
//				} else {
//					chaineAEstVide();
//				}
//			} else if (SOUS_CHAINE_A.charAt(0) < SOUS_CHAINE_B.charAt(0)) {
//				coteGaucheEstPlusPetit();
//			} else if (SOUS_CHAINE_A.charAt(0) > SOUS_CHAINE_B.charAt(0)) {
//				coteDroitEstPlusPetit();
//			} else 
			{
				if (SOUS_CHAINE_A.length() > 3) {
					SOUS_CHAINE_A = "[" + SOUS_CHAINE_A.substring(3);
					SOUS_CHAINE_B = "[" + SOUS_CHAINE_B.substring(3);
					System.out.println("LIGNE_A : " + LIGNE_A + " SOUS_CHAINE_A : " + SOUS_CHAINE_A);
					System.out.println("LIGNE_B : " + LIGNE_B + " SOUS_CHAINE_B : " + SOUS_CHAINE_B);
				}

			}

		}

	}

	private static void chaineBEstVide() {
		resultatChaineDansLeMauvaisOrdre();
	}

	private static void chaineAEstVide() {
		resultatChaineDansLeBonOrdre();
	}

	private static void coteDroitEstPlusPetit() {
		resultatChaineDansLeMauvaisOrdre();
	}

	private static void coteGaucheEstPlusPetit() {
		resultatChaineDansLeBonOrdre();
	}

//	private static String recupererA() {
//		String a = "";
//		int debut = 0;
//		int fin = 0;
//		if (LIGNE_A.charAt(1) == '[') {
//			debut = 1;
//		}
//		fin = trouverIndiceFin(LIGNE_A);
//
//		a = LIGNE_A.substring(debut, fin + 1);
//		if (LIGNE_A.length() > fin + 1) {
//			if (LIGNE_A.charAt(fin) == ',') {
//				LIGNE_A = LIGNE_A.substring(0, debut) + LIGNE_A.substring(fin + 2);
//			} else {
//				LIGNE_A = LIGNE_A.substring(0, debut) + LIGNE_A.substring(fin + 1);
//			}
//		}
//		return a;
//	}
//
//	private static String recupererB() {
//		String b = "";
//		int debut = 0;
//		int fin = 0;
//		if (LIGNE_B.charAt(1) == '[') {
//			debut = 1;
//		}
//		fin = trouverIndiceFin(LIGNE_B);
//
//		b = LIGNE_B.substring(debut, fin + 1);
//		if (LIGNE_B.length() > fin + 1) {
//			if (LIGNE_B.charAt(fin) == ',') {
//				LIGNE_B = LIGNE_B.substring(0, debut) + LIGNE_B.substring(fin + 2);
//			} else {
//				LIGNE_B = LIGNE_B.substring(0, debut) + LIGNE_B.substring(fin + 1);
//			}
//		}
//		return b;
//	}

	private static int trouverIndiceFin(String ligne) {
		int fin = 0;
		for (int i = 1; i < ligne.length(); i++) {
			int sousGroupes = 0;
			if (ligne.charAt(i) == '[') {
				sousGroupes++;
			}
			if (ligne.charAt(i) == ']') {
				if (sousGroupes == 0) {
					fin = i;
					break;
				} else
					sousGroupes--;
			}
		}
		return fin;
	}

	private static void resultatChaineDansLeMauvaisOrdre() {
		CONTINUER_BOUCLES = false;
	}

	private static void resultatChaineDansLeBonOrdre() {
		RESULTAT += INDICE_PAIRE;
		CONTINUER_BOUCLES = false;
	}

	private static void recupererLignes() {
		ligneSuivante();
		LIGNE_A = LIGNE;
		ligneSuivante();
		LIGNE_B = LIGNE;
		INDICE_PAIRE++;
	}

	private static void ligneSuivante() {
		LIGNE = LIGNES.get(NUMERO_LIGNE);
		NUMERO_LIGNE++;
	}
}
