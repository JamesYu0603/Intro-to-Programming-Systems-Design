import java.util.Scanner;
import javax.swing.JFrame;

/**
* CoinSimViewer class
* Show the graphic results. And make sure the input is the correct form.
*
*/
public class CoinSimViewer{
	public static void main(String[] args) {
       int trialsNum = 0;

	    System.out.print("Enter number of trials: ");
	    Scanner in = new Scanner(System.in);
	    trialsNum = in.nextInt();
	    
       //When the input is less than or equal to 0, give the ERROR message until entering the right number.
	    while(trialsNum <= 0) {
	    	 System.out.println("ERROR: Number entered must be greater than 0.");
		    System.out.print("Enter number of trials: ");
		    trialsNum = in.nextInt();
       }
	    
       JFrame frame = new JFrame();

	    frame.setSize(800, 500);
	    frame.setTitle("CoinSim");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    CoinSimComponent component = new CoinSimComponent(trialsNum);
	    frame.add(component);

	    frame.setVisible(true);
	}
}
