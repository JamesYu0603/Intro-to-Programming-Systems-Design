import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

/**
   A Rack of Scrabble tiles
 */

public class Rack {
   /**
      Representation invariant:
      The string the rack refers to must only contains letters.
   */
   private String rack;
   
   /**
      create a rack with given string.
      @param rack the string from input
   */
   public Rack(String rack){
      this.rack = rack;  
   }
  
   /**
      Get all subsets by unique string and their multiplicity, and then use allSubsets() method.
      @return a ArrayList contains all the subsets of rack
   */
   public ArrayList<String> getAllSubsets(){
      return new ArrayList<String>(allSubsets(getUniqueString(letterCount(rack)), 
                                 getMultiplicity(letterCount(rack)), 
                                 0));
   }
   
   /**
      Use a HashMap to store the letter and its corresponding multiplity.
      E.g. s = "abbccc", then keys: {a, b, c};  values: {1, 2, 3}.
      @return a HashMap which keys are letters and values are multiplicities of letters.
   */
   private Map<Character, Integer> letterCount(String s){
      Map<Character, Integer> letterCounter = new HashMap();
      
      for(char ch : s.toCharArray()){
         if(letterCounter.containsKey(ch)){
            letterCounter.put(ch, letterCounter.get(ch) + 1); 
         }
         else{ 
            letterCounter.put(ch, 1); 
         }
      }
      return letterCounter;
   }
   
   /**
      Get unique string from the keys of map. Since keys are unique, just iterate the keys and form a string.
      @param map input map to process
      @return a unique string formed by keys
   */
   private String getUniqueString(Map<Character, Integer> map){
      String uniqueStr = "";
      Iterator<Character> iter = map.keySet().iterator();
      
      while(iter.hasNext()){
         uniqueStr += iter.next();
      }
      return uniqueStr;
   }
   
   /**
      Get multiplicity from the values of map.
      @param map input map to process
      @return a int[] which has corresponding multiplicity as keys
   */
   private int[] getMultiplicity(Map<Character, Integer> map){
      int index = 0;
      int[] mult = new int[map.size()];
      Iterator<Integer> iter = map.values().iterator();
       
      while(iter.hasNext()){
         mult[index] = iter.next();
         index++;
      }
      return mult;
   }


   /**
      Finds all subsets of the multiset starting at position k in unique and mult.
      unique and mult describe a multiset such that mult[i] is the multiplicity of the char
           unique.charAt(i).
      PRE: mult.length must be at least as big as unique.length()
           0 <= k <= unique.length()
      @param unique a string of unique letters
      @param mult the multiplicity of each letter from unique.  
      @param k the smallest index of unique and mult to consider.
      @return all subsets of the indicated multiset.  Unlike the multiset in the parameters,
      each subset is represented as a String that can have repeated characters in it.
      @author Claire Bono
    */
   private static ArrayList<String> allSubsets(String unique, int[] mult, int k) {
      ArrayList<String> allCombos = new ArrayList<>();
      
      if (k == unique.length()) {  // multiset is empty
         allCombos.add("");
         return allCombos;
      }
      
      // get all subsets of the multiset without the first unique char
      ArrayList<String> restCombos = allSubsets(unique, mult, k+1);
      
      // prepend all possible numbers of the first char (i.e., the one at position k) 
      // to the front of each string in restCombos.  Suppose that char is 'a'...
      
      String firstPart = "";          // in outer loop firstPart takes on the values: "", "a", "aa", ...
      for (int n = 0; n <= mult[k]; n++) {   
         for (int i = 0; i < restCombos.size(); i++) {  // for each of the subsets 
                                                        // we found in the recursive call
            // create and add a new string with n 'a's in front of that subset
            allCombos.add(firstPart + restCombos.get(i));  
         }
         firstPart += unique.charAt(k);  // append another instance of 'a' to the first part
      }
      return allCombos;
   }
}