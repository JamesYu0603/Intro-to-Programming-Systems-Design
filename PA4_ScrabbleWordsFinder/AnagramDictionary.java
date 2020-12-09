import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.io.File;

/**
   A dictionary of all anagram sets. 
   Note: the processing is case-sensitive; so if the dictionary has all lower
   case words, you will likely want any string you test to have all lower case
   letters too, and likewise if the dictionary words are all upper case.
 */
public class AnagramDictionary {
   /**
     Representation invariant: keys for anagramDict are canonical string entry, and values are anagram string entries.
     E.g. if key: canonical string entry, like 'abc'
     then value: anagram string entries, like {'abc', 'bac', 'bca', ... }
   */
   private Map<String, ArrayList<String>> anagramDict;

   /**
      Create an anagram dictionary from the list of words given in the file
      indicated by fileName.  
      @param fileName  the name of the file to read from
      @throws FileNotFoundException  if the file is not found
      @throws IllegalDictionaryException  if the dictionary has any duplicate words
    */
   public AnagramDictionary(String fileName) throws FileNotFoundException, IllegalDictionaryException {
      anagramDict = new HashMap<String, ArrayList<String>>();
      
      File file = new File(fileName);
      Scanner in = new Scanner(file);
      
      while(in.hasNext()){
         String str = in.next();
         String sortedStr = getSortedForm(str);
         ArrayList<String> arrList;
         
         if(anagramDict.containsKey(sortedStr)){
            arrList = new ArrayList<String>(anagramDict.get(sortedStr));
            Set<String> keySet = new HashSet<String>(arrList);//to find duplicate words in O(1) time complexity
            
            if(keySet.contains(str)){ throw new IllegalDictionaryException(str); }
         }
         else{
            arrList = new ArrayList<String>();
         }
         arrList.add(str);
         anagramDict.put(sortedStr, arrList);//update the map
      }                                
   }
   

   /**
      Get all anagrams of the given string. This method is case-sensitive.
      E.g. "CARE" and "race" would not be recognized as anagrams.
      @param s string to process
      @return a list of the anagrams of s
    */
   public ArrayList<String> getAnagramsOf(String s) {
      String sortedStr = getSortedForm(s);
      
      if(anagramDict.containsKey(sortedStr)){
         return anagramDict.get(sortedStr);//key of a sorted string already exists
      }
      else{
         return new ArrayList<String>();//return a empty Arraylist if the key of a sorted string cannot be found
      }
   }
   
   /**
      Get a sorted string from a specific string.
      E.g. s = "bca", then return "abc".
      @param s string to process
      @return a list of sorted String
    */
   private String getSortedForm(String s){
      char[] charArr = s.toCharArray();
      Arrays.sort(charArr);
      
      return new String(charArr);
   }  
   
}