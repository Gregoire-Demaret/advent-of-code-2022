package fr.game.advent.advent2022.day01;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Day01_2 {
	static Integer caloriesMax1 = 0;
	static Integer caloriesMax2 = 0;
	static Integer caloriesMax3 = 0;

	public static void main(String[] args) throws IOException {
				Integer sommeTemp = 0;
		try {
			FileInputStream source = new FileInputStream(
					"C:\\INSEE\\WS\\Advent-of-code\\advent-of-code\\src\\main\\resources\\fr\\game\\advent\\advent2022\\day01\\input01-1.txt");
			Scanner scanner = new Scanner(source);
			while (scanner.hasNextLine()) {

				String contenuLigne = scanner.nextLine();
				if (!contenuLigne.isEmpty()) {
					System.out.println("sommetemp = " + sommeTemp);
					System.out.println("contenuLigne = " + contenuLigne);
					sommeTemp += Integer.parseInt(contenuLigne);
				} else {
					if (sommeTemp > caloriesMax3) {
						nouveauMax(sommeTemp);
						sommeTemp = 0;
					} else {
						sommeTemp = 0;
					}
				}
			}
			scanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Integer result = caloriesMax1 + caloriesMax2 + caloriesMax3;
		System.out.println("Resultat final :" + result);

	}

	private static void nouveauMax(Integer sommeTemp) {
		if (sommeTemp > caloriesMax1) {
			caloriesMax3 = caloriesMax2;
			caloriesMax2 = caloriesMax1;
			caloriesMax1 = sommeTemp;
		} else {
			if (sommeTemp > caloriesMax2) {
				caloriesMax3 = caloriesMax2;
				caloriesMax2 = sommeTemp;
			} else {
				caloriesMax3 = sommeTemp;
			}

		}
	}
}
