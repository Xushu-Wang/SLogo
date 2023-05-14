package View.viewclasses;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class AboutView extends View {
  private final String IMAGE_PATH = "/UI/images/miscellaneous/meme.jpg";
  private final int DEFAULT_IMAGE_SIZE = 200;
  public AboutView(String language) {
    super(language);
    super.addSceneToStage(makeScene());
  }

  @Override
  public Scene makeScene() {
    VBox box = new VBox();
    box.getChildren().add(new Text(getFromResources("Team")));
    box.getChildren().add(new Text(getFromResources("Teammates")));
    ImageView image = new ImageView(new Image(IMAGE_PATH));
    image.setFitHeight(DEFAULT_IMAGE_SIZE);
    image.setFitWidth(DEFAULT_IMAGE_SIZE);
    image.getStyleClass().add("meme");
    box.getChildren().add(image);
    Scene scene = new Scene(box);
    scene.getStylesheets().add(getDefaultStylesheet());
    return scene;
  }
}
