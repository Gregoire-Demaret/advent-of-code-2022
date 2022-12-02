package fr.game.advent.advent2022.day02;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Day02_1 {
	static final char A_ROCK = 'A';
	static final char X_ROCK = 'X';
	static final char B_PAPER = 'B';
	static final char Y_PAPER = 'Y';
	static final char C_SCISSORS = 'C';
	static final char Z_SCISSORS = 'Z';
	static Integer LOSS = 0;
	static Integer DRAW = 3;
	static Integer WIN = 6;

	public static void main(String[] args) throws IOException {
		Integer points = 0;
		try {
			FileInputStream source = new FileInputStream(
					"C:\\INSEE\\WS\\Advent-of-code\\advent-of-code\\src\\main\\resources\\fr\\game\\advent\\day02\\input02-1.txt");
			Scanner scanner = new Scanner(source);
			while (scanner.hasNextLine()) {

				String contenuLigne = scanner.nextLine();
				char mainOpposant = contenuLigne.charAt(0);
				System.out.println(mainOpposant);
				char mainJoueur = contenuLigne.charAt(2);
				System.out.println(mainJoueur);
				points += comparerMains(mainOpposant, mainJoueur);
				System.out.println(points);
				points += pointsFormeJouee(mainJoueur);
				System.out.println(points);

			}
			scanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Resultat final :" + points);

	}

	private static Integer comparerMains(char mainOpposant, char mainJoueur) {
		Integer result = 0;
		switch (mainOpposant) {
		case A_ROCK:
			switch (mainJoueur) {
			case X_ROCK:
				result = DRAW;
				break;
			case Y_PAPER:
				result = WIN;
				break;
			case Z_SCISSORS:
				result = LOSS;
				break;
			}
			break;
		case B_PAPER:
			switch (mainJoueur) {
			case X_ROCK:
				result = LOSS;
				break;
			case Y_PAPER:
				result = DRAW;
				break;
			case Z_SCISSORS:
				result = WIN;
				break;
			}
			break;

		case C_SCISSORS:
			switch (mainJoueur) {
			case X_ROCK:
				result = WIN;
				break;
			case Y_PAPER:
				result = LOSS;
				break;
			case Z_SCISSORS:
				result = DRAW;
				break;
			}
		}
		return result;
	}

	private static Integer pointsFormeJouee(char mainJoueur) {
		Integer result = 0;
		switch (mainJoueur) {
		case X_ROCK:
			result = 1;
			break;
		case Y_PAPER:
			result = 2;
			break;
		case Z_SCISSORS:
			result = 3;
			break;
		}
		return result;
	}
}
