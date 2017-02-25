package fr.esiea.unique.binome.name.dictionary;

import org.junit.runner.manipulation.Filter;

public abstract class Dictionary implements IDictionary 
{
	/*private Cell origin = new Cell('0');
	
	public void addWord(String w)
	{
		Cell currentCell = origin;
		for (char currentLetter : w.toCharArray())
		{
			currentCell = currentCell.getOrCreate(currentLetter);
		}
		
	}*/
	
	private static Scanner scan;
	private BufferedReader br;

	public static void main(String[] args) {		

}
	
	
	
	public boolean isWordValid(String w)
	{
		/* Cell currentCell = origin;*/
		/*for (char currentLetter : w.toCharArray())*/
		
			/*currentCell = currentCell.get(currentLetter);
			if (currentCell == null)
			{
				return false;
			}*/
			
			/*Optional<Cell> optional = currentCell.get(currentLetter);
			if (!optional.isPresent())
			{
				return false;
			}
			
		} return currentCell.isEndOfWord();
	}*/
			try{

				FileReader fr = new Filter("src/main/resources/dico.txt");
				br = new BufferedReader(fr);
				String s;
				String wordDico = null;

				while((s = br.readLine()) != null) {
					scan = new Scanner(s);
					while (scan.hasNext()) {
						wordDico = scan.next().toLowerCase();
						if(wordDico.equals(w)) {
							System.out.println("Mot existant");
							return true;
						}
					}
				}
			br.close();
			fr.close();
			System.out.println("Mot inexistant");
			return false;

			} catch (Exception e){//Catch exception if any
			System.err.println("Error: " + e.getMessage());
			}
			return false;
	}



	@Override
	public boolean isWord(String word) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
