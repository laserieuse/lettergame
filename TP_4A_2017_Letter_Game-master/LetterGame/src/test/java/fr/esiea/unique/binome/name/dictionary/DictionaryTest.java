package fr.esiea.unique.binome.name.dictionary;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class DictionaryTest {
	
	private Dictionary dictionary = new Dictionary();

	@Test
	public void testIsWord() {
		assertTrue(dictionary.isWord("maman"));
		assertFalse(dictionary.isWord("cici"));
	}
}