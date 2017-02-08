package fr.esiea.unique.binome.name.dictionary;

import java.util.Optional;

public class Dictionary 
{
	private Cell origin = new Cell('0');
	
	public void addWord(String w)
	{
		Cell currentCell = origin;
		for (char currentLetter : w.toCharArray())
		{
			currentCell = currentCell.getOrCreate(currentLetter);
		}
		
	}
	
	
	
	public boolean isWordValid(String w)
	{
		Cell currentCell = origin;
		for (char currentLetter : w.toCharArray())
		{
			/*currentCell = currentCell.get(currentLetter);
			if (currentCell == null)
			{
				return false;
			}*/
			
			Optional<Cell> optional = currentCell.get(currentLetter);
			if (!optional.isPresent())
			{
				return false;
			}
			
		} return currentCell.isEndOfWord();
	}
	
	
}
