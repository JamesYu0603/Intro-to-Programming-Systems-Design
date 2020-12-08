import java.util.Scanner; 
import java.util.ArrayList;

/**
 * Class BookshelfKeeperProg
 *
 * Enables users to input book heights and do the pick or put operation. If 
 * there is any wrong input, the program will show the error message and 
 * exit the program.
 * 
 */
public class BookshelfKeeperProg{
   
   private static BookshelfKeeper keeper;
   private static Bookshelf bookshelf;
   
   public static void main(String[] args){
      Scanner in = new Scanner(System.in);

      getStartBooks(in);
      
      while(in.hasNext()){
         boolean endFlag = doPickOrPut(keeper, in);//endFlag will be true iff program has been ended
         in.nextLine();
         if(endFlag){ return; }//exit the program
      }  
   }
   
   /**
    * Get the input of book heights, and show the results.
    */
   public static void getStartBooks(Scanner scanner){
      ArrayList<Integer> books = new ArrayList<Integer>();
      boolean flag = true; 
      System.out.println("Please enter initial arrangement of books followed by newline:");
      
      String line = scanner.nextLine();
      Scanner linescanner = new Scanner(line);
      
      while(linescanner.hasNextInt()){
         int book = linescanner.nextInt();
         if(book <= 0){
            System.out.println("ERROR: Height of a book must be positive.");
            flag = false;
            break;
         }
         books.add(book); 
      }
      bookshelf = new Bookshelf(books);
      if(!bookshelf.isSorted()){
         System.out.println("ERROR: Heights must be specified in non-decreasing order.");
         flag = false;
      }

      if(flag){
         keeper = new BookshelfKeeper(bookshelf);
         System.out.println(keeper.toString());
         System.out.println("Type pick <index> or put <height> followed by newline. Type end to exit.");
      }
   }
   
   /**
    * Do the operation of pick, put or end the program according to input.
    * Return true if program has been ended.
    */
   public static boolean doPickOrPut(BookshelfKeeper bookshelfkeeper, Scanner scanner){
      String operation = scanner.next();
      if(operation.equals("pick")){
         doPick(bookshelfkeeper, scanner);
      }
      else if(operation.equals("put")){
         doPut(bookshelfkeeper, scanner);
      }
      else if(operation.equals("end")){
         System.out.println("Exiting Program.");
         return true;
      }
      else{
         System.out.println("ERROR: Operation should be either pick or put.");
      }
      return false;
   }

   /**
    * Do the pick operation and error checking.
    */
   public static void doPick(BookshelfKeeper bookshelfkeeper, Scanner scanner){
      int pickLoc = scanner.nextInt();
      if(pickLoc > bookshelf.size()-1 || pickLoc < 0){
         System.out.println("ERROR: Entered pick operation is invalid on this shelf.");
      }
      else{
         bookshelfkeeper.pickPos(pickLoc);
         System.out.println(bookshelfkeeper.toString());
      }
   }
   
   /**
    * Do the put operation and error checking.
    */
   public static void doPut(BookshelfKeeper bookshelfkeeper, Scanner scanner){
      int putHeight = scanner.nextInt();
      if(putHeight <= 0){
         System.out.println("ERROR: Height of a book must be positive.");
      }
      else{
         bookshelfkeeper.putHeight(putHeight);
         System.out.println(bookshelfkeeper.toString());
      }
   }
}