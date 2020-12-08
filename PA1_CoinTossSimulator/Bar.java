import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

/**
 * Bar class
 * A labeled bar that can serve as a single bar in a bar graph.
 * The text for the label is centered under the bar.
 * 
 * NOTE: we have provided the public interface for this class. Do not change
 * the public interface. You can add private instance variables, constants,
 * and private methods to the class. You will also be completing the
 * implementation of the methods given.
 * 
 */
public class Bar {
   /** 
      @param barBottom  Bottom of the bar.
      @param barLeft  Left of the bar.
      @param barWidth  Width of the bar.
      @param barBarHeight  Height of the bar.
      @param barScale  Scale of the bar.
      @param barColor  Color of the bar.
      @param barLabel  Label under the bar.
   */
	private int barBottom;
	private int barLeft;
	private int barWidth;
	private int barBarHeight;
	private double barScale;
	private Color barColor;
	private String barLabel;
   
   /**
      Creates a labeled bar.  You give the height of the bar in application
      units (e.g., population of a particular state), and then a scale for how
      tall to display it on the screen (parameter scale). 
  
      @param bottom  location of the bottom of the label
      @param left  location of the left side of the bar
      @param width  width of the bar (in pixels)
      @param barHeight  height of the bar in application units
      @param scale  how many pixels per application unit
      @param color  the color of the bar
      @param label  the label at the bottom of the bar
   */
   public Bar(int bottom, int left, int width, int barHeight,
              double scale, Color color, String label) {
      barBottom = bottom;
      barLeft = left;
      barWidth = width;
      barBarHeight = barHeight;
      barScale = scale;
      barColor = color;
      barLabel = label;
   }
   
   /**
      Draw the labeled bar. 
      @param g2  the graphics context
   */
   public void draw(Graphics2D g2) {
      Font font = g2.getFont();
      FontRenderContext context = g2.getFontRenderContext();
      Rectangle2D labelBounds = font.getStringBounds(barLabel, context);
      
      int widthOfLabel = (int)labelBounds.getWidth();//Get the width of label.
      int heightOfLabel = (int)labelBounds.getHeight();//Get the height of label.
      int labelLeft = barLeft - (int)Math.round((widthOfLabel - barWidth) / 2);//Calculate the left location of label.
      
      g2.setColor(Color.black);
      g2.drawString(barLabel, labelLeft, barBottom);
      
      int barTop = (int)Math.round(barBottom - heightOfLabel - barBarHeight * barScale);//Calculate the top location of label.
      Rectangle recBar = new Rectangle(barLeft,
                            barTop, 
                            barWidth, 
                            (int)Math.round(barBarHeight * barScale));
      
      g2.setColor(barColor);
      g2.fill(recBar);
   }
}
