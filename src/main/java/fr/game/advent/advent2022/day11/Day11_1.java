package fr.game.advent.advent2022.day11;

import java.util.ArrayList;
import java.util.Arrays;

public class Day11_1 {

	private static ArrayList<Singe> LISTE_SINGES = new ArrayList<>();

	public static void main(String[] args) {

		ArrayList<Integer> objetsSinge0 = new ArrayList<Integer>(Arrays.asList(72, 97));
		ArrayList<Integer> objetsSinge1 = new ArrayList<Integer>(Arrays.asList(55, 70, 90, 74, 95));
		ArrayList<Integer> objetsSinge2 = new ArrayList<Integer>(Arrays.asList(74, 97, 66, 57));
		ArrayList<Integer> objetsSinge3 = new ArrayList<Integer>(Arrays.asList(86, 54, 53));
		ArrayList<Integer> objetsSinge4 = new ArrayList<Integer>(Arrays.asList(50, 65, 78, 50, 62, 99));
		ArrayList<Integer> objetsSinge5 = new ArrayList<Integer>(Arrays.asList(90));
		ArrayList<Integer> objetsSinge6 = new ArrayList<Integer>(Arrays.asList(88, 92, 63, 94, 96, 82, 53, 53));
		ArrayList<Integer> objetsSinge7 = new ArrayList<Integer>(Arrays.asList(70, 60, 71, 69, 77, 70, 98));

		Singe singe0 = new Singe(0, objetsSinge0, 5, 6, 19);
		Singe singe1 = new Singe(1, objetsSinge1, 5, 0, 7);
		Singe singe2 = new Singe(2, objetsSinge2, 1, 0, 17);
		Singe singe3 = new Singe(3, objetsSinge3, 1, 2, 13);
		Singe singe4 = new Singe(4, objetsSinge4, 3, 7, 11);
		Singe singe5 = new Singe(5, objetsSinge5, 4, 6, 2);
		Singe singe6 = new Singe(6, objetsSinge6, 4, 7, 5);
		Singe singe7 = new Singe(7, objetsSinge7, 2, 3, 3);

		LISTE_SINGES.add(singe0);
		LISTE_SINGES.add(singe1);
		LISTE_SINGES.add(singe2);
		LISTE_SINGES.add(singe3);
		LISTE_SINGES.add(singe4);
		LISTE_SINGES.add(singe5);
		LISTE_SINGES.add(singe6);
		LISTE_SINGES.add(singe7);

		for (int round = 0; round < 20; round++) {
			for (int i = 0; i < 8; i++) {
				Singe singe = LISTE_SINGES.get(i);
				tourSinge(singe);
			}
			
		}

		LISTE_SINGES.stream().map(Singe -> Singe.getNombreObjetsInspectes()).forEach(System.out::println);;
	}

	private static void tourSinge(Singe singe) {
		while (!singe.getObjets().isEmpty()) {
			singe.modifierObjet();
			Integer valeurALancer = singe.valeurObjet0();
			LISTE_SINGES.get(singe.lancerObjet()).addObjet(valeurALancer);
		}
	}

}
