package View;

import Controller.TurtleObserver;
import java.awt.Dimension;
import javafx.scene.layout.GridPane;

/**
 * Canvas interface details the API that is used by the Canvas.
 *
 * @author tmh85
 */

public abstract class Canvas implements TurtleObserver {

  public Canvas(){
    initializeCanvas(0.0, 0.0);
  }

  public Canvas(double initWindowWidth, double initWindowHeight){
    initializeCanvas(initWindowWidth, initWindowHeight);
  }

  /**
   * Adds the Canvas to a given grid pane.
   *
   * @param currPane the GridPane that we wish to add the canvas to.
   * @param colIndex Column that the canvas will start on.
   * @param rowIndex Row that the canvas will start on.
   * @param colSpan  Number of columns that the canvas takes up.
   * @param rowSpan  Number of rows that the canvas takes up.
   */
  public abstract void addToGridPane(GridPane currPane, int colIndex, int rowIndex, int colSpan,
      int rowSpan);

  // UPDATE: CHANGE FROM PLAN, Update will no longer require a turtle argument.

  /**
   * Update all elements on the Canvas with new values.
   */
  public abstract void update();

  /**
   * Clear whatever's on the Canvas, leaving behind a blank state.
   */
  public abstract void clear();

  /**
   * Alter the speed of any animations on the canvas.
   *
   * @return true if successful, false if not
   */
  public abstract boolean changeAnimationSpeed(double speed);

  /**
   * returns the current size of the canvas.
   *
   * @return size
   */
  public abstract Dimension getSize();

  /**
   * Initialize the canvas using a given width and height.
   * @param initWindowWidth Width of the window.
   * @param initWindowHeight Height of the window.
   */
  protected abstract void initializeCanvas(double initWindowWidth, double initWindowHeight);

  public abstract TurtleUI getMyTurtle();
}