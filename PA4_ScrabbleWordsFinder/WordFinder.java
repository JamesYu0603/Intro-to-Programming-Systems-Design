import java.util.Scanner;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.Collections;
import java.util.Map;
import java.util.Comparator;
import java.io.FileNotFoundException;

/**
   A class to read the input and print out the score of words ordered by score.
   It is case-sensitive and ignores characters other than letters.
*/
public class WordFinder{
   private static String rack;
   private static final String DEFAULT_FILE_NAME = "sowpods.txt";
   
   public static void main(String[] args){
      String fileName = "";
      
      try{
         if(args.length == 0){ fileName = DEFAULT_FILE_NAME; }//use default input file
         else{ fileName = args[0]; }
         
         AnagramDictionary dic = new AnagramDictionary(fileName);
         Scanner in = new Scanner(System.in);
         System.out.println("Type . to quit.");
         System.out.print("Rack? "); 
         
         while(in.hasNextLine()){
            rack = in.nextLine();
            if(rack.equals(".")){ break; }
            else{ printSortedScore(getSubsets(dic, rack)); }
         System.out.print("Rack? ");
         }
      }
      catch(FileNotFoundException e){
         System.out.println("ERROR: Dictionary file \"" + fileName + "\" does not exist.");
         System.out.println("Exiting program.");
      }
      catch(IllegalDictionaryException e){
         System.out.println("ERROR: Illegal dictionary: dictionary file has a duplicate word: " + e);
         System.out.println("Exiting program.");
      }
   }
   
   /**
      Get the subsets of a string and their scores.
      @param dic to use getAnagramsof() to get all words
      @param rack Input string rack
      @return a TreeMap stores the words as key and scores as values
   */
   private static TreeMap<String, Integer> getSubsets(AnagramDictionary dic, String rack){
      TreeMap<String, Integer> scoreList = new TreeMap<String, Integer>();
      Rack inRack = new Rack(validInput(rack));
      ArrayList<String> subsets = new ArrayList<String>(inRack.getAllSubsets()); 
      
      for(int i = 0; i < subsets.size(); i++){
         ArrayList<String> words = dic.getAnagramsOf(subsets.get(i));

         if (words != null) {
            for(int j = 0; j < words.size(); j++){
               String word = words.get(j);
               scoreList.put(word, getScore(word));
            }
         }
      }
      return scoreList;
   }
   
   /**
      Get the string only contains letters.
      @param s the string to process
      @return a string only contains letters.
   */
   private static String validInput(String s){
      String validStr = "";
      
      for(int i = 0; i < s.length(); i++){
         char ch = s.charAt(i);
         if(Character.isLetter(ch)){
            validStr += ch;
         }
      }
      return validStr;
   }  
   
   /**
      Get the score of a string.
      @param s the string to get the score
      @return the score of the string s 
   */
   private static int getScore(String s){
      ScoreTable st = new ScoreTable(s);
      return st.getScore();
   }
      
   /**
      Print out all the words and corresponding scores ordered by the scores.
      @param scoreList the scoreList to be printed
   */
   private static void printSortedScore(TreeMap<String, Integer> scoreList){
      ArrayList<Map.Entry<String, Integer>> sortList = new ArrayList<Map.Entry<String, Integer>>(scoreList.entrySet());
      Collections.sort(sortList, new scoreComparator());
      
      System.out.println("We can make " + sortList.size() + " words from \"" + rack + "\"");
      
      if (sortList.size() > 0) {
         System.out.println("All of the words with their scores (sorted by score):");
         for (int i = 0; i < sortList.size(); i++) {
            System.out.println(sortList.get(i).getValue() + ": " + sortList.get(i).getKey());
         }
      }
   }
   
   /**
     Implement Java Comparator interface to sort the list by the descending value
     of score. If scores are the same, then it will be alphabetically ordered.
   */
   static class scoreComparator implements Comparator<Map.Entry<String, Integer>>{
      public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b){
         return b.getValue() - a.getValue();
      }
   }
}