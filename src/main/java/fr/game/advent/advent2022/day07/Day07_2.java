package fr.game.advent.advent2022.day07;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.game.advent.outils.FichierServices;

public class Day07_2 {

	private static Integer NEXT_ID = 1;
	private static Dossier RACINE = new Dossier(0, "/", null, 0);
	private static Dossier DOSSIER_ACTUEL = RACINE;
	private static String LIGNE = null;
	private static boolean LIGNE_LUE = false;
	private static List<String> LIGNES = null;
	private static ArrayList<Dossier> LISTE_DOSSIERS = new ArrayList<>();
	private static int NUMERO_LIGNE = 1;

	public static void main(String[] args) throws IOException {
		try {
			FichierServices fichierServices = new FichierServices();
			LISTE_DOSSIERS.add(RACINE);

			LIGNES = fichierServices.lireFichierTexte(
					"C:\\INSEE\\WS\\Advent-of-code\\advent-of-code\\src\\main\\resources\\fr\\game\\advent\\advent2022\\day07\\input07-1.txt");
			while (NUMERO_LIGNE < LIGNES.size()) {
				if (LIGNE_LUE == false) {
					ligneSuivante();
				} else {
					LIGNE_LUE = false;
				}
				lireLigne();
			}
			calculerTailleDossiers();
			
			Integer tailleLibreActuelle = 70000000 - RACINE.getTaille();
			Integer tailleALiberer = 30000000 - tailleLibreActuelle;
			Integer tailleDossierIdeal = 0;
			tailleDossierIdeal = (LISTE_DOSSIERS.stream().mapToInt(Dossier -> Dossier.getTaille())
					.filter(Dossier -> Dossier >= tailleALiberer)).min().getAsInt();
			System.out.println(tailleALiberer);

			System.out.println(tailleDossierIdeal);
//				tailleCumulee = LISTE_DOSSIERS.stream().mapToInt(Dossier -> Dossier.getTaille()).limit(100000).sum();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void calculerTailleDossiers() {
		Integer profondeur = LISTE_DOSSIERS.stream().mapToInt(Dossier::getProfondeur).max().getAsInt();
		while (profondeur > 1) {
			traiterDossiersProfondeurActuelle(profondeur);
			profondeur--;
		}
	}

	private static void traiterDossiersProfondeurActuelle(Integer profondeur) {
		LISTE_DOSSIERS.stream().filter(Dossier -> Dossier.getProfondeur() == profondeur)
				.forEach(Dossier -> Dossier.ajouterTailleAuDossierParent());
	}

	private static void lireLigne() {

		if (LIGNE.substring(0, 4).equals("$ cd")) {
			if (LIGNE.substring(5, 6).equals("/")) {
				DOSSIER_ACTUEL = RACINE;
			} else if (LIGNE.substring(5, 7).equals("..")) {
				DOSSIER_ACTUEL = DOSSIER_ACTUEL.getDossierParent();
			} else {
				String NomDossierDestination = LIGNE.substring(5);
				DOSSIER_ACTUEL = LISTE_DOSSIERS.stream()
						.filter(Dossier -> Dossier.getNom().contains(NomDossierDestination))
						.filter(Dossier -> Dossier.getDossierParent() == DOSSIER_ACTUEL).findAny().get();
			}
		} else {
			if (NUMERO_LIGNE < LIGNES.size()) {
				ligneSuivante();
			}
			while (NUMERO_LIGNE <= LIGNES.size() && LIGNE.charAt(0) != '$') {
				if (LIGNE.substring(0, 3).equals("dir")) {
					creerDossier();
				}
				if (LIGNE.charAt(0) >= '0' && LIGNE.charAt(0) <= '9') {
					ajouterTailleAuDossier(LIGNE);
					if (NUMERO_LIGNE == 1032)
						break;
				}
				ligneSuivante();
				LIGNE_LUE = true;
			}
		}
	}

	private static void ajouterTailleAuDossier(String ligne) {
		int i = 0;
		while (ligne.charAt(i) != ' ') {
			i++;
		}
		DOSSIER_ACTUEL.setTaille(DOSSIER_ACTUEL.getTaille() + Integer.valueOf(ligne.substring(0, i)));
	}

	private static void creerDossier() {
		LISTE_DOSSIERS.add(new Dossier(NEXT_ID, LIGNE.substring(4), DOSSIER_ACTUEL, DOSSIER_ACTUEL.getProfondeur()));
		NEXT_ID++;
	}

	private static void ligneSuivante() {
		LIGNE = LIGNES.get(NUMERO_LIGNE);
		NUMERO_LIGNE++;
	}
}
