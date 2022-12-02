package fr.game.advent.advent2022.day02;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Day02_2 {

	static final char OPPONENT_ROCK = 'A';
	static final char X_LOOSE = 'X';
	static final char OPPONENT_PAPER = 'B';
	static final char Y_DRAW = 'Y';
	static final char OPPONENT_SCISSORS = 'C';
	static final char Z_WIN = 'Z';
	private static final char X_ROCK = 'R';
	private static final char Y_PAPER = 'P';
	private static final char Z_SCISSORS = 'S';
	static Integer LOSS = 0;
	static Integer DRAW = 3;
	static Integer WIN = 6;

	public static void main(String[] args) throws IOException {
		Integer points = 0;
		try {
			FileInputStream source = new FileInputStream(
					"C:\\INSEE\\WS\\Advent-of-code\\advent-of-code\\src\\main\\resources\\fr\\game\\advent\\advent2022\\day02\\input02-1.txt");
			Scanner scanner = new Scanner(source);
			while (scanner.hasNextLine()) {

				String contenuLigne = scanner.nextLine();
				char mainOpposant = contenuLigne.charAt(0);
				char mainJoueur = calculerMainAJouer(mainOpposant, contenuLigne.charAt(2));
				
				points += comparerMains(mainOpposant, mainJoueur);
				points += pointsFormeJouee(mainJoueur);

			}
			scanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Resultat final :" + points);

	}

	private static char calculerMainAJouer(char mainOpposant, char resultatAttendu) {
		char result = 0;
		switch (mainOpposant) {
		case OPPONENT_ROCK:
			switch (resultatAttendu) {
			case X_LOOSE:
				result = 'S';
				break;
			case Y_DRAW:
				result = 'R';
				break;
			case Z_WIN:
				result = 'P';
				break;
			}
			break;
		case OPPONENT_PAPER:
			switch (resultatAttendu) {
			case X_LOOSE:
				result = 'R';
				break;
			case Y_DRAW:
				result = 'P';
				break;
			case Z_WIN:
				result = 'S';
				break;
			}
			break;

		case OPPONENT_SCISSORS:
			switch (resultatAttendu) {
			case X_LOOSE:
				result = 'P';
				break;
			case Y_DRAW:
				result = 'S';
				break;
			case Z_WIN:
				result = 'R';
				break;
			}
		}
		return result;
	}

	private static Integer comparerMains(char mainOpposant, char mainJoueur) {
		Integer result = 0;
		switch (mainOpposant) {
		case OPPONENT_ROCK:
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
		case OPPONENT_PAPER:
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

		case OPPONENT_SCISSORS:
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
