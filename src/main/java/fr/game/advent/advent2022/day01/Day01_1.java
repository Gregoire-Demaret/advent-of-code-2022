package fr.game.advent.advent2022.day01;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Day01_1 {
	public static void main(String[] args) throws IOException {
	   
		Integer caloriesMax = 0;
		Integer sommeTemp = 0;
		try
	    {
		FileInputStream source = new FileInputStream(
				"C:\\INSEE\\WS\\Advent-of-code\\advent-of-code\\src\\main\\resources\\fr\\game\\advent\\advent2022\\Day01\\input01-1.txt");
	    Scanner scanner = new Scanner(source);  
	      while(scanner.hasNextLine())
	      {
	    	  
	    	  String contenuLigne = scanner.nextLine();
	    	  if (!contenuLigne.isEmpty()) {
				sommeTemp += Integer.parseInt(contenuLigne);
			}
	    	  else {
				if (sommeTemp > caloriesMax) {
					caloriesMax = sommeTemp;
					sommeTemp = 0;
				}
				else {
					sommeTemp = 0;
				}
			}
	      }  
	      scanner.close(); }
	      catch(IOException e)
	      {
	        e.printStackTrace();
	      }
	      System.out.println("Resultat final :" + caloriesMax);
	    }
	}


