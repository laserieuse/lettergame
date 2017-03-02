package fr.esiea.unique.binome.name.dictionary;

import java.util.LinkedList;

public class Player {
		private String pseudo;
		private int wordNb;
		private LinkedList<String> playerWord;
		private boolean winner;
		private boolean isComputer;
		
		public Player() {
			this.pseudo = "";
			this.wordNb = 0;
			this.playerWord = new LinkedList<String>();
			this.winner = false;
			this.isComputer = false;
		}
		
		public String getPseudo() {
			return pseudo;
		}
		
		public void setPseudo(String pseudo) {
			this.pseudo = pseudo;
		}
		
		public int getWordNb() {
			return wordNb;
	}
		
		public void setWordNb() {
			this.wordNb = playerWord.size();
		}
		
		public LinkedList<String> getPlayerWord() {
			return playerWord;
		}
		
		public String getPlayerWordToString() {
			return playerWord.toString();
		}
		
		public void setPlayerWord(String word) {
			this.playerWord.add(word);
		}
		
		public boolean getWinner() {
			return winner;
		}
		
		public void setWinner(boolean winner) {
			this.winner = winner;
		}
		
		public boolean getIsComputer() {
			return isComputer;
		}
		
		public void setIsComputer(boolean isComputer) {
			this.isComputer = isComputer;
		}
	}