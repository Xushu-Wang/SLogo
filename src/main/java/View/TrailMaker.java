package View;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * TurtleLine will contain methods that deal with the line the follows the turtle.
 *
 * @author tmh85
 */
public abstract class TrailMaker {
  private Paint myTrailColor;
  private static final Paint DEFAULT_COLOR = Color.BLACK;

  public TrailMaker(){
    myTrailColor = DEFAULT_COLOR;
  }

  public TrailMaker(Color lineColor){
    myTrailColor = lineColor;
  }

  /**
   * Creates a straight line of a trail.
   * @param startx Starting horizontal position
   * @param starty Starting vertical position
   * @param endx Ending horizontal position
   * @param endy Ending vertical position
   * @return the straight line trail
   */
  protected abstract Node createTrailInLine(double startx, double starty, double endx, double endy);

  /**
   * Updates the parameters of a trail.
   * @param trail trail to update
   */
  public abstract void updateTrail(Node trail);

  /**
   * Draws a straight line of a trail
   * @param startx Starting horizontal position
   * @param starty Starting vertical position
   * @param endx Ending horizontal position
   * @param endy Ending vertical position
   * @return the straight line trail
   */
  public Node drawTrailLine(double startx, double starty, double endx, double endy){
    Node trailLine = createTrailInLine(startx, starty, endx, endy);
    return trailLine;
  }

  /**
   * Sets a new color for future trails.
   * To apply to all current trails, update must be called.
   * @param newColor new color to make the trails
   */
  public void setTrailColor(Paint newColor){
    myTrailColor = newColor;
  }

  /**
   * Returns the current color of trails.
   *
   * @return the trail color
   */
  public Paint getTrailColor(){
    return myTrailColor;
  }

  /**
   * Updates all parameters of trails in a given list.
   * @param trails list of trails
   */
  public void updateTrails(List<Node> trails){
    for (Node trail : trails){
      // todo: assure that color is updated
      updateTrail(trail);
    }
  }

}
