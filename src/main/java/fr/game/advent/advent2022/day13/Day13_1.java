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
	private static int DEBUT_A = 0;
	private static int FIN_A = 0;
	private static int DEBUT_B = 0;
	private static int FIN_B = 0;

	public static void main(String[] args) throws IOException {
		try {
			FichierServices fichierServices = new FichierServices();

			LIGNES = fichierServices.lireFichierTexte(
					"C:\\INSEE\\WS\\Advent-of-code\\advent-of-code\\src\\main\\resources\\fr\\game\\advent\\advent2022\\day13\\input13-1.txt");

			while (NUMERO_LIGNE < LIGNES.size()) {
				recupererLignes();
				effacerLigneVide();
				comparerPaires();
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
			if (LIGNE_B.length() < 3) {
				chaineBEstVide();
			} else if (LIGNE_A.length() < 3) {
				chaineAEstVide();
			} else
				comparerChaines();
		}
	}

	private static void comparerChaines() {
		CONTINUER_BOUCLES = true;
		System.out.println("Paire : " + INDICE_PAIRE);
//		System.out.println("LIGNE_A : " + LIGNE_A);
//		System.out.println("LIGNE_B : " + LIGNE_B);
		while (CONTINUER_BOUCLES) {
			recupererPremierGroupe();
			raccourcirLignes();
			if (CONTINUER_BOUCLES) {
				if (SOUS_CHAINE_B.length() == 2) {
					chaineBEstVide();
				} else if (SOUS_CHAINE_A.length() == 2) {
					chaineAEstVide();
					System.out.println("test");

				} else {
					System.out.println("entrée dans comparerSousChaines");
					comparerSousChaines();
				} 
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

		finA = trouverIndiceFin(LIGNE_A);
		finB = trouverIndiceFin(LIGNE_B);

		while (LIGNE_A.charAt(debutCommun) == '[' && LIGNE_B.charAt(debutCommun) == '[') {
			debutCommun++;
		}

		if (LIGNE_A.charAt(debutCommun) == '[') {
			if (LIGNE_A.charAt(debutCommun + 1) == '[') {
				chaineBEstVide();
			} else {
				debutA = debutCommun + 1;
			}
		} else {
			debutA = debutCommun;
		}
		SOUS_CHAINE_A = LIGNE_A.substring(debutA - 1, finA + 1);
		DEBUT_A = debutA;
		FIN_A = finA;

		if (LIGNE_B.charAt(debutCommun) == '[') {
			if (LIGNE_B.charAt(debutCommun + 1) == '[') {
				chaineAEstVide();
			} else {
				debutB = debutCommun + 1;
			}
		} else {
			debutB = debutCommun;
		}
		SOUS_CHAINE_B = LIGNE_B.substring(debutB - 1, finB + 1);
		DEBUT_B = debutB;
		FIN_B = finB;

		System.out.println("LIGNE_A : " + LIGNE_A + " SOUS_CHAINE_A : " + SOUS_CHAINE_A);
		System.out.println("LIGNE_B : " + LIGNE_B + " SOUS_CHAINE_B : " + SOUS_CHAINE_B);
	}

	private static void raccourcirLignes() {
//
//		System.out.println(LIGNE_A.substring(DEBUT_A, FIN_A));

		// raccourcir si caractère puis virgule
		if (LIGNE_A.length() > FIN_A + 2 && LIGNE_A.charAt(FIN_A) == ',') {
			LIGNE_A = LIGNE_A.substring(0, 1) + LIGNE_A.substring(FIN_A + 1);
			// raccourcir si caractère puis virgule
		} else if (LIGNE_A.length() > FIN_A + 3 && LIGNE_A.charAt(FIN_A + 1) == ',') {
			LIGNE_A = LIGNE_A.substring(0, 1) + LIGNE_A.substring(FIN_A + 2);
		} else {
			LIGNE_A = LIGNE_A.substring(0, DEBUT_A) + LIGNE_A.substring(FIN_A);

		}

		if (LIGNE_B.length() > FIN_B + 2 && LIGNE_B.charAt(FIN_B) == ',') {
			LIGNE_B = LIGNE_B.substring(0, 1) + LIGNE_B.substring(FIN_B + 1);
		} else if (LIGNE_B.length() > FIN_B + 3 && LIGNE_B.charAt(FIN_B + 1) == ',') {
			LIGNE_B = LIGNE_B.substring(0, 1) + LIGNE_B.substring(FIN_B + 2);
		} else {
			LIGNE_B = LIGNE_B.substring(0, DEBUT_B) + LIGNE_B.substring(FIN_B);

		}
	}

	private static void comparerSousChaines() {
		while (CONTINUER_BOUCLES) {
//			System.out.println(SOUS_CHAINE_A.charAt(1));
			if (SOUS_CHAINE_B.charAt(1) == ']') {

				chaineBEstVide();
			} else if (SOUS_CHAINE_A.charAt(1) == ']') {

				chaineAEstVide();
			} else {
				if (SOUS_CHAINE_A.charAt(1) == '[' && SOUS_CHAINE_B.charAt(1) == '[') {
					SOUS_CHAINE_A = SOUS_CHAINE_A.substring(1);
					SOUS_CHAINE_B = SOUS_CHAINE_B.substring(1);
					comparerSousChaines();
				}
				gerer10();
				if (!CONTINUER_BOUCLES) {
					break;
				}
				if (SOUS_CHAINE_A.charAt(1) == '[') {
					if (SOUS_CHAINE_A.charAt(2) < SOUS_CHAINE_B.charAt(1)) {
						coteGaucheEstPlusPetit();
					} else if (SOUS_CHAINE_A.charAt(2) > SOUS_CHAINE_B.charAt(1)) {
						coteDroitEstPlusPetit();
					}
				}
				if (SOUS_CHAINE_B.charAt(1) == '[') {
					if (SOUS_CHAINE_A.charAt(1) < SOUS_CHAINE_B.charAt(2)) {
						coteGaucheEstPlusPetit();
					} else if (SOUS_CHAINE_A.charAt(1) > SOUS_CHAINE_B.charAt(2)) {
						coteDroitEstPlusPetit();
					}
				} else if (SOUS_CHAINE_A.charAt(1) < SOUS_CHAINE_B.charAt(1)) {
					coteGaucheEstPlusPetit();
				} else if (SOUS_CHAINE_A.charAt(1) > SOUS_CHAINE_B.charAt(1)) {
					coteDroitEstPlusPetit();
				} else if (SOUS_CHAINE_A.length() < 4 && SOUS_CHAINE_B.length() < 4) {
					comparerChaines();
				} else
					raccourcirSousChaines();
			}
		}
//					comparerChaines();
	}

	private static void gerer10() {
		if (SOUS_CHAINE_A.charAt(1) == '1' && SOUS_CHAINE_A.charAt(2) == '0') {
			if (SOUS_CHAINE_B.charAt(1) == '1' && SOUS_CHAINE_B.charAt(2) == '0') {
				System.out.println("DIX OPTION DOUBLE 10");
				if (SOUS_CHAINE_A.charAt(3) == ',') {
					SOUS_CHAINE_A = "[" + SOUS_CHAINE_A.substring(4);
				} else if (SOUS_CHAINE_A.charAt(3) == ']' && SOUS_CHAINE_A.length() > 5) {
					SOUS_CHAINE_A = "[" + SOUS_CHAINE_A.substring(3);
				} else if (SOUS_CHAINE_A.charAt(3) == ']' && SOUS_CHAINE_A.length() <= 4) {
					SOUS_CHAINE_A = "[]";
				} else {
					SOUS_CHAINE_A = "[" + SOUS_CHAINE_A.substring(3);
				}
				if (SOUS_CHAINE_B.charAt(3) == ',') {
					SOUS_CHAINE_B = "[" + SOUS_CHAINE_B.substring(4);
				} else if (SOUS_CHAINE_B.charAt(3) == ']' && SOUS_CHAINE_B.length() > 5) {
					SOUS_CHAINE_B = "[" + SOUS_CHAINE_B.substring(3);
				} else if (SOUS_CHAINE_B.charAt(3) == ']' && SOUS_CHAINE_B.length() < 4) {
					SOUS_CHAINE_B = "[]";
				} else {
					SOUS_CHAINE_B = "[" + SOUS_CHAINE_B.substring(3);
				}
				System.out.println("A : " + SOUS_CHAINE_A + " B : " + SOUS_CHAINE_B);
				comparerSousChaines();
			} else {
				System.out.println("DIX OPTION 10 A GAUCHE");
				coteDroitEstPlusPetit();
			}
		}
		if (SOUS_CHAINE_B.charAt(1) == '1' && SOUS_CHAINE_B.charAt(2) == '0')

		{
			System.out.println("DIX OPTION 10 A DROITE");
			coteGaucheEstPlusPetit();

		}
	}

//	private static void raccourcirSousChaines() {
//		System.out.println("+LIGNE_A : " + LIGNE_A + " SOUS_CHAINE_A : " + SOUS_CHAINE_A);
//		System.out.println("+LIGNE_B : " + LIGNE_B + " SOUS_CHAINE_B : " + SOUS_CHAINE_B);
//		if (SOUS_CHAINE_A.charAt(SOUS_CHAINE_A.length() - 2) == ']') {
//			SOUS_CHAINE_A = "[" + SOUS_CHAINE_A.substring(3, SOUS_CHAINE_A.length() - 2) + "]";
//		} else if (SOUS_CHAINE_A.length() < 4) {
//			SOUS_CHAINE_A = "[]";
//		} else {
//			SOUS_CHAINE_A = "[" + SOUS_CHAINE_A.substring(3);
//		}
//
//		if (SOUS_CHAINE_B.charAt(SOUS_CHAINE_B.length() - 2) == ']') {
//			SOUS_CHAINE_B = "[" + SOUS_CHAINE_B.substring(3, SOUS_CHAINE_B.length() - 2) + "]";
//		} else if (SOUS_CHAINE_B.length() < 4) {
//			SOUS_CHAINE_B = "[]";
//		} else {
//			SOUS_CHAINE_B = "[" + SOUS_CHAINE_B.substring(3);
//		}
//
//		System.out.println("-LIGNE_A : " + LIGNE_A + " SOUS_CHAINE_A : " + SOUS_CHAINE_A);
//		System.out.println("-LIGNE_B : " + LIGNE_B + " SOUS_CHAINE_B : " + SOUS_CHAINE_B);
//
//	}

	private static void raccourcirSousChaines() {
		System.out.println("+LIGNE_A : " + LIGNE_A + " SOUS_CHAINE_A : " + SOUS_CHAINE_A);
		System.out.println("+LIGNE_B : " + LIGNE_B + " SOUS_CHAINE_B : " + SOUS_CHAINE_B);
		if (SOUS_CHAINE_A.charAt(SOUS_CHAINE_A.length() - 2) == ']') {
			SOUS_CHAINE_A = "[" + SOUS_CHAINE_A.substring(3, SOUS_CHAINE_A.length() - 2) + "]";
		} else if (SOUS_CHAINE_A.length() < 4) {
			SOUS_CHAINE_A = "[]";
		} else if (SOUS_CHAINE_A.charAt(3) == '[') {
			SOUS_CHAINE_A = "[" + SOUS_CHAINE_A.substring(4);
		} else {
			SOUS_CHAINE_A = "[" + SOUS_CHAINE_A.substring(3);
		}

		if (SOUS_CHAINE_B.charAt(SOUS_CHAINE_B.length() - 2) == ']') {
			SOUS_CHAINE_B = "[" + SOUS_CHAINE_B.substring(3, SOUS_CHAINE_B.length() - 2) + "]";
		} else if (SOUS_CHAINE_B.length() < 4) {
			SOUS_CHAINE_B = "[]";
		} else if (SOUS_CHAINE_B.charAt(3) == '[') {
				SOUS_CHAINE_B = "[" + SOUS_CHAINE_B.substring(4);
		} else {
			SOUS_CHAINE_B = "[" + SOUS_CHAINE_B.substring(3);

		}

		System.out.println("-LIGNE_A : " + LIGNE_A + " SOUS_CHAINE_A : " + SOUS_CHAINE_A);
		System.out.println("-LIGNE_B : " + LIGNE_B + " SOUS_CHAINE_B : " + SOUS_CHAINE_B);

	}

//	private static void raccourcirSousChaines() {
//		System.out.println("+LIGNE_A : " + LIGNE_A + " SOUS_CHAINE_A : " + SOUS_CHAINE_A);
//		System.out.println("+LIGNE_B : " + LIGNE_B + " SOUS_CHAINE_B : " + SOUS_CHAINE_B);
//		if (SOUS_CHAINE_A.charAt(SOUS_CHAINE_A.length() - 2) == ']') {
//			SOUS_CHAINE_A = "[" + SOUS_CHAINE_A.substring(3, SOUS_CHAINE_A.length() - 2) + "]";
//		} else if (SOUS_CHAINE_A.length() < 4) {
//			SOUS_CHAINE_A = "[]";
//		} else {
//			SOUS_CHAINE_A = "[" + SOUS_CHAINE_A.substring(3);
//		}
//
//		if (SOUS_CHAINE_B.charAt(SOUS_CHAINE_B.length() - 2) == ']') {
//			SOUS_CHAINE_B = "[" + SOUS_CHAINE_B.substring(3, SOUS_CHAINE_B.length() - 2) + "]";
//		} else if (SOUS_CHAINE_B.length() < 4) {
//			SOUS_CHAINE_B = "[]";
//		} else {
//			SOUS_CHAINE_B = "[" + SOUS_CHAINE_B.substring(3);
//		}
//
//		System.out.println("-LIGNE_A : " + LIGNE_A + " SOUS_CHAINE_A : " + SOUS_CHAINE_A);
//		System.out.println("-LIGNE_B : " + LIGNE_B + " SOUS_CHAINE_B : " + SOUS_CHAINE_B);
//
//	}

	private static void chaineBEstVide() {
		resultatChaineDansLeMauvaisOrdre();
		System.out.println("chaineBEstVide");
	}

	private static void chaineAEstVide() {
		resultatChaineDansLeBonOrdre();
		System.out.println("chaineAEstVide");
	}

	private static void coteDroitEstPlusPetit() {
		resultatChaineDansLeMauvaisOrdre();
		System.out.println("coteDroitEstPlusPetit");
	}

	private static void coteGaucheEstPlusPetit() {
		resultatChaineDansLeBonOrdre();
		System.out.println("coteGaucheEstPlusPetit");
	}

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
