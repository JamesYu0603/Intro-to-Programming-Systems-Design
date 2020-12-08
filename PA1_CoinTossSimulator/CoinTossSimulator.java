import java.util.Random;


/**
 * class CoinTossSimulator
 * 
 * Simulates trials of repeatedly tossing two coins and allows the user to access the
 * cumulative results.
 * 
 * NOTE: we have provided the public interface for this class.  Do not change
 * the public interface.  You can add private instance variables, constants, 
 * and private methods to the class.  You will also be completing the 
 * implementation of the methods given. 
 * 
 * Invariant: getNumTrials() = getTwoHeads() + getTwoTails() + getHeadTails()
 * 
 */

public class CoinTossSimulator {
   /**
      @param twoHeadsNum  The number of two-heads case.
      @param twoTailsNum  The number of two-tails case.
      @param headTailNum  The number of one-head and one-tail case.
   */
	private int twoHeadsNum;
	private int twoTailsNum;
	private int headTailNum;


   /**
      Creates a coin toss simulator with no trials done yet.
   */
   public CoinTossSimulator() {
	   twoHeadsNum = 0;
	   twoTailsNum = 0;
	   headTailNum = 0;
   }


   /**
      Runs the simulation for numTrials more trials. Multiple calls to this method
      without a reset() between them *add* these trials to the current simulation.
      
      @param numTrials  number of trials to for simulation; must be >= 1
    */
   public void run(int numTrials) {
	   int coinResults;
	   
	   Random rd = new Random();
	   
	   for(int i=0; i<numTrials; i++) {
		   coinResults = rd.nextInt(2) + rd.nextInt(2);
		   
		   if(coinResults == 0) {
			   twoTailsNum++;
		   }
		   else if(coinResults == 2) {
			   twoHeadsNum++;
		   }
		   else {
			   headTailNum++;
		   }
	   }
   }


   /**
      Get number of trials performed since last reset.
   */
   public int getNumTrials() {
       return twoHeadsNum + twoTailsNum + headTailNum;
   }


   /**
      Get number of trials that came up two heads since last reset.
   */
   public int getTwoHeads() {
       return twoHeadsNum;
   }


   /**
     Get number of trials that came up two tails since last reset.
   */  
   public int getTwoTails() {
       return twoTailsNum;
   }


   /**
     Get number of trials that came up one head and one tail since last reset.
   */
   public int getHeadTails() {
       return headTailNum;
   }


   /**
      Resets the simulation, so that subsequent runs start from 0 trials done.
    */
   public void reset() {
	   twoHeadsNum = 0;
	   twoTailsNum = 0;
	   headTailNum = 0;
   }

}
