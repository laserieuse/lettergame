package fr.esiea.unique.binome.name.dictionary;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Cell
{
	private static final char EOW = '\0';
	// parcours arbre et cree le mot
	
	private char c ; 
	Map<Character,Cell> children = new HashMap<Character,Cell>();
	
	public Cell(char c )
	{
		this.c = c;
	}

	public Cell getOrCreate(char firstLetter)
	{
		/*
		 * Cell result = cells.get(firstLetter);
		 * if (result == null)
		 * {
		 *    cells.put(firstLetter, new Cell(firstLetter));
		 * }
		 * 
		 * return cells.getOrCreate(firstLetter);
		 */
		
		return children.computeIfAbsent(firstLetter, l -> new Cell(l));
	}
	
	public boolean isEndOfWord()
	{
		return c == EOW ; 
		//return children.get(EOW) ;
	}
	
	public Optional<Cell> get(char currentLetter)
	{
		return Optional.ofNullable(children.get(currentLetter));
	}
	
	
}
