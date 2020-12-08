import java.util.ArrayList;
/**
 * Class BookshelfKeeper 
 *
 * Enables users to perform efficient putPos or pickHeight operation on a bookshelf of books kept in 
 * non-decreasing order by height, with the restriction that single books can only be added 
 * or removed from one of the two *ends* of the bookshelf to complete a higher level pick or put 
 * operation.  Pick or put operations are performed with minimum number of such adds or removes.
 */
public class BookshelfKeeper {

    /**
      Representation invariant:
      Invariant: Heights must be specified in non-decreasing order.
   */
   
   private Bookshelf bookshelf;
   private int callsNum = 0;
   private int currCallsNum = 0;
   /**
    * Creates a BookShelfKeeper object with an empty bookshelf
    */
   public BookshelfKeeper() {
      bookshelf = new Bookshelf();
      assert isValidBookshelfKeeper();
   }

   /**
    * Creates a BookshelfKeeper object initialized with the given sorted bookshelf.
    * Note: method does not make a defensive copy of the bookshelf.
    *
    * PRE: sortedBookshelf.isSorted() is true.
    */
   public BookshelfKeeper(Bookshelf sortedBookshelf) {
      bookshelf = sortedBookshelf;
      assert isValidBookshelfKeeper();
   }

   /**
    * Removes a book from the specified position in the bookshelf and keeps bookshelf sorted 
    * after picking up the book.
    * 
    * Returns the number of calls to mutators on the contained bookshelf used to complete this
    * operation. This must be the minimum number to complete the operation.
    * 
    * PRE: position must be in the range [0, getNumBooks()).
    */
   public int pickPos(int position) {
      ArrayList<Integer> removeHeight = new ArrayList<Integer>();//store the removed books
      
      if(position < bookshelf.size()-1-position){//the case when position is closer to the left side
         currCallsNum = 2 * position + 1;
         for(int i = 0; i <= position; i++){
            removeHeight.add(bookshelf.removeFront());
         }
         for(int j = removeHeight.size()-2; j >= 0; j--){
            bookshelf.addFront(removeHeight.get(j));
         }
      }
      else{//the case when position is closer to the right side
         currCallsNum = 2 * (bookshelf.size() - position) - 1;
         for(int i = bookshelf.size()-1; i >= position; i--){
            removeHeight.add(bookshelf.removeLast());
         }
         for(int j = removeHeight.size()-2; j >= 0; j--){
            bookshelf.addLast(removeHeight.get(j));
         }
      }
      callsNum += currCallsNum;//add to the total calls numbers
      assert isValidBookshelfKeeper();
      return currCallsNum;   
   }

   /**
    * Inserts book with specified height into the shelf.  Keeps the contained bookshelf sorted 
    * after the insertion.
    * 
    * Returns the number of calls to mutators on the contained bookshelf used to complete this
    * operation. This must be the minimum number to complete the operation.
    * 
    * PRE: height > 0
    */
   public int putHeight(int height) {
      ArrayList<Integer> removeHeight = new ArrayList<Integer>();//store the removed books
      int posLeft = putPosLeft(bookshelf, height);
      int posRight = putPosRight(bookshelf, height);
      
      if(posLeft < bookshelf.size() - posRight){//the case when the position is closer to the left side
         currCallsNum = 2 * posLeft + 1;
         for(int j = 0; j < posLeft; j++){ 
            removeHeight.add(bookshelf.removeFront()); 
         }
         bookshelf.addFront(height);
         for(int k = removeHeight.size()-1; k >= 0; k--){
            bookshelf.addFront(removeHeight.get(k)); 
         }
      }
      else{//the case when the position is closer to the right side
         currCallsNum = 2 * (bookshelf.size() - posRight) + 1;
         for(int j = bookshelf.size()-1; j >= posRight; j--){ 
            removeHeight.add(bookshelf.removeLast()); 
         }
         bookshelf.addLast(height);
         for(int k = removeHeight.size()-1; k >= 0; k--){ 
            bookshelf.addLast(removeHeight.get(k)); 
         }
      }
      callsNum += currCallsNum;//add to the total calls numbers
      assert isValidBookshelfKeeper();
      return currCallsNum;  
   }

   /**
    * Returns the total number of calls made to mutators on the contained bookshelf
    * so far, i.e., all the ones done to perform all of the pick and put operations
    * that have been requested up to now.
    */
   public int getTotalOperations() {
      assert isValidBookshelfKeeper();
      return callsNum;   // dummy code to get stub to compile
   }

   /**
    * Returns the number of books on the contained bookshelf.
    */
   public int getNumBooks() {
      assert isValidBookshelfKeeper();
      return bookshelf.size();  
   }

   /**
    * Returns string representation of this BookshelfKeeper. Returns a String containing height
    * of all books present in the bookshelf in the order they are on the bookshelf, followed 
    * by the number of bookshelf mutator calls made to perform the last pick or put operation, 
    * followed by the total number of such calls made since we created this BookshelfKeeper.
    * 
    * Example return string showing required format: ¡°[1, 3, 5, 7, 33] 4 10¡±
    * 
    */
   public String toString() {
      assert isValidBookshelfKeeper();
      return bookshelf.toString() + " " + currCallsNum + " " + callsNum;  
   }

   /**
    * Returns true iff the BookshelfKeeper data is in a valid state.
    * (See representation invariant comment for details.)
    */
   private boolean isValidBookshelfKeeper() {
      return bookshelf.isSorted();  
   }
   
   /**
    * Returns the position where the book could insert started from the left side.
    *
    * PRE: height > 0
    */
   private int putPosLeft(Bookshelf bookshelf, int height){
      int posLeft = 0;
      for(int i = 0; i < bookshelf.size(); i++){
         if(height > bookshelf.getHeight(i)){ posLeft++; }
         else{ break; }
      } 
      return posLeft;
   }
   
   /**
    * Returns the position where the book could insert started from the right side.
    *
    * PRE: height > 0
    */
   private int putPosRight(Bookshelf bookshelf, int height){
      int posRight = bookshelf.size();
      for(int i = bookshelf.size()-1; i >= 0; i--){
         if(height < bookshelf.getHeight(i)){ posRight--; }
         else{ break; }
      }
      return posRight;
   }
}
