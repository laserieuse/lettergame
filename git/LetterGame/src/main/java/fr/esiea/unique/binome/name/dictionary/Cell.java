package fr.esiea.unique.binome.name.dictionary;

import java.util.HashMap;
import java.util.Map;

public class Cell {
	public static final char EOW='\0';
	private Map<Character,Cell> children=new HashMap<Character, Cell>();
	private char c;
	
//	public Cell(char c){
		this.c=c;
	}
	public Cell getOrCreate(char firstLetter){
		return children.computeIfAbsent(firstLetter)= new Cell(firstLetter));
		
		}
	public boolean isEndOfWord(){
		return children.get(EOW);
	}
}
