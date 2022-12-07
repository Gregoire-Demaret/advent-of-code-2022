package fr.game.advent.outils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FichierServices {

	public List<String> lireFichierTexte(String chemin) throws IOException {
		List<String> lines = null;
        Path filePath = Paths.get(chemin);
        Charset charset = StandardCharsets.UTF_8;
        try {
            lines = Files.readAllLines(filePath, charset);

        } catch (IOException ex) {
            System.out.format("I/O error: %s%n", ex);
        }
        return lines;
    }
	
}