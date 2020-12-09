import java.util.Arrays;

/**
   A scoretable that stores the score of each letter. You can use it to 
   get the score of a word.
*/
public class ScoreTable{
   /**
      Representation invariant:
      The string word refers to must only contain letters.
      Upper case and lower case have the same score.
      (1 point)-A, E, I, O, U, L, N, S, T, R
      (2 points)-D, G
      (3 points)-B, C, M, P
      (4 points)-F, H, V, W, Y
      (5 points)-K
      (8 points)- J, X
      (10 points)-Q, Z
   */
   private String word;
   private static final int[] SCORE = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};
   
   /**
      Creat a scoretable with the string word. 
      All the letter with upper case in the string will be transferred to lower case.
      @param word Input word to calculate the score
   */
   public ScoreTable(String word){
      this.word = word.toLowerCase();
   }
   
   /**
      Calculate the score of the string.
      @return the score of string
   */
   public int getScore(){
      int score = 0;
      for(int i = 0; i < word.length(); i++){
         score += letterScore(word.charAt(i));
      }
      return score;
   }
   
   /**
      To give score for a specific letter.
      @param c the single character of a string
      @return the score of the character c
   */
   private int letterScore(char c){
      return SCORE[c-'a'];
   }
}