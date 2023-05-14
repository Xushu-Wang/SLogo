package Controller.Actions;

import Controller.Controller;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class SaveAction implements Action {
  public SaveAction() {
    // Does not do anything
  }

  @Override
  public void execute(Controller controller) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Save Text");
    fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.slogo"));
    File file = fileChooser.showSaveDialog(new Stage());

    if (file != null) {
      try (PrintWriter writer = new PrintWriter(new FileWriter(file), true)) {
        writer.write(controller.getCommandsFromView());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
