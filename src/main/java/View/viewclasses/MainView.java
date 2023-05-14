package View.viewclasses;

import Controller.ButtonObserver;
import Controller.Observable;
import Controller.Observer;
import View.Canvas;
import View.canvasclasses.DefaultCanvas;
import View.toggleableViews.CustomizeToggleableView;
import View.toggleableViews.FunctionsToggleableView;
import View.toggleableViews.HelpToggleableView;
import View.toggleableViews.HistoryToggleableView;
import View.toggleableViews.ToggleablePane;
import View.toggleableViews.VariablesToggleableView;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Default UI for the program, following the Figma design.
 *
 * @author tmh85
 * @author rb419
 */
public class MainView extends View implements Observable<ButtonObserver> {

  private static final String DEFAULT_RESOURCE_PACKAGE = "UI.";
  private static final String GRIDLOCATION_RESOURCE = DEFAULT_RESOURCE_PACKAGE + "GridLocation";
  private static final boolean DEBUG = false;
  private static final int DEFAULT_BUTTON_SIZE = 32;
  private int myNumberOfColumns;
  private int myNumberOfRows;
  private Dimension myDefaultWindowSize;
  private double myColWidth;
  private double myRowHeight;

  private ResourceBundle myGridInfo;
  private GridPane myScreen;
  private Map<String, ToggleablePane> mySidePanes;
  private Canvas myCanvas;
  private List<ButtonObserver> myObservers;
  private Button myLastButtonClicked;
  private String myLanguage;

  public MainView(String language) {
    super(language);
    myLanguage = language;
    myObservers = new ArrayList<>();
    loadWindowInfoFromResources();
  }

  @Override
  public Scene makeScene() {
    super.setStageTitle(super.getFromResources("Title"));

    initializeGridPane();
    initializeCanvas();
    HBox titleBar = createTitleBar();
    mySidePanes = new HashMap<>();
    ToggleablePane infoWindow = makeInfoToggleablePane();
    ToggleablePane codeWindow = makeCodeToggleablePane();
    HBox inputArea = createUserInputArea();

    Map<Pane, String> mapOfUIWindows = Map.of(
        titleBar, "TITLEBAR",
        infoWindow.getPane(), "INFOWINDOW",
        codeWindow.getPane(), "VARWINDOW",
        inputArea, "INPUT"
    );
    placeElementsOnGridPane(mapOfUIWindows);

    Scene hello = new Scene(myScreen, myDefaultWindowSize.width, myDefaultWindowSize.height);
    hello.getStylesheets().add(getClass().getResource(getDefaultStylesheet()).toExternalForm());
    return hello;
  }

  @Override
  public void register(ButtonObserver observer) {
    myObservers.add(observer);
  }

  @Override
  public void remove(ButtonObserver observer) {
    myObservers.remove(observer);
  }

  @Override
  public void notifyObservers() {
    for (ButtonObserver observer : myObservers) {
      observer.onEvent(myLastButtonClicked.getId());
    }
  }


  /**
   * Places all elements in the map on the gridpane using its corresponding GridLocation record.
   * @param uiElements map of panes and gridlocations
   */
  private void placeElementsOnGridPane(Map<Pane, String> uiElements){
    for (Pane window : uiElements.keySet()){
      String resourcePrefix = uiElements.get(window);
      myScreen.add(
          window,
          getIntegerFromResource(resourcePrefix + "_COLSTART", myGridInfo),
          getIntegerFromResource(resourcePrefix + "_ROWSTART", myGridInfo),
          getIntegerFromResource(resourcePrefix + "_COLSPAN", myGridInfo),
          getIntegerFromResource(resourcePrefix + "_ROWSPAN", myGridInfo)
      );
    }
  }

  private ToggleablePane makeCodeToggleablePane() {
    Dimension size = getPaneSize("VARWINDOW");
    ToggleablePane pane = new ToggleablePane();
    pane.addView(new VariablesToggleableView(size.width, size.height, myLanguage));
    pane.addView(new FunctionsToggleableView(size.width, size.height, myLanguage));
    mySidePanes.put("CodePane", pane);
    return pane;
  }

  private ToggleablePane makeInfoToggleablePane() {
    Dimension size = getPaneSize("INFOWINDOW");
    ToggleablePane pane = new ToggleablePane();
    pane.addView(new HelpToggleableView(size.width, size.height, myLanguage));
    pane.addView(new HistoryToggleableView(size.width, size.height, myLanguage));
    pane.addView(new CustomizeToggleableView(size.width, size.height, myLanguage));
    mySidePanes.put("InfoPane", pane);
    return pane;
  }

  /**
   * Sets constraints on each row and column based on the total number of rows and columns.
   *
   * @param myScreen current gridpane
   */
  private void initializeColumnAndRowSizes(GridPane myScreen) {
    double col_percent_width = (1.0 / myNumberOfColumns) * 100;
    double row_percent_height = (1.0 / myNumberOfRows) * 100;
    for (int i = 0; i < myNumberOfColumns; i++) {
      ColumnConstraints col = new ColumnConstraints();
      col.setPercentWidth(col_percent_width);
      myScreen.getColumnConstraints().add(col);
    }
    for (int j = 0; j < myNumberOfRows; j++){
      RowConstraints row = new RowConstraints();
      row.setPercentHeight(row_percent_height);
      myScreen.getRowConstraints().add(row);
    }

    myRowHeight = (row_percent_height/100) * myDefaultWindowSize.height;
    myColWidth = (col_percent_width/100) * myDefaultWindowSize.width;
  }

  public String getCommandsText() {
    TextArea textArea = (TextArea) myScreen.lookup("#Input");
    if (textArea == null) {
      return "";
    }
    return textArea.getText();
  }

  public void populateUserInput(String preset) {
    TextArea textArea = (TextArea) myScreen.lookup("#Input");
    textArea.setText(preset);
  }

  public Observer getSideView(String sidePaneName, String sideViewName) {
    return mySidePanes.get(sidePaneName).getView(sideViewName);
  }

  public Canvas getCanvas() {
    return myCanvas;
  }
  public void setCanvas(Canvas canvas){
    myCanvas = canvas;
  }

  /**
   * Creates the canvas and adds it to the Gridpane.
   */
  private void initializeCanvas(){
    String resourcePrefix = "CANVAS";
    double windowWidth = myColWidth * (getIntegerFromResource(resourcePrefix + "_COLSPAN", myGridInfo));
    double windowHeight = myRowHeight * (getIntegerFromResource(resourcePrefix + "_ROWSPAN", myGridInfo));
    // Note: Initial turtle position is slightly off because I'm not factoring in padding, but
    // there doesn't seem to be a good way to factor that in without hard coding it in.
    setCanvas(createCanvas(windowWidth, windowHeight));
    getCanvas().addToGridPane(myScreen,
        getIntegerFromResource(resourcePrefix + "_COLSTART", myGridInfo),
        getIntegerFromResource(resourcePrefix + "_ROWSTART", myGridInfo),
        getIntegerFromResource(resourcePrefix + "_COLSPAN", myGridInfo),
        getIntegerFromResource(resourcePrefix + "_ROWSPAN", myGridInfo)
    );
  }

  protected Canvas createCanvas(double windowWidth, double windowHeight){
    Canvas newCanvas = new DefaultCanvas(windowWidth, windowHeight);
    return newCanvas;
  }

  /**
   * Creates the top row of the scene.
   * TODO: Make this properly aligned. Also add the language button box.
   * @return HBox of the top row
   */
  private HBox createTitleBar() {
    HBox titleBar = new HBox();
    Text titleText = new Text(super.getFromResources("TitleText"));
    titleText.getStyleClass().add("title-text");
    HBox textHolder = new HBox(titleText);
    textHolder.setAlignment(Pos.CENTER_LEFT);
    //titleBar.getChildren().add(titleText);
    HBox titleButtons = new HBox();
    for (String buttonName : new String[]{"SaveButton", "LoadButton", "AboutButton", "ClearButton", "AddViewButton"}){
      addButtonToPane(buttonName, titleButtons);
    }
    titleButtons.getStyleClass().addAll("test-right");
    titleBar.getChildren().addAll(textHolder, titleButtons);
    //titleBar.getStyleClass().add("titlebar");

    return titleBar;
  }

  /**
   * Creates a window, complete with a topBar of buttons.
   * @param buttonList String array of resourceID's corresponding to a button.
   * @param windowID What you wish the ID for the window to be.
   * @return VBox of the entire window.
   */
  private VBox createWindow(String[] buttonList, String windowID){
    VBox theWindow = new VBox();
    HBox topBar = makeWindowTopBar(buttonList, windowID + "TopBar");
    theWindow.getChildren().add(topBar);
    theWindow.getStyleClass().add("window");

    return (VBox) setID(windowID, theWindow);
  }

  /**
   * Creates the buttons that compose the top bar of a window.
   * @param buttonList List of resource file id's that correspond to each button.
   * @param topBarID What you wish the top bar ID to be.
   * @return HBox of the entire topBar.
   */
  private HBox makeWindowTopBar(String[] buttonList, String topBarID){
    HBox topBar = new HBox();
    for (String buttonName : buttonList){
      addButtonToPane(buttonName, topBar);
    }
    topBar.getStyleClass().add("sub-window");
    return (HBox) setID(topBarID, topBar);
  }

  /**
   * Update the text inside of a given window.
   * TODO: Make sure this actually updates.
   * @param resourceString resource ID of the string you want to add.
   * @param ourWindow Window you want to add content to.
   */
  private void updateWindowLabel(String resourceString, Pane ourWindow){
    Label text = new Label(super.getFromResources(resourceString));
    text.getStyleClass().add("window-text");
    ourWindow.getChildren().add(text);
  }

  /**
   * Adds a button (based on its resource ID) to a given pane.
   * @param buttonName resourceID of the desired button
   * @param thePane pane we wish to add a button to.
   */
  private void addButtonToPane(String buttonName, Pane thePane){
    Button theButton = makeButton(buttonName, DEFAULT_BUTTON_SIZE, DEFAULT_BUTTON_SIZE);
    theButton.setOnAction(e -> {
      myLastButtonClicked = (Button) e.getSource();
      notifyObservers();
    });
    thePane.getChildren().add(theButton);
  }

  /**
   * Wrapper function that will create a TextArea along with the buttons that go with it.
   * @return HBox of the TextArea along with buttons that work with it.
   */
  private HBox createUserInputArea(){
    HBox userArea = new HBox();
    TextArea inputBox = makeInputField();
    VBox buttonHolder = new VBox();
    addButtonToPane("RunButton", buttonHolder);
    addButtonToPane("StepButton", buttonHolder);
    userArea.getChildren().addAll(inputBox, buttonHolder);

    return (HBox) setID("UserInputArea", userArea);
  }

  private void initializeGridPane(){
    myScreen = new GridPane();
    myScreen.setPrefSize(myDefaultWindowSize.width, myDefaultWindowSize.height);
    myScreen.setGridLinesVisible(DEBUG);
    initializeColumnAndRowSizes(myScreen);
    myScreen.getStyleClass().add("root");
    myScreen.setId("Screen");
  }

  // Taking the makeInputField and setID methods from example_testing.
  private TextArea makeInputField() {
    TextArea result = new TextArea();
    result.setPromptText(getFromResources("TextAreaPrompt"));
    result.getStyleClass().add("input");
    return (TextArea)setID("Input", result);
  }

  /**
   * Loads grid location information from the resources file.
   * TODO: ADD ERROR CHECKING
   */
  private void loadWindowInfoFromResources(){
    myGridInfo = ResourceBundle.getBundle(GRIDLOCATION_RESOURCE);
    myNumberOfColumns = getIntegerFromResource("NUMBER_OF_COLUMNS", myGridInfo);
    myNumberOfRows = getIntegerFromResource("NUMBER_OF_ROWS", myGridInfo);
    myDefaultWindowSize = new Dimension(
        getIntegerFromResource("DEFAULT_WINDOW_WIDTH", myGridInfo),
        getIntegerFromResource("DEFAULT_WINDOW_HEIGHT", myGridInfo)
    );
  }

  /**
   * Reads an integer from a resource bundle
   *
   * @param key key to get an integer from
   * @param resource resource file we are reading from
   * @return integer read from resource file
   */
  private int getIntegerFromResource(String key, ResourceBundle resource){
    return Integer.parseInt(resource.getString(key));
  }

  public Dimension getPaneSize(String pane) {
    int width = (int) (getIntegerFromResource(pane + "_COLSPAN", myGridInfo) * myRowHeight);
    int height = (int) (getIntegerFromResource(pane + "_ROWSPAN", myGridInfo) * myColWidth);
    return new Dimension(width, height);
  }
}
