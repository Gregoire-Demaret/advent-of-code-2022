package fr.game.advent.advent2022.day05;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Day05_2 {

	public static void main(String[] args) throws IOException {
		try {


			FileInputStream source = new FileInputStream(
					"C:\\INSEE\\WS\\Advent-of-code\\advent-of-code\\src\\main\\resources\\fr\\game\\advent\\advent2022\\day05\\input05-1.txt");
			Scanner scanner = new Scanner(source);
			while (scanner.hasNextLine()) {

				String ligne = scanner.nextLine();


			}
			scanner.close();
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

}
