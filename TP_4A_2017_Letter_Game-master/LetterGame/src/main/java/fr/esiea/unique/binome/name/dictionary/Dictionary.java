package fr.esiea.unique.binome.name.dictionary;

import org.junit.runner.manipulation.Filter;

import java.io.BufferedReader;

import java.io.FileInputStream;
import java.io.IOException;

import java.io.InputStream;

import java.io.InputStreamReader;

public class Dictionary  {

	public BufferedReader OuvrirFichier(String motTester) {

		String fichier = "src/main/ressources/dico.txt";

		BufferedReader br = null;

		try {// Ouvrir fichier

			InputStream ips = new FileInputStream(fichier);

			InputStreamReader ipsr = new InputStreamReader(ips);

			br = new BufferedReader(ipsr);

		}

		catch (Exception e) {

			System.out.println(e.toString());

		}

		return br;

	}
	 @Override

		public String isWord (String motTest, BufferedReader br) throws IOException {

			String currentLine;

			do {

				currentLine = br.readLine();

				if (currentLine.equals(motTest) || currentLine.equals(null)) {

					// System.out.println("Le mot : " + currentLine + " a ete trouve
					// dans le dictionnaire");

					br.close();

					return currentLine;

				}
			} while (br.ready());

			System.out.println("Error Detected.");

			return "";

		}
	
}

