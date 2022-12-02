package fr.game.advent.outils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LireFichier {
	public static String texte = "";

	public static String lireFichierTexte(File fichierEntree) throws IOException {
		String texte = "";
		FileReader fr = new FileReader(fichierEntree);
		try (BufferedReader br = new BufferedReader(fr)) {
			int c = 0;
			while ((c = br.read()) != -1) {
				c--;
				char ch = (char) c;
				System.out.print(ch);
				texte = texte + ch;

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return texte;
	}

//	static ArrayList<Integer> lireFichierLigneParLigne(Path chemin) throws IOException {
//		
//		Files.lines(chemin)
//	    .Arrays(ligne -> ligne.split("\\s+"))
//	    .flatMap(Arrays::stream)
//	    .map(Integer::new)
//	    .collect(arr(Integer::hashCode, (Integer o)->o));
//	}

}
