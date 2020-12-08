import java.awt.geom.Rectangle2D;
import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.lang.Math;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

/**
 * CoinSimComponent class
 * Constructor initializes any necessary data and runs the simulation. Overrides paintComponent to draw the bar graph, using Bar objects for each bar in the graph. 
 * This class uses the CoinTossSimulator and Bar class
 *
 */
public class CoinSimComponent extends JComponent{
   /**
      @param twoHeadsNum  The number of two-heads case.
      @param twoTailsNum  The number of two-tails case.
      @param headTailsNum  The number of one-head and one-tail case.
      @param trailNum  The number of trials.
   */
	private int twoHeadsNum;
	private int twoTailsNum;
	private int headTailsNum;
	private int trialsNum;
	private static final int VERTICAL_BUFFER = 20;//The fixed value of vertical buffer.
	private static final int BAR_WIDTH = 50;//The fixed value of bar width.
	private static final int APPLICATION_UNITS = 100;//The value of total application units.
	
   /**
      @param trialsNum  The number of trials.
   */
	public CoinSimComponent(int trialsNum) {
       CoinTossSimulator sim = new CoinTossSimulator();
       sim.run(trialsNum);
		
       twoHeadsNum = sim.getTwoHeads();
       twoTailsNum = sim.getTwoTails();
       headTailsNum = sim.getHeadTails();
       this.trialsNum = trialsNum;
	}
	
   /**
      Paint every bar.
      @param g  the graphics context
   */
	public void paintComponent(Graphics g) {
       Graphics2D g2 = (Graphics2D)g;
       
       int bottom = getHeight() - VERTICAL_BUFFER;//Calculate the bottom of component.
       int barDistance = (getWidth() - 3 * BAR_WIDTH) / 4;//Calculate the distance between bars.

       int percentageTwoHeads = (int)Math.round((double)twoHeadsNum / trialsNum * APPLICATION_UNITS);//Calculate the percentage of two-heads case.
       int percentageHeadTails = (int)Math.round((double)headTailsNum / trialsNum * APPLICATION_UNITS);//Calculate the percentage of two-tails case.
       int percentageTwoTails = APPLICATION_UNITS - percentageTwoHeads - percentageHeadTails;//Calculate the percentage of one-head and one-tail case.
    
       String firstLabel = "Two Heads: " + twoHeadsNum + "(" + percentageTwoHeads + "%)";
       String secondLabel = "A Head and a Tail: " + headTailsNum + "(" + percentageHeadTails + "%)";
       String thirdLabel = "Two Tails: " + twoTailsNum + "(" + percentageTwoTails + "%)";
       
       Font font = g2.getFont();
       FontRenderContext context = g2.getFontRenderContext();
       Rectangle2D labelBounds = font.getStringBounds(firstLabel, context);
       int heightOfLabel = (int)labelBounds.getHeight();//To get the height of a string label, and then use it later to calculate the scale.  
       
       double scale = (double)((getHeight() - 2 * VERTICAL_BUFFER - heightOfLabel) / APPLICATION_UNITS);//Calculate the scale.
       
       Bar bar1 = new Bar(bottom, barDistance, BAR_WIDTH, percentageTwoHeads, scale, Color.red, firstLabel);//Create the object of the first bar.
       Bar bar2 = new Bar(bottom, 2 * barDistance + BAR_WIDTH, BAR_WIDTH, percentageHeadTails, scale, Color.green, secondLabel);//Create the object of the second bar.
       Bar bar3 = new Bar(bottom, 3 * barDistance + 2 * BAR_WIDTH, BAR_WIDTH, percentageTwoTails, scale, Color.blue, thirdLabel);//Create the object of the third bar.
       
       //draw the bars
       bar1.draw(g2);
       bar2.draw(g2);
       bar3.draw(g2);
	}
}