package fr.game.advent.advent2022.day11;

import java.util.ArrayList;
import java.util.Arrays;

public class Day11_2 {

	private static ArrayList<Singe2> LISTE_SINGES = new ArrayList<>();
	private static Integer MEGA_MODULO = 19 * 7 * 17 * 13 * 11 * 2 * 5 * 3;

	public static void main(String[] args) {

		ArrayList<Long> objetsSinge0 = new ArrayList<Long>(Arrays.asList(72L, 97L));
		ArrayList<Long> objetsSinge1 = new ArrayList<Long>(Arrays.asList(55L, 70L, 90L, 74L, 95L));
		ArrayList<Long> objetsSinge2 = new ArrayList<Long>(Arrays.asList(74L, 97L, 66L, 57L));
		ArrayList<Long> objetsSinge3 = new ArrayList<Long>(Arrays.asList(86L, 54L, 53L));
		ArrayList<Long> objetsSinge4 = new ArrayList<Long>(Arrays.asList(50L, 65L, 78L, 50L, 62L, 99L));
		ArrayList<Long> objetsSinge5 = new ArrayList<Long>(Arrays.asList(90L));
		ArrayList<Long> objetsSinge6 = new ArrayList<Long>(Arrays.asList(88L, 92L, 63L, 94L, 96L, 82L, 53L, 53L));
		ArrayList<Long> objetsSinge7 = new ArrayList<Long>(Arrays.asList(70L, 60L, 71L, 69L, 77L, 70L, 98L));

		Singe2 singe0 = new Singe2(0, objetsSinge0, 5, 6, 19);
		Singe2 singe1 = new Singe2(1, objetsSinge1, 5, 0, 7);
		Singe2 singe2 = new Singe2(2, objetsSinge2, 1, 0, 17);
		Singe2 singe3 = new Singe2(3, objetsSinge3, 1, 2, 13);
		Singe2 singe4 = new Singe2(4, objetsSinge4, 3, 7, 11);
		Singe2 singe5 = new Singe2(5, objetsSinge5, 4, 6, 2);
		Singe2 singe6 = new Singe2(6, objetsSinge6, 4, 7, 5);
		Singe2 singe7 = new Singe2(7, objetsSinge7, 2, 3, 3);

		LISTE_SINGES.add(singe0);
		LISTE_SINGES.add(singe1);
		LISTE_SINGES.add(singe2);
		LISTE_SINGES.add(singe3);
		LISTE_SINGES.add(singe4);
		LISTE_SINGES.add(singe5);
		LISTE_SINGES.add(singe6);
		LISTE_SINGES.add(singe7);

		for (int round = 0; round < 10000; round++) {
			for (int i = 0; i < 8; i++) {
				Singe2 singe = LISTE_SINGES.get(i);
				tourSinge(singe);
			}

		}

		LISTE_SINGES.stream().map(Singe -> Singe.getNombreObjetsInspectes()).forEach(System.out::println);
	}

	private static void tourSinge(Singe2 singe) {
		while (!singe.getObjets().isEmpty()) {
			singe.modifierObjet();
			Long valeurALancer = singe.valeurObjet0();
			LISTE_SINGES.get(singe.lancerObjet()).addObjet(valeurALancer % MEGA_MODULO);
		}
	}

}
