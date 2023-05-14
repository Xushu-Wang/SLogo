package View.trailclasses;

import View.TrailMaker;
import javafx.scene.Node;
import javafx.scene.shape.Line;

/**
 * TrailMaker that creates line trails.
 *
 * @author tmh85
 */
public class LineTrailMaker extends TrailMaker {

  @Override
  protected Node createTrailInLine(double startx, double starty, double endx, double endy) {
    Line newLine = new Line(startx, starty, endx, endy);
    newLine.setFill(getTrailColor());
    return newLine;
  }

  @Override
  public void updateTrail(Node trail) {
    // todo: check if trail is a line
    Line lineTrail = (Line) trail;
    lineTrail.setFill(getTrailColor());

  }
}
