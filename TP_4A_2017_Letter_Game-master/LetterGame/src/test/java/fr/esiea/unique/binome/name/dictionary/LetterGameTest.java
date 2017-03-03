package fr.esiea.unique.binome.name.dictionary;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class LetterGameTest {
		
		private Player player1=new Player();
		private Player player2=new Player();
		private Player computer=new Player();
		private Dictionary dictionary=new Dictionary();
		private LetterGame letterGame = new LetterGame();
		

		@Test
		public void testInitPseudos() {
			player1.setPseudo("pseudo1");
			player2.setPseudo("pseudo1");
			assertNotEquals(player1.getPseudo(), null);
			assertNotEquals(player2.getPseudo(), null);
		}

		@Test
		public void testInitPseudo() {
			player1.setPseudo("pseudo1");
			computer.setPseudo("pseudoIA");
			assertNotEquals(player1.getPseudo(), null);
			assertNotEquals(computer.getPseudo(), null);
		}

		@Test
		public void testPioche() {
			letterGame.pioche(player1);
			assertTrue(letterGame.getLettreSurTable().size() >= 1);
		}

		@Test
		public void testFaitUnMot() {
			letterGame.getLettreSurTable().add('t');
			letterGame.getLettreSurTable().add('e');
			letterGame.getLettreSurTable().add('s');
			letterGame.getLettreSurTable().add('t');
			String mot = "test";
			if (letterGame.wordPossible(mot, player1)) {
				player1.setPlayerWord(mot);
				assertTrue(player1.getPlayerWord().size() == 1);
			} else {
				assertFalse(player1.getPlayerWord().size() == 1);
			}
		}

		@Test
		public void testMotPossible() {
			letterGame.getLettreSurTable().add('t');
			letterGame.getLettreSurTable().add('e');
			letterGame.getLettreSurTable().add('s');
			letterGame.getLettreSurTable().add('t');
			assertTrue(letterGame.wordPossible("test", player1));
		}

		@Test
		public void testFaitUnMotAvecUnMot() {
			letterGame.getLettreSurTable().add('a');
			letterGame.getLettreSurTable().add('b');
			letterGame.getLettreSurTable().add('e');
			letterGame.getLettreSurTable().add('r');
			player1.getPlayerWord().add("ration");
			for (int i = 0; i < player1.getPlayerWord().get(0).length(); i++) {
				letterGame.getLettreSurTable().add(player1.getPlayerWord().get(0).charAt(i));
			}
			String mot = "aberration";
			if (letterGame.wordPossible(mot, player1)) {
				player1.getPlayerWord().remove("ration");
				player1.setPlayerWord(mot);
			}
			assertTrue(player1.getPlayerWord().get(0).equals("aberration"));
		}

		@Test
		public void testFaitUnMotAvecDeuxMot() {
			player1.setPlayerWord("aber");
			player2.setPlayerWord("ration");
			String mot = "";
			for (int i = 0; i < player1.getPlayerWord().size(); i++) {
				for (int j = 0; j < player2.getPlayerWord().size(); j++) {
					mot = player1.getPlayerWord().get(i).concat(player2.getPlayerWord().get(j));
					if (dictionary.isWord(mot)) {
						player1.getPlayerWord().remove(player1.getPlayerWord().get(i));
						player2.getPlayerWord().remove(player2.getPlayerWord().get(j));
						player1.setPlayerWord(mot);
					}
				}
			}
			assertTrue(player1.getPlayerWord().get(0).equals(mot));
		}

		@Test
		public void testRemoveUnMot() {
			player1.getPlayerWord().add("test");
			letterGame.removeUnMot(player1, player2, "test");
			assertTrue(player1.getPlayerWord().size() == 0);
		}

		@Test
		public void testTestGagnant() {
			for (int i = 0; i <= 9; i++) {
				player1.getPlayerWord().add("test" + i);
			}
			letterGame.testGagnant(player1);
			assertTrue(player1.getWinner() == true);
		}

		@Test
		public void motPossibleParIA() {
			letterGame.getLettreSurTable().add('m');
			letterGame.getLettreSurTable().add('a');
			letterGame.getLettreSurTable().add('m');
			letterGame.getLettreSurTable().add('a');
			letterGame.getLettreSurTable().add('n');
			assertTrue(letterGame.wordPossibleParIA().contains("maman"));
		}

		@Test
		public void testFaitUnMotIA() {
			List<String> listeDeMots = new ArrayList<String>();
			String motAFaire = "";
			listeDeMots.add("man");
			listeDeMots.add("manan");
			listeDeMots.add("tresLongMot");
			for (int i = 0; i < listeDeMots.size(); i++) {
				if (letterGame.isTheBestWord(listeDeMots.get(i), motAFaire)) {
					motAFaire = listeDeMots.get(i);
				}
			}
			assertTrue(motAFaire.equals("tresLongMot"));
		}

		@Test
		public void testEstLeMeilleurMot() {
			String mot = "manan";
			String meilleurMot = "tresLongMot";
			assertTrue(letterGame.isTheBestWord(meilleurMot, mot));
		}

		@Test
		public void testMotAvecUnMotIA() {
			player1.setPlayerWord("man");
			player1.setPlayerWord("test");
			letterGame.getLettreSurTable().add('a');
			letterGame.getLettreSurTable().add('m');
			for (int i = 0; i < player1.getPlayerWord().size(); i++) {
				String oldMot = player1.getPlayerWord().get(i);
				for(int j = 0; j < oldMot.length(); j++) {
					letterGame.getLettreSurTable().add(oldMot.charAt(j));
				}
				List<String> newMots = letterGame.wordPossibleParIA();
				for(int k = 0; k < letterGame.wordPossibleParIA().size(); k++) {
					String newMot = newMots.get(k);
					if(newMot.contains(oldMot) && !newMot.equals(oldMot)) {
						player1.getPlayerWord().remove(oldMot);
						player1.getPlayerWord().add(newMot);
						for(int j = 0; j < newMot.length(); j++) {
							letterGame.getLettreSurTable().remove((Character) newMot.charAt(j));
						}
					}
				}
			}
			assertTrue(player1.getPlayerWord().size() == 2);
			assertTrue(player1.getPlayerWord().contains("maman"));
		}

		@Test
		public void testMotAvecDeuxMotsIA() {
			player1.setPlayerWord("mari");
			player1.setPlayerWord("age");
			String mot = "";
			for (int i = 0; i < player1.getPlayerWord().size(); i++) {
				for (int j = 0; j < player2.getPlayerWord().size(); j++) {
					mot = player1.getPlayerWord().get(i).concat(player2.getPlayerWord().get(j));
					if (dictionary.isWord(mot)) {
						player1.getPlayerWord().remove(player1.getPlayerWord().get(i));
						player2.getPlayerWord().remove(player2.getPlayerWord().get(j));
						player1.setPlayerWord(mot);
					}
				}
			}
			assertTrue(player1.getPlayerWord().get(0).equals(mot));
		}
}
	
