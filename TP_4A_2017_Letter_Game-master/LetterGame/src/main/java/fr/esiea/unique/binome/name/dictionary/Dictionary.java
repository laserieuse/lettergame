package fr.esiea.unique.binome.name.dictionary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 * @auteur ndeye Amy 
 *
 */

public class Dictionary {
	private String path;
	
	public Dictionary(){
		this.path = System.getProperty("user.dir")+"/target/classes/dico.txt";
	}
	
	public Dictionary(String path) {
		this.path = path;
   }
	/**
	 * Recherche le mot dans le dictionnaire
	 * @param Word
	 * @return
	 */
	
	public boolean isWord(String word) {
		try {
			File f = new File(path); 
			BufferedReader bfReader = new BufferedReader(new FileReader(f)); 	
			String l;
			while((l = bfReader.readLine()) != null) { 
				if(l.contains(word) && l.equals(word)) {
					bfReader.close();
					return true;
				}
			}
			bfReader.close();
		} catch (IOException e) {
			System.out.println("Erreur dans le dictionnaire"); 
			e.printStackTrace();
		}		
		System.out.println("Le mot "+word +" introuvablr");
		return false;
	}
	/**
	 * mots du dictionnaire
	 * @return
	 */
	public ArrayList<String> getWord() {
		File f = new File(path); 
		BufferedReader bfReader;
		ArrayList<String> word = new ArrayList<String>();
		try {
			bfReader = new BufferedReader(new FileReader(f));
			String l;
			while((l = bfReader.readLine()) != null) { 
				word.add(l);
			}
			bfReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return word;
	}
}
	