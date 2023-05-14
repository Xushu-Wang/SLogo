package View.canvasclasses;

import View.TrailMaker;
import View.TurtleAnimator;
import View.Canvas;
import View.TurtleUI;
import View.trailclasses.LineTrailMaker;
import View.viewturtles.ViewTurtle;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javafx.animation.SequentialTransition;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class DefaultCanvas extends Canvas {

  private Pane myPane;
  private javafx.scene.canvas.Canvas myCanvas;
  //private TurtleUI myTurtle;
  private TurtleAnimator myTurtleAnimator;
  private TrailMaker myTrailMaker;
  private ArrayList<Node> myTrails;
  private Map<Integer, TurtleUI> myTordles;
  private static final int INITIAL_ID = 0;
  public DefaultCanvas() {
    super();
  }

  public DefaultCanvas(double myWinWidth, double myWinHeight) {
    super(myWinWidth, myWinHeight);
  }

  /**
   * Initializes the canvas, creating a turtle and centering it as needed.
   *
   * @param initWindowWidth  Width of the window.
   * @param initWindowHeight Height of the window.
   */
  @Override
  protected void initializeCanvas(double initWindowWidth, double initWindowHeight) {
    myPane = new Pane();
    //myCanvas = new javafx.scene.canvas.Canvas();
    myTrailMaker = new LineTrailMaker();
    myTordles = new HashMap<Integer, TurtleUI>();
    myTordles.put(INITIAL_ID, createTurtle(INITIAL_ID));
    myTordles.get(INITIAL_ID).addToPane(myPane);
    initializeTurtlePosition(initWindowWidth, initWindowHeight);
    myPane.getStyleClass().add("canvas");
    // TODO: REFACTOR TO NOT REQUIRE AN INITIAL TURTLE
    myTurtleAnimator = new TurtleAnimator();
    myTrails = new ArrayList<>();
  }

  protected TurtleUI createTurtle(int ID){
    return new ViewTurtle(ID);
  }

  protected TurtleUI createTurtle(){
    return new ViewTurtle();
  }

  /**
   * Adds the canvas to a given gridpane.
   *
   * @param currPane the GridPane that we wish to add the canvas to.
   * @param colIndex Column that the canvas will start on.
   * @param rowIndex Row that the canvas will start on.
   * @param colSpan  Number of columns that the canvas takes up.
   * @param rowSpan  Number of rows that the canvas takes up.
   */
  @Override
  public void addToGridPane(GridPane currPane, int colIndex, int rowIndex, int colSpan,
      int rowSpan) {
    currPane.add(myPane, colIndex, rowIndex, colSpan, rowSpan);
  }

  /**
   * Clears the Canvas, destroying all lines and resetting the turtle back to it's initial
   * position.
   */
  @Override
  public void clear() {
    for (int ID : myTordles.keySet()) {
//      myPane.getChildren().remove(myTordles.get(ID));
//      myTordles.remove(ID);
      this.updateOnPosition(ID, 0, 0);
      this.updateOnDirection(ID, 0);
    }
//    myTordles.put(INITIAL_ID, createTurtle(INITIAL_ID));

    for (Node trail : myTrails){
      myPane.getChildren().remove(trail);
    }
  }

  // todo: still needed?
  @Override
  public void update() {
//    for (Node item : myPane.getChildren()){
//      //do things?
//    }
    //updateOnPosition();
    //updateOnDirection();
    //myTurtleAnimator.playSavedAnimations();
    //myTurtleAnimator.clearSavedAnimations();
  }

  //todo: still needed?
  @Override
  public boolean changeAnimationSpeed(double speed) {
    myTurtleAnimator.setSpeed(speed);
    return true;
  }

  /**
   * Returns the current size of the Canvas based on its location in the parent stage.
   *
   * @return dimension with width and height of the canvas
   */
  @Override
  public Dimension getSize() {
    double currWidth = myPane.getBoundsInParent().getWidth();
    double currHeight = myPane.getBoundsInParent().getHeight();
    Dimension result = new Dimension();
    result.setSize(currWidth, currHeight);
    return result;
  }

  /**
   * Updates the position of the view turtle. Note that this will correct the turtles location for
   * the canvas, meaning that it will be centered for 0,0.
   *
   * @param x x-location
   * @param y y-location
   */
  public void updateOnPosition(double x, double y) {
    //Point2D offset = point(myPane).atPosition(Pos.CENTER).query();
    double windowWidth = myPane.getLayoutBounds().getWidth();
    double windowHeight = myPane.getLayoutBounds().getHeight();
    double corrected_x = correctPosition(windowWidth, x, myTordles.get(INITIAL_ID).getSize().getWidth());
    double corrected_y = correctPosition(windowHeight, y, myTordles.get(INITIAL_ID).getSize().getHeight());

    Optional<Node> trail = Optional.ofNullable(makeNewTrail(corrected_x, corrected_y));
    SequentialTransition animation = myTurtleAnimator.makeAnimation(myTurtleAnimator.animateLineMovement(corrected_x, corrected_y, trail, myTordles.get(INITIAL_ID)), myTordles.get(INITIAL_ID));
    myTordles.get(INITIAL_ID).setPosition(corrected_x, corrected_y);
    myTurtleAnimator.playAnimation(animation);
  }

  // TODO: DON'T DUPLICATE CODE
  //ID:1
  public void updateOnPosition(int ID, double x, double y) {
    //Point2D offset = point(myPane).atPosition(Pos.CENTER).query();
    double windowWidth = myPane.getLayoutBounds().getWidth();
    double windowHeight = myPane.getLayoutBounds().getHeight();
    double corrected_x = correctPosition(windowWidth, x, myTordles.get(ID).getSize().getWidth());
    double corrected_y = correctPosition(windowHeight, y, myTordles.get(ID).getSize().getHeight());

    Optional<Node> trail = Optional.ofNullable(makeNewTrail(corrected_x, corrected_y));
    SequentialTransition animation = myTurtleAnimator.makeAnimation(myTurtleAnimator.animateLineMovement(corrected_x, corrected_y, trail, myTordles.get(ID)), myTordles.get(ID));
    myTordles.get(INITIAL_ID).setPosition(corrected_x, corrected_y);
    myTurtleAnimator.playAnimation(animation);
  }

  private Node makeNewTrail(double x, double y){
    if (myTordles.get(INITIAL_ID).isPenActive()) {
      Node trail = myTrailMaker.drawTrailLine(myTordles.get(INITIAL_ID).currentPosition().getWidth(),
          myTordles.get(INITIAL_ID).currentPosition().getHeight(), x, y);
      this.putOnCanvas(trail);
      this.addTrailToList(trail);
      return trail;
    }
    else{
      return null;
    }
  }

  /**
   * Adds a given thing to the canvas (typically a trail)
   * @param thing
   */
  private void putOnCanvas(Node thing){
    myPane.getChildren().addAll(thing);
  }

  /**
   * Adds a given trail to the list oftrails
   * @param trail
   */
  private void addTrailToList(Node trail){
    myTrails.add(trail);
  }

  /**
   * Update the direction the turtle is facing.
   *
   * @param angle how much direction the turtle should turn in
   */
  public void updateOnDirection(double angle) {
    double correctedAngle = correctAngle(angle);
    SequentialTransition animation = myTurtleAnimator.makeAnimation(
        myTurtleAnimator.animateRotation(correctedAngle, myTordles.get(INITIAL_ID)), myTordles.get(INITIAL_ID));
    myTordles.get(INITIAL_ID).setRotation(correctedAngle);
    myTurtleAnimator.playAnimation(animation);
  }

  public void updateOnDirection(int ID, double angle) {
    double correctedAngle = correctAngle(angle);
//    System.out.println(
//        "current turtle angle (b4 set): " + myTurtle.currentRotation() + " given angle: " + angle);
    SequentialTransition animation = myTurtleAnimator.makeAnimation(
        myTurtleAnimator.animateRotation(correctedAngle, myTordles.get(ID)), myTordles.get(ID));
    myTordles.get(ID).setRotation(correctedAngle);
//    System.out.println(
//        "current turtle angle: " + myTurtle.currentRotation() + " given angle: " + angle);
    myTurtleAnimator.playAnimation(animation);
  }

  @Override
  public void updateOnVisibility(int id, boolean status) {

  }

  /**
   * Corrects the position of the turtle to appear properly on the Canvas. Canvas assumes that 0,0
   * is the center, so correcting everything based on that. This also needs the size of the turtle
   * to correct the turtles location so that its position is based on the center of the turtle and
   * not the top left corner.
   *
   * @param windowSize    size of the canvas in this direction
   * @param givenPosition position given from the model
   * @param turtleSize    size of the turtle in this dimension
   * @return the corrected position
   */
  private double correctPosition(double windowSize, double givenPosition, double turtleSize) {
    double correctedPosition = (windowSize * 0.5) + givenPosition - turtleSize / 2;
    if (isPositionOutOfBounds(windowSize, correctedPosition))  {
      return findOutOfBoundsPosition(windowSize, correctedPosition);
    }
    else {
      return correctedPosition;
    }
  }

  private double findOutOfBoundsPosition(double windowSize, double correctedPosition){
    double remainingdistance = Math.abs(correctedPosition % windowSize);
    if (correctedPosition < 0){
      return (windowSize - remainingdistance);
    }
    else{
      return remainingdistance;
    }
  }

  private boolean isPositionOutOfBounds(double windowSize, double position){
    return (position > windowSize) || (position < 0.0);
  }

  /**
   * Corrects the orientation of the turtle to match the backend.
   *
   * @param givenAngle Angle we wish to update
   * @return the corrected angle
   */
  private double correctAngle(double givenAngle) {
    return (givenAngle);
  }

  /**
   * Initial position of the turtle based on a given width and height.
   *
   * @param initWidth  initial horizontal location
   * @param initHeight initial vertical location
   */
  private void initializeTurtlePosition(double initWidth, double initHeight) {
    myTordles.get(INITIAL_ID).setPosition(correctPosition(initWidth, 0.0, myTordles.get(INITIAL_ID).getSize().getWidth()),
        correctPosition(initHeight, 0.0, myTordles.get(INITIAL_ID).getSize().getHeight()));
    myTordles.get(INITIAL_ID).setRotation((correctAngle(0.0)));
  }

  public TurtleUI getMyTurtle(){
    return myTordles.get(INITIAL_ID);
  }

}
