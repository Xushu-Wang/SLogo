package View.viewclasses;

import Controller.ButtonObserver;
import Controller.Observable;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LanguageSelectionView extends View implements Observable<ButtonObserver> {
  private final int STANDARD_SPACING = 10;
  private final String LANGUAGES_RESOURCES_PATH = "languages.";
  public static final String DEFAULT_STYLESHEET = "/UI/css-files/Default.css";
  private final Dimension STANDARD_WINDOW_SIZE = new Dimension(400, 400);

  private List<ButtonObserver> myObservers;
  private ResourceBundle myLanguageResources;
  private ResourceBundle myPromptResources;
  private String mySelectedLanguage;
  private Text myPromptText;
  private int myCurrentIndex;


  public LanguageSelectionView(Stage stage) {
    super(stage);
    myLanguageResources = ResourceBundle.getBundle(LANGUAGES_RESOURCES_PATH + "LanguageOptions");
    myPromptResources = ResourceBundle.getBundle(LANGUAGES_RESOURCES_PATH + "LanguagePrompts");
    myObservers = new ArrayList<>();
  }

  @Override
  public Scene makeScene() {
    VBox box = new VBox(STANDARD_SPACING);
    makeAlternatingText();
    box.getChildren().add(myPromptText);
    Set<String> languages = myLanguageResources.keySet();
    for (String language : languages) {
      Button button = new Button(myLanguageResources.getString(language));
      button.setId(language);
      button.setOnAction(e -> {
        Button clicked = (Button) e.getSource();
        mySelectedLanguage = clicked.getId();
        notifyObservers();
        this.hide();
      });
      box.getChildren().add(button);
    }

    Scene scene = new Scene(box, STANDARD_WINDOW_SIZE.getWidth(), STANDARD_WINDOW_SIZE.getHeight());
    scene.getStylesheets().add(getClass().getResource(DEFAULT_STYLESHEET).toExternalForm());
    return scene;
  }

  private void makeAlternatingText() {
    myPromptText = new Text(myPromptResources.getString(myPromptResources.getKeys().nextElement()));
    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
      List<String> keyList = new ArrayList<>(myPromptResources.keySet());
      myCurrentIndex = (myCurrentIndex + 1) % keyList.size();
      myPromptText.setText(myPromptResources.getString(keyList.get(myCurrentIndex)));
    }));
    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.play();
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
      observer.onEvent(mySelectedLanguage);
    }
  }
}
