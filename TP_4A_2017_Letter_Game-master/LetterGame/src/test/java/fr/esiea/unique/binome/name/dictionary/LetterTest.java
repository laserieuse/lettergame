package fr.esiea.unique.binome.name.dictionary;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class LetterTest {
	
		private Letter letter = new Letter();
		
		@Test
		public void testMeilleurLettre() {
			char l1 = 'a';
			char l2 = 'b';
			assertTrue(letter.BestLetter(l1, l2));
			assertFalse(letter.BestLetter(l2, l1));
			assertTrue(letter.BestLetter(l1, l1));
		}
		
		@Test
		public void testLetterRandom() {
			String chars = "abcdefghijklmnopqrstuvwxyz";
		    char c = letter.letterRandom();
		    assertNotEquals(chars.lastIndexOf(c), -1);
		}
	}

