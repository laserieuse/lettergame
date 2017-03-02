package fr.esiea.unique.binome.name.dictionary;
	import java.util.ArrayList;
	import java.util.List;
	import java.util.Scanner;
	
	
	public class LetterGame {
		private Letter letter;
		private List<Character> common;
		private Player player1;
		private Player player2;
		private Player computer;
		private Scanner sc;
		private Dictionary dictionary;
		private boolean toPlay;
		
		public LetterGame() {
			letter = new Letter();
			common = new ArrayList<Character>();
			player1 = new Player();
			player2= new Player();
			computer= new Player();
			sc = new Scanner(System.in);
			dictionary = new Dictionary();
			toPlay = false;
		}
		
		public void initLetterGame() {
			System.out.println("Welcome to LettreGame !");
			System.out.println("---------------------------------------------------------------");
			choicePlayer();
		}
		
		/**
		 * Choix entre ordinateur et ami
		 */
		public void choicePlayer() {
			System.out.println("[1] Jouer contre un ami");
			System.out.println("[2] Jouer contre l'ordinateur");
			String choice = sc.next();
			if (choice.length() > 0) {
				switch (choice.charAt(0)) {
				case '1':
					initPseudos();
					whoStart();
				case '2':
					computer.setIsComputer(true);
					initPseudo();
					whoStart();
				default:
					System.out.println("Mauvais choix !");
					choicePlayer();
				}
			}
	}
		public void initPseudos() {
			System.out.println("Indiquez le pseudo du player 1:");
			player1.setPseudo(sc.next());
			System.out.println("Indiquez le pseudo du player 2:");
			player2.setPseudo(sc.next());
	    }
		public void initPseudo() {
			System.out.println("Indiquez votre pseudo:");
			player1.setPseudo(sc.next());
			computer.setPseudo("Computer");
			player2 = computer;
	    }
		
		/**
		 * Qui commence à jouer
		 */
		public void whoStart() {
			System.out.println("-------------------- Le jeu commence -------------------");
			char letterPlayer1 = letter.letterRandom();
			char letterPlayer2 = letter.letterRandom();
			System.out.println(player1.getPseudo() + " a pioché la lettre :" + letterPlayer1);
			System.out.println(player2.getPseudo() + " a pioché la lettre :" + letterPlayer2);
			common.add(letterPlayer1);
			common.add(letterPlayer2);
			if (letter.BestLetter(letterPlayer1, letterPlayer2)) {
				System.out.println(player1.getPseudo() + " commence alors le jeu !");
				actionMenu(player1, player2);
			} else {
				System.out.println(player2.getPseudo() + " commence !");
				actionMenu(player1, player2);
			}
		}
		/**
		 *le jeu commence
		 * 
		 * @param player
		 * @param playeradv
		 */
		public void runLetterGame(Player player, Player playeradv) {
			if (player.getWinner()) {
				System.out.println("Bravo vous avez fait 10 mots !");
				System.out.println(player.getPseudo() + " gagne la partie ! \\o/");
				System.exit(0);
			}
			if (player.getIsComputer()) {
				System.out.println("********************************************************");
				System.out.println("                   Tour du player " + player.getPseudo());
				System.out.println("*********************************************************");
			} else {
				System.out.println("********************************************************");
				System.out.println("                   Tour du player " + player.getPseudo());
				System.out.println("--------------------------------------------------------");
				System.out.println("Lettres disponibles:" + common);
				System.out.println("Les mots de votre adversaire: " + playeradv.getPlayerWord());
				System.out.println("Vos mots" + player.getPlayerWord());
			}
	}
  
		public void actionMenu(Player player, Player playeradv) {
			runLetterGame(player, playeradv);
			if (player.getIsComputer()) {
				actionComputer(player, playeradv);
			} else {
				System.out.println("********** Que souhaites tu faire?********************");
				System.out.println("[1] Passe ton tour");
				System.out.println("[2] Ecrire un mot");
				System.out.println("[3] Ecrire un nouveau mot à partir d'un mot");
				System.out.println("[4] Ecrire un nouveau mot à partir de deux mots");
				System.out.println("[5] quitter");
				System.out.println("*******************************************************");

				String choice= sc.next();
				if (choice.length() > 0) {
					switch (choice.charAt(0)) {
					case '1':
						pass(player, playeradv);
						break;
					case '2':
						aWord(player, playeradv);
						break;
					case '3':
						wordWithAWord(player, playeradv);
						break;
					case '4':
						wordWithTwoWord(player, playeradv);
						break;
					case '5':
						abandon(player, playeradv);
						break;
					default:
						System.out.println("Mauvais choix, recommencez !");
						actionMenu(player, playeradv);
						break;
					}
				}
			}		
		}

		
		public void actionComputer(Player player, Player playeradv) {
			if (toPlay) {
				toPlay = false;
				actionMenu(playeradv, player);
			} else {
				aWordIA(player, playeradv);
				if (toPlay) {
					toPlay = false;
					actionMenu(playeradv, player);
				} else {
					wordWithAWordIA(player, playeradv);
					if (toPlay) {
						toPlay = false;
						actionMenu(playeradv, player);
					} else {
						wordWithTwoWordIA(player, playeradv);
						if (toPlay) {
							toPlay = false;
							actionMenu(playeradv, player);
						} else {
							pass(player, playeradv);
							actionMenu(playeradv, player);
						}
					}
				}
			}
	}
		
		/**
		 * Permet à l'ordinateur de voir les mots possibles
		 * 
		 * @return la liste des mots possibles
		 */
		public ArrayList<String> wordPossibleParIA() {
			ArrayList<String> wordDico = dictionary.getWord();
			ArrayList<String> wordsPossible = new ArrayList<String>();

			for (int j = 0; j <wordDico.size(); j++) {
				String word = wordDico.get(j);
				boolean possible = true;
				if (word.length() <= common.size()) {
					int l = 0;
					while (l < word.length()) {
						for (int k = 0; k < word.length(); k++) {
							char charMot = word.charAt(k);
							if (!common.contains(charMot)) {
								possible = false;
							}
						}
						l++;
					}
					if (possible) {
						wordsPossible.add(word);
					}
				}
			}
			return (wordsPossible);
	}
		
		/**
		 * L'ordinateur fait un mot
		 * 
		 * @param player
		 * @param playeradv
		 */
		public void aWordIA(Player player,Player playeradv) {
			String bestWord = "";
			ArrayList<String> listeDeswordsPossible = wordPossibleParIA();
			for (int i = 0; i < listeDeswordsPossible.size(); i++) {
				String wordPossible = listeDeswordsPossible.get(i);
				if (!isTheBestWord(bestWord, wordPossible)) {
					bestWord = wordPossible;
					break;
				}
			}
			if (bestWord != "" && wordPossible(bestWord, player)) {
				List<Character> lettreARemove = new ArrayList<Character>();
				for (int k = 0; k < bestWord.length(); k++) {
					lettreARemove.add(bestWord.charAt(k));
				}
				for (int j = 0; j < lettreARemove.size(); j++) {
					common.remove(lettreARemove.get(j));
				}
				computer.getPlayerWord().add(bestWord);
				System.out.println("L'IA a fait le mot [" + bestWord + "]");
				testGagnant(player);
				pioche(player);
				toPlay = false;
				actionMenu(player,playeradv);
			}
		}
		/**
		 *  Best mot à faire
		 * 
		 * @param bestWord
		 * @param word
		 * @return
		 */
		public boolean isTheBestWord(String bestWord, String word) {
			if (bestWord.length() < word.length()) {
				return false;
			}
			return true;
		}

		/**
		 * L'ordinateur fait un mot avec un mot déjà fait
		 * 
		 * @param player
		 * @param playeradv
		 */
		public void wordWithAWordIA(Player player, Player playeradv) {
			List<String> wordsPossible = new ArrayList<>();
			String newWord = "";
			String oldWord = "";
			for (int i = 0; i < player.getPlayerWord().size(); i++) {
				for (int j = 0; j < player.getPlayerWord().get(i).length(); j++) {
					common.add(player.getPlayerWord().get(i).charAt(j));
				}
				wordsPossible = wordPossibleParIA();
				for (int k = 0; k < wordsPossible.size(); k++) {
					if (wordsPossible.get(k).contains(player.getPlayerWord().get(i))
							&& !wordsPossible.get(k).equals(player.getPlayerWord().get(i))
							&& wordPossible(wordsPossible.get(k), player)) {
						newWord = wordsPossible.get(k);
						oldWord = player.getPlayerWord().get(i);
						toPlay = true;
					}
				}
				if (newWord != "") {
					for (int l = 0; l < newWord.length(); l++) {
						common.remove((Character) newWord.charAt(l));
					}
				} else {
					for (int l = 0; l < player.getPlayerWord().get(i).length(); l++) {
						common.remove((Character) player.getPlayerWord().get(i).charAt(l));
					}
				}
			}
			if (toPlay) {
				player.getPlayerWord().add(newWord);
				player.getPlayerWord().remove(oldWord);
				System.out.println("L'IA a fait [" + newWord + "] avec " + oldWord);
				testGagnant(player);
				pioche(player);
	            toPlay = false;
	            actionMenu(player, playeradv);
			}
	}
		
		/**
		 * L'IA fait un mot avec deux mots déjà fait
		 * 
		 * @param player
		 * @param playeradv
		 */
		public void wordWithTwoWordIA(Player player, Player playeradv) {
			chercherUnMotAvecDeuxMotsIA(player, playeradv);
			chercherUnMotAvecDeuxMotsIA(playeradv, player);
			if (toPlay) {
				testGagnant(player);
	            pioche(player);
	            toPlay = false;
	            actionMenu(player, playeradv);
			}
		}

		/**
		 * Permet à l'IA de trouver un mot avec deux mots
		 * 
		 * @param player
		 * @param playeradv
		 */
		public void chercherUnMotAvecDeuxMotsIA(Player player, Player playeradv) {
			for (int i = 0; i < player.getPlayerWord().size(); i++) {
				for (int j = 0; j < playeradv.getPlayerWord().size(); j++) {
					if (dictionary.isWord(player.getPlayerWord().get(i).concat(playeradv.getPlayerWord().get(j)))) {
						player.getPlayerWord().add(player.getPlayerWord().get(i).concat(playeradv.getPlayerWord().get(j)));
						System.out.println(
								"L'IA a fait [" + player.getPlayerWord().get(i).concat(playeradv.getPlayerWord().get(j))
										+ "] avec les deux mots " + player.getPlayerWord().get(i) + " - "
										+ playeradv.getPlayerWord().get(j));
						player.getPlayerWord().remove(player.getPlayerWord().get(i));
						playeradv.getPlayerWord().remove(playeradv.getPlayerWord().get(j));
						toPlay= true;
					}
				}
			}
	}
	
		
		/**
		 * Le player passe son tour, pioche 2 lettres
		 * 
		 * @param player
		 * @param playeradv
		 */
		public void pass(Player player, Player playeradv) {
			char l1 = letter.letterRandom();
			char l2 = letter.letterRandom();
			if (player.getIsComputer()) {
				System.out.println("L'IA a passé son tour");
				System.out.println("L'IA a pioché un " + l1 + " et un " + l2);
			} else {
				System.out.println("Vous avez pioché un " + l1 + " et un " + l2);
			}
			common.add(l1);
			common.add(l2);
			actionMenu(playeradv, player);
		}
		
		/**
		 * On pioche une lettre
		 */
		public void pioche(Player player) {
			char l1 = letter.letterRandom();
			if (player.getIsComputer()) {
				System.out.println("L'ordinateur a pioché un " + l1);
			} else {
				System.out.println("Vous avez pioché un " + l1);
			}
			common.add(l1);
		}
		/**
		 * On fait un mot
		 * 
		 * @param player
		 * @param playeradv
		 */
		public void aWord(Player player, Player playeradv) {
			System.out.println("Entrez votre mot: ");
			String word = sc.next();

			if (dictionary.isWord(word) && wordPossible(word, player)) {
				player.setPlayerWord(word);
				testGagnant(player);
				pioche(player);
			}
			actionMenu(player, playeradv);
		}

		/**
		 * Détermine si le mot est possible avec les lettres du pot commun
		 * 
		 * @param mot
		 * @return
		 */
		public boolean wordPossible(String mot, Player player) {
			boolean bool = true;
			List<Character> lettreARemove = new ArrayList<Character>();
			List<Character> lettreSurTableTmp = common;

			for (int i = 0; i < mot.length(); i++) {
				Character charMot = mot.charAt(i);
				if (!lettreSurTableTmp.contains(charMot)) {
					if (!player.getIsComputer()) {
						System.out.println("Vous pouvez pas faire ce mot avec les lettres disponibles");
					}
					bool = false;
					break;
				} else {
					lettreSurTableTmp.remove(charMot);
					lettreARemove.add(charMot);
				}
			}
			if (bool) {
				for (int j = 0; j < lettreARemove.size(); j++) {
					common.remove(lettreARemove.get(j));
				}
			}
			return bool;
	}
		
		/**
		 * On fait un mot avec un autre mot
		 * 
		 * @param player
		 * @param playeradv
		 */
		public void wordWithAWord(Player player, Player playeradv) {
			System.out.println("Quel mot souhaitez vous compléter: ");
			String motACompleter = sc.next();

			if (player.getPlayerWord().contains(motACompleter) || playeradv.getPlayerWord().contains(motACompleter)) {
				for (int i = 0; i < motACompleter.length(); i++) {
					Character lettre = motACompleter.charAt(i);
					common.add(lettre);
				}
				System.out.println("Quel est votre nouveau mot?");
				String nouveauMot = sc.next();

				if (nouveauMot.contains(motACompleter) && wordPossible(nouveauMot, player)) {
					removeUnMot(player, playeradv, motACompleter);
					player.getPlayerWord().add(nouveauMot);
					testGagnant(player);
					pioche(player);
				} else {
					for (int i = 0; i < motACompleter.length(); i++) {
						Character lettre = motACompleter.charAt(i);
						common.remove(lettre);
					}
					System.out.println("C'est le même mot, petit tricheur ;)");
				}
			} else {
				System.out.println("Ce mot n'a pas encore été fait");
			}
			actionMenu(player, playeradv);
	}
		
		/**
		 * On fait un mot avec deux mots existants
		 * 
		 * @param player
		 * @param playeradv
		 */
		public void wordWithTwoWord(Player player, Player playeradv) {
			System.out.println("Quel est le premier mot à utiliser: ");
			String premierMot = sc.next();

			if (player.getPlayerWord().contains(premierMot) || playeradv.getPlayerWord().contains(premierMot)) {
				System.out.println("Quel est le deuxième mot à utiliser: ");
				String deuxiemeMot = sc.next();
				if (player.getPlayerWord().contains(deuxiemeMot) || playeradv.getPlayerWord().contains(deuxiemeMot)) {
					String nouveauMot = premierMot.concat(deuxiemeMot);
					if (dictionary.isWord(nouveauMot)) {
						removeUnMot(player, playeradv, premierMot);
						removeUnMot(player, playeradv, deuxiemeMot);
						player.getPlayerWord().add(nouveauMot);
						testGagnant(player);
						pioche(player);
					}
				} else {
					System.out.println("Ce mot n'a pas encore été fait");
				}
			} else {
				System.out.println("Ce mot n'a pas encore été fait");
			}
			actionMenu(player, playeradv);
		}

		/**
		 * Retire un mot déjà réaliser par un player
		 * 
		 * @param player
		 * @param playeradv
		 * @param mot
		 */
		public void removeUnMot(Player player, Player playeradv, String mot) {
			if (player.getPlayerWord().contains(mot)) {
				player.getPlayerWord().remove(mot);
			} else {
				playeradv.getPlayerWord().remove(mot);
			}
		}

		/**
		 * Le player abandonne
		 * 
		 * @param player
		 * @param playeradv
		 */
		public void abandon(Player player, Player playeradv) {
			System.out.println(player.getPseudo() + " a abandonné, " + playeradv.getPseudo() + " a gagné !!!");
			System.out.println("Merci d'avoir joué, à la prochaine !");
			System.exit(0);
		}

		/**
		 * Détermine si on a fait 10 mots
		 * 
		 * @param player
		 */
		public void testGagnant(Player player) {
			if (player.getPlayerWord().size() >= 10) {
				player.setWinner(true);
			}
		}

		/**
		 * Récupère les lettresCommunes
		 * 
		 * @return
		 */
		public List<Character> getLettreSurTable() {
			return common;
		}


}
		
