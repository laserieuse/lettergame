package fr.esiea.unique.binome.name.dictionary;

import java.util.Random;

public class Letter {
       private String chars = "aaabcdeeeeefghiiijklmnooopqrstuuuvwxyz"; 
      
       public void letter() {
   		letterRandom();
       }
       public char letterRandom() {
   		Random r = new Random();
   		int val = r.nextInt(chars.length());
   		char lettreRandom = chars.charAt(val);
   		return lettreRandom;
       }
       
       public boolean BestLetter(char l1, char l2) {
   		if((int)l1<=(int)l2){
   			return true;
   		}else{
   			return false;
   		}
   }
}
