package fr.esiea.unique.binome.name.dictionary;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit test sample for Dictionary.
 */
public class DictionaryTest 
{
    private IDictionary dictionary;

    @Before
    public void setup() 
    {
    	IDictionary dico = dictionary;
    }

    @Test
    public void testIsWord() 
    {
    	Dictionary dictionary = new Dictionary();
    	dictionary.addWord("papa");
    	dictionary.addWord("maman");
    	//assertTrue(dictionary.isWord("maman"));
        //assertFalse(dictionary.isWord("namam"));
        //assertFalse(dictionary.isWord("namam"));
    	//assertTrue("Papa should be in the dictionary");
    	
    }
    
}
