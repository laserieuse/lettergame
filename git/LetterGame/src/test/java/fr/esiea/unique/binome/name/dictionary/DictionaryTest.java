package fr.esiea.unique.binome.name.dictionary;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test sample for Dictionary.
 */
public class DictionaryTest {




    @Test
    public void testIsWord() {
    	
    	Dictionnaire dictionnaire = new Dictionnaire();
        dictionnaire.addWord("maman");
        dictionnaire.addWord("papa");
        
        assertTrue("maman should be in the dictionnary",dictionnaire.isWordValid("maman"));
        assertTrue("papa should be in the dictionnary",dictionnaire.isWordValid("papa"));
    }
}
