import java.util.ArrayList;

/**
 * Class Bookshelf
 * Implements idea of arranging books into a bookshelf.
 * Books on a bookshelf can only be accessed in a specific way so books don¡¯t fall down;
 * You can add or remove a book only when it¡¯s on one of the ends of the shelf.   
 * However, you can look at any book on a shelf by giving its location (starting at 0).
 * Books are identified only by their height; two books of the same height can be
 * thought of as two copies of the same book.
*/

public class Bookshelf {

    /**
      Representation invariant:
      Invariant:size() >= 0; 
             every height should be a positive number.
   */
   
   private ArrayList<Integer> booksArr;

   /**
    * Creates an empty Bookshelf object i.e. with no books
    */
   public Bookshelf() {
      booksArr = new ArrayList<Integer>();
      assert isValidBookshelf();  
   }

   /**
    * Creates a Bookshelf with the arrangement specified in pileOfBooks. Example
    * values: [20, 1, 9].
    * 
    * PRE: pileOfBooks contains an array list of 0 or more positive numbers
    * representing the height of each book.
    */
   public Bookshelf(ArrayList<Integer> pileOfBooks) {
      booksArr = new ArrayList<Integer>(pileOfBooks);
      assert isValidBookshelf();
   }

   /**
    * Inserts book with specified height at the start of the Bookshelf, i.e., it
    * will end up at position 0.
    * 
    * PRE: height > 0 (height of book is always positive)
    */
   public void addFront(int height) {
      booksArr.add(0, height);
      assert isValidBookshelf();
   }

   /**
    * Inserts book with specified height at the end of the Bookshelf.
    * 
    * PRE: height > 0 (height of book is always positive)
    */
   public void addLast(int height) {
      booksArr.add(height);
      assert isValidBookshelf();
   }

   /**
    * Removes book at the start of the Bookshelf and returns the height of the
    * removed book.
    * 
    * PRE: this.size() > 0 i.e. can be called only on non-empty BookShelf
    */
   public int removeFront() {
      int heightFront = booksArr.get(0);
      booksArr.remove(0);
      assert isValidBookshelf();
      return heightFront;  
   }

   /**
    * Removes book at the end of the Bookshelf and returns the height of the
    * removed book.
    * 
    * PRE: this.size() > 0 i.e. can be called only on non-empty BookShelf
    */
   public int removeLast() {
      int heightLast = booksArr.get(booksArr.size()-1);
      booksArr.remove(booksArr.size()-1);
      assert isValidBookshelf();
      return heightLast;     
   }

   /*
    * Gets the height of the book at the given position.
    * 
    * PRE: 0 <= position < this.size()
    */
   public int getHeight(int position) {
      assert isValidBookshelf();
      return booksArr.get(position);   
   }

   /**
    * Returns number of books on the this Bookshelf.
    */
   public int size() {
      assert isValidBookshelf();
      return booksArr.size();   

   }

   /**
    * Returns string representation of this Bookshelf. Returns a string with the height of all
    * books on the bookshelf, in the order they are in on the bookshelf, using the format shown
    * by example here:  ¡°[7, 33, 5, 4, 3]¡±
    */
   public String toString() {
      assert isValidBookshelf();
      String booksStr = "";
      if(booksArr.size() == 0){
         return "[]";//just return [] considering the case of no books
      }
      
      if(booksArr.size() == 1){
         return "[" + booksArr.get(0) + "]";
      }

      for(int i = 0; i<booksArr.size(); i++){
         if(i == 0){
            booksStr += "[" + booksArr.get(i) + ", ";//printing the first book needs a "[" in the beginning
         }
         else if(i == booksArr.size() - 1){
            booksStr += booksArr.get(i) + "]";//printing the last book needs a "]" in the end
         }
         else{
            booksStr += booksArr.get(i) + ", ";
         }
      }
      return booksStr;   // dummy code to get stub to compile
   }

   /**
    * Returns true iff the books on this Bookshelf are in non-decreasing order.
    * (Note: this is an accessor; it does not change the bookshelf.)
    */
   public boolean isSorted() {
      assert isValidBookshelf();
      if(booksArr.size() == 1 || booksArr.size() == 0){
         return true;//if the size is 0 or 1, it should be always sorted
      }
      
      for(int i=0; i<booksArr.size()-1; i++){
         if(booksArr.get(i) > booksArr.get(i+1)){
            return false;
         }
      }
      return true;  
   }

   /**
    * Returns true iff the Bookshelf data is in a valid state.
    * (See representation invariant comment for more details.)
    */
   private boolean isValidBookshelf() {
      for(int i=0; i<booksArr.size(); i++){
         if(booksArr.get(i) <= 0){
            return false;
         }
      }
      return true;  
   }

}